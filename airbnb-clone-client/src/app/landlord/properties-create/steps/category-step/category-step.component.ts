import {Component, EventEmitter, inject, input, Output} from '@angular/core';
import {Category, CategoryName} from "../../../../modals/category";
import {CategoryService} from "../../../../services/category.service";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'app-category-step',
  standalone: true,
  imports: [
    FaIconComponent
  ],
  templateUrl: './category-step.component.html',
  styleUrl: './category-step.component.scss'
})
export class CategoryStepComponent {
  categoryName = input.required<CategoryName>();

  @Output()
  categoryChange = new EventEmitter<CategoryName>();

  @Output()
  stepValidityChange = new EventEmitter<boolean>();

  categoryService = inject(CategoryService);
  categories: Category[] | undefined;

  ngOnInit(): void {
    this.categories = this.categoryService.getCategories();
  }

  onSelectCategory(newCategory: CategoryName): void {
    this.categoryChange.emit(newCategory);
    this.stepValidityChange.emit(true);
  }
}
