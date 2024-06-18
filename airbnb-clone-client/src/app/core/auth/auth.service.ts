import {computed, inject, Injectable, signal, WritableSignal} from '@angular/core';
import {HttpClient, HttpParams, HttpStatusCode} from "@angular/common/http";
import {Location} from "@angular/common";
import {State} from "../model/state.model";
import {User} from "../model/user.model";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private httpClient = inject(HttpClient);
  private location = inject(Location);
  public notConnected = "NOT_CONNECTED";

  private fetchUser$: WritableSignal<State<User>> = signal(State.Builder<User>().forSuccess({email: this.notConnected}));
  public fetchUser = computed(() => this.fetchUser$());

  login(): void {
    location.href = `${location.origin}${this.location.prepareExternalUrl("oauth2/authorization/okta")}`;
  }

  fetch(forceResync: boolean) {
    this.fetchHttpUser(forceResync)
      .subscribe(
        {
          next: user => this.fetchUser$.set(State.Builder<User>().forSuccess(user)),
          error: error => {
            if (error.status === HttpStatusCode.Unauthorized && this.isAuthenticated()) {
              this.fetchUser$.set(State.Builder<User>().forSuccess({email: this.notConnected}));
            } else {
              this.fetchUser$.set(State.Builder<User>().forError(error));
            }
          }
        }
      )
  }

  fetchHttpUser(forceResync: boolean) {
    const params = new HttpParams().set('forceResync', forceResync);
    return this.httpClient.get<User>(`${environment.API_URL}/auth/get-authenticated-user`, {params});
  }

  isAuthenticated(): boolean {
    if (this.fetchUser$().value) {
      return this.fetchUser$().value!.email !== this.notConnected;
    } else {
      return false;
    }
  }

  hasAnyAuthority(authorities: string[] | string): boolean {
    if(this.fetchUser$().value!.email === this.notConnected) {
      return false;
    }
    if(!Array.isArray(authorities)) {
      authorities = [authorities];
    }
    return this.fetchUser$().value!.authorities!
      .some((authority: string) => authorities.includes(authority));
  }

  logout(): void {
    this.httpClient.post(`${environment.API_URL}/auth/logout`, {})
      .subscribe({
        next: (response: any) => {
          this.fetchUser$.set(State.Builder<User>()
            .forSuccess({email: this.notConnected}));
          location.href = response.logoutUrl
        }
      })
  }
}
