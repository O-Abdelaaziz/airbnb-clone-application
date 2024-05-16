import {Component, inject, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {fontAwesomeIcons} from "./shared/font-awesome-icons";
import {FaIconLibrary, FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {faAirbnb} from "@fortawesome/free-brands-svg-icons";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,FontAwesomeModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'airbnb-clone-client';
  faIconLibrary = inject(FaIconLibrary);


  ngOnInit(): void {
    this.initFontAwesome();
  }

  private initFontAwesome() {
    this.faIconLibrary.addIcons(...fontAwesomeIcons);
  }

  protected readonly faAirbnb = faAirbnb;
}
