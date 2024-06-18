import {Component, effect, inject, OnInit} from '@angular/core';
import {ButtonModule} from "primeng/button";
import {ToolbarModule} from "primeng/toolbar";
import {MenuModule} from "primeng/menu";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {CategoryComponent} from "./category/category.component";
import {AvatarComponent} from "./avatar/avatar.component";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {MenuItem} from "primeng/api";
import {ToastService} from "../../services/toast.service";
import {AuthService} from "../../core/auth/auth.service";
import {User} from "../../core/model/user.model";
import {PropertiesCreateComponent} from "../../landlord/properties-create/properties-create.component";
import {SearchComponent} from "../../tenant/search/search.component";
import {ActivatedRoute} from "@angular/router";
import dayjs from "dayjs";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    ButtonModule,
    ToolbarModule,
    MenuModule,
    FontAwesomeModule,
    CategoryComponent,
    AvatarComponent
  ],
  providers: [DialogService],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit {
  location = "Anywhere";
  guests = "Add guests";
  dates = "Any week";

  currentMenuItems: MenuItem[] | undefined = [];

  toastService = inject(ToastService);

  authService = inject(AuthService);
  activatedRoute = inject(ActivatedRoute);
  dialogService = inject(DialogService);
  ref: DynamicDialogRef | undefined;

  connectedUser: User = {email: this.authService.notConnected};

  login = () => this.authService.login();

  logout = () => this.authService.logout();

  constructor() {
    effect(() => {
      if (this.authService.fetchUser().status === "OK") {
        this.connectedUser = this.authService.fetchUser().value!;
        this.currentMenuItems = this.fetchMenu();
      }
    });
  }

  ngOnInit(): void {
    this.authService.fetch(false);
    // this.currentMenuItems = this.fetchMenu();
    // this.toastService.send({severity: "info", summary: "Welcome to Your airbnb App"});
  }

  private fetchMenuOld(): MenuItem[] {
    return [
      {
        label: "Sign up",
        styleClass: "font-bold"
      },
      {
        label: "Log in",
      }
    ]
  }

  private fetchMenu(): MenuItem[] {
    if (this.authService.isAuthenticated()) {
      return [
        {
          label: "My properties",
          routerLink: "landlord/properties",
          visible: this.hasToBeLandlord(),
        },
        {
          label: "My booking",
          routerLink: "booking",
        },
        {
          label: "My reservation",
          routerLink: "landlord/reservation",
          visible: this.hasToBeLandlord(),
        },
        {
          label: "Log out",
          command: this.logout
        },
      ]
    } else {
      return [
        {
          label: "Sign up",
          styleClass: "font-bold",
          command: this.login
        },
        {
          label: "Log in",
          command: this.login
        }
      ]
    }
  }

  hasToBeLandlord(): boolean {
    return this.authService.hasAnyAuthority("ROLE_LANDLORD");
  }

  openNewListing() {
    this.ref = this.dialogService.open(PropertiesCreateComponent, {
      width: "60%",
      header: "Airbnb Your Home",
      closable: true,
      focusOnShow: true,
      modal: true,
      showHeader: true,
    });
  }

  openNewSearch(): void {
    this.ref = this.dialogService.open(SearchComponent,
      {
        width: "40%",
        header: "Search",
        closable: true,
        focusOnShow: true,
        modal: true,
        showHeader: true
      });
  }

  private extractInformationForSearch(): void {
    this.activatedRoute.queryParams.subscribe({
      next: params => {
        if (params["location"]) {
          this.location = params["location"];
          this.guests = params["guests"] + " Guests";
          this.dates = dayjs(params["startDate"]).format("MMM-DD")
            + " to " + dayjs(params["endDate"]).format("MMM-DD");
        } else if (this.location !== "Anywhere") {
          this.location = "Anywhere";
          this.guests = "Add guests";
          this.dates = "Any week";
        }
      }
    })
  }

}
