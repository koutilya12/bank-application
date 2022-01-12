import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { AccountService } from 'src/app/services/account.service';
import { User } from '../../../Domain/User';
import { Account } from '../../../Domain/Account';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  isSuccessful = false;
  isFailed: boolean | undefined;
  errorMessage: any;
  user: User = {
    "userId": undefined,
    name: undefined,
    password: undefined,
    emailId: undefined,
    mobileNum: undefined,
    dateOfBirth: undefined,
    address: undefined,
    userType: undefined
  };

  accountTypes = [
    { id: "CURRENT", name: "Current Account" },
    { id: "SAVINGS", name: "Savings Account" },
  ];
  account: Account = {
    "user": this.user, "accountType": this.accountTypes[0].name,
    accountId: undefined,
    status: undefined,
    balance: undefined,
    lastUpdated: undefined
  };
  constructor(private accountService: AccountService, private tokenService: TokenStorageService) { }



  ngOnInit(): void {
    this.user.userId = this.tokenService.getUserDetails()?.userId;
  }

  onSubmit(): void {

    this.accountService.createAccount(this.account).subscribe(
      response => {
        if (response.status == 'SUCCESS') {
          this.isSuccessful = true;
          this.isFailed = false;
        } else if (response.errorMessage) {
          this.errorMessage = response.errorMessage;
          this.isFailed = true;
        } else {
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
  
  setAccountType(target : any) : void {
    console.log(target)
    this.account.accountType = target.value;
  }
}

