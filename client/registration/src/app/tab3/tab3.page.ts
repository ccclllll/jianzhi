import { Component } from '@angular/core';
import { AuthService } from '../shared/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})
export class Tab3Page {
  // user = JSON.parse(localStorage.getItem('userVM'));
  icon = 'create';
  edit = false;

  constructor(public auth: AuthService, public router: Router) {

  }

  clickIcon(e) {
    this.edit = !this.edit;

    if (this.edit) {
      this.icon = 'save';
    } else {
      this.icon = 'create';
      // this.authService.updateUser(this.person).subscribe();
    }
  }
  // logout() {
  //   this.auth.clearToken();
  //   this.router.navigate(['']);
  // }

  // ionViewWillEnter() {
  //   this.user = JSON.parse(localStorage.getItem('userVM'));
  // }
}
