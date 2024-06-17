import {Component, effect, inject, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CardListing} from "../landlord/model/listing.model";
import {filter, Subscription} from "rxjs";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {CardListingComponent} from "../shared/card-listing/card-listing.component";
import {TenantListingService} from "../tenant/tenant-listing.service";
import {ToastService} from "../services/toast.service";
import {CategoryService} from "../services/category.service";
import {Pagination} from "../core/model/request.model";
import {Category} from "../modals/category";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    FaIconComponent,
    CardListingComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, OnDestroy {

  tenantListingService = inject(TenantListingService);
  toastService = inject(ToastService);
  categoryService = inject(CategoryService);
  activatedRoute = inject(ActivatedRoute);
  router = inject(Router);

  listings: Array<CardListing> | undefined;

  pageRequest: Pagination = {size: 20, page: 0, sort: []};

  loading = false;

  categoryServiceSubscription: Subscription | undefined;
  searchIsLoading = false;
  emptySearch = false;
  private searchSubscription: Subscription | undefined;



  constructor() {
    this.listenToGetAllCategory();
  }

  ngOnDestroy(): void {
    this.tenantListingService.resetGetAllCategory();

    if (this.categoryServiceSubscription) {
      this.categoryServiceSubscription.unsubscribe();
    }

  }

  ngOnInit(): void {
    this.listenToChangeCategory();
  }

  private listenToChangeCategory() {
    this.categoryServiceSubscription = this.categoryService.changeCategoryObs.subscribe({
      next: (category: Category) => {
        this.loading = true;
        if (!this.searchIsLoading) {
          this.tenantListingService.getAllByCategory(this.pageRequest, category.technicalName);
        }
      }
    })
  }

  private listenToGetAllCategory() {
    effect(() => {
      const categoryListingsState = this.tenantListingService.getAllByCategorySig();
      if (categoryListingsState.status === "OK") {
        this.listings = categoryListingsState.value?.content;
        this.loading = false;
        this.emptySearch = false;
      } else if (categoryListingsState.status === "ERROR") {
        this.toastService.send({
          severity: "error", detail: "Error when fetching the listing", summary: "Error",
        });
        this.loading = false;
        this.emptySearch = false;
      }
    });
  }
}
