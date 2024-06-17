import {computed, inject, Injectable, signal, WritableSignal} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {State} from "../core/model/state.model";
import {createPaginationOption, Page, Pagination} from "../core/model/request.model";
import {CardListing} from "../landlord/model/listing.model";
import {CategoryName} from "../modals/category";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TenantListingService {

  http = inject(HttpClient);

  private getAllByCategory$: WritableSignal<State<Page<CardListing>>>
    = signal(State.Builder<Page<CardListing>>().forInit())
  getAllByCategorySig = computed(() => this.getAllByCategory$());

  getAllByCategory(pageRequest: Pagination, category: CategoryName): void {
    let params = createPaginationOption(pageRequest);
    params = params.set("category", category);
    this.http.get<Page<CardListing>>(`${environment.API_URL}/tenant-listing/get-all-by-category`, {params})
      .subscribe({
        next: displayListingCards =>
          this.getAllByCategory$.set(State.Builder<Page<CardListing>>().forSuccess(displayListingCards)),
        error: error => this.getAllByCategory$.set(State.Builder<Page<CardListing>>().forError(error))
      })
  }

  resetGetAllCategory(): void {
    this.getAllByCategory$.set(State.Builder<Page<CardListing>>().forInit())
  }

}
