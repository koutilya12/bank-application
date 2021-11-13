import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
 // roles: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      //this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe(
      response => {
        if(response.status == 'SUCCESS'){
        this.tokenStorage.saveToken(response.data.jwt);
        this.tokenStorage.saveUser(response.data.user);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.reloadPage();
        }else if(response.errorMessage){
          this.errorMessage = response.errorMessage;
          this.isLoginFailed = true;
        }else {
          this.errorMessage = 'Something went wring at server';
          this.isLoginFailed = true;
        }
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    this.router.navigate(['/home']);
  }

  redirectToSignUp () : void {
    this.router.navigate(['/register']);
  }

}
