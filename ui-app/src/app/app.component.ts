import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Constant } from './constant/Constant';
import { User } from './models/User';
import { UserService } from './service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ui-app';

  userInContext: User = Constant.user;

  constructor(private userService: UserService, private route: Router) { }

  ngOnInit(): void {
    if (localStorage.getItem("auth_token") == null) {
      this.route.navigate(['login']);
    }
    else {
      this.userService.getUserInContext().subscribe((data: User) => {
        Constant.user= data;
        this.userInContext = data;
        this.route.navigate(['']);
      },error => {
        console.log('oops', error);
        if(error == "Forbidden") {
          this.route.navigate(['login']);
        }
      });
    }
  }

  logout() {
    localStorage.removeItem("auth_token");
    this.userInContext = {} as User;
    Constant.user = {} as User;
    this.route.navigate(['login']);
    location.reload();
  }

  isUserLogin() {
    if(Constant.user.id != null) {
      return true;
    }else {
      return false;
    }
  }

}
