import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/User';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  username: string = '';
  password: string = '';
  usernameInput: string = '';
  passwordInput: string = '';
  emailInput: string = '';
  firstNameInput: string = '';
  lastNameInput: string = '';


  constructor(private userService : UserService, private router : Router) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.login(this.username,this.password).then(res => {
      localStorage.setItem('auth_token', res.jwt);
      this.router.navigate(['']);
      location.reload();
    });
  }

  createUser() {
    let user : User = {} as User;
    user.email =  this.emailInput;
    user.firstName = this.firstNameInput;
    user.lastName = this.lastNameInput;
    user.userName = this.usernameInput;
    user.password = this.passwordInput;
    this.userService.createUser(user).subscribe(data => { alert("User created successfully")})
  }

}
