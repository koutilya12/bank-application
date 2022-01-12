import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';
import { User } from '../../../Domain/User';


@Component({
  selector: 'app-update-contact-details',
  templateUrl: './update-contact-details.component.html',
  styleUrls: ['./update-contact-details.component.css']
})
export class UpdateContactDetailsComponent implements OnInit {

  emailId      : any;
  address      : any;
  userId       : any;
  isSuccessful = false;
  isFailed     : boolean | undefined;
  errorMessage : any;
  constructor(private userService : UserService,private tokenService : TokenStorageService) { }

  ngOnInit(): void {
    this.userId = this.tokenService.getUserDetails()?.userId;

  }

  onSubmit(): void {
    let user = {"userId": this.userId, "emailId": this.emailId, "address": this.address};
    this.userService.updateContactDetails(user).subscribe(
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
