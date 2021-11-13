import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Domain/User';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  user: User  = {
    userId : undefined,
    name: undefined,
    password: undefined,
    emailId: undefined,
    mobileNum: undefined,
    dateOfBirth: undefined,
    address: undefined,
    userType: undefined
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  onSubmit(): void {


    this.authService.register(this.user).subscribe(
      response => {
      if(response.status == 'SUCCESS'){
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        }else if(response.errorMessage){
          this.errorMessage = response.errorMessage;
          this.isSignUpFailed = true; 
        }else {
          this.errorMessage = 'Something went wring at server';
          this.isSignUpFailed = true;
        }
     },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  redirectToLogin() : void {
    this.router.navigate(['/login']);
  }

}
