import {Component, inject, OnInit} from '@angular/core';
import {ButtonModule} from "primeng/button";
import {ToolbarModule} from "primeng/toolbar";
import {MenuModule} from "primeng/menu";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {CategoryComponent} from "./category/category.component";
import {AvatarComponent} from "./avatar/avatar.component";
import {DialogService} from "primeng/dynamicdialog";
import {MenuItem} from "primeng/api";
import {ToastService} from "../../services/toast.service";

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

  // login () => this.authService.login();

  // logout () => this.authService.logout();

  ngOnInit(): void {
    this.currentMenuItems = this.fetchMenu();
    this.toastService.send({severity: "info", summary: "Welcome to Your airbnb App"});
  }

  private fetchMenu(): MenuItem[] {
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

}
