import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
 
  password     : any;
  newPassword  : any;
  userId       : any;
  isSuccessful = false;
  isFailed     : boolean | undefined;
  errorMessage : any;
  constructor(private userService : UserService,private tokenService : TokenStorageService) { }


  ngOnInit(): void {
    this.userId = this.tokenService.getUserDetails()?.userId;
  }

  onSubmit(): void {


    this.userService.changePassword(this.userId,this.password,this.newPassword).subscribe(
      response => {
      if(response.status == 'SUCCESS'){
        this.isSuccessful = true;
        this.isFailed = false;
        }else if(response.errorMessage){
          this.errorMessage = response.errorMessage;
          this.isFailed = true; 
        }else {
          this.errorMessage = 'Something went wrong at server';
          this.isFailed = true;
        }
     },
      err => {
        this.errorMessage = err.error.message;
        this.isFailed = true;
      }
    );
  }
}
