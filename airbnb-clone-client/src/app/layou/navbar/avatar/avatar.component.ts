import {Component, input} from '@angular/core';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {NgClass, NgOptimizedImage} from "@angular/common";
import {AvatarModule} from "primeng/avatar";

@Component({
  selector: 'app-avatar',
  standalone: true,
  imports: [FontAwesomeModule, NgClass, NgOptimizedImage, AvatarModule],
  templateUrl: './avatar.component.html',
  styleUrl: './avatar.component.scss'
})
export class AvatarComponent {
  public avatarUrl = input<string>();
  public avatarSize = input<"avatar-sm" | "avatar-xl">();
}
