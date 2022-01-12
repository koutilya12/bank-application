import { Component, OnInit } from '@angular/core';
import { Account } from 'src/app/Domain/Account';
import { User } from 'src/app/Domain/User';
import { AccountService } from 'src/app/services/account.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-transact',
  templateUrl: './transact.component.html',
  styleUrls: ['./transact.component.css']
})
export class TransactComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService,private accountService: AccountService) { }

  accounts : Array<Account> | undefined

  user : User | undefined
  

  isSuccessful = false;
  isFailed     : boolean | undefined;
  errorMessage : any;


  ngOnInit(): void {
    this.user = this.tokenStorage.getUserDetails();

    this.accountService.getAccountDetailsByUserId(this.user?.userId).subscribe(
      response => {
        if(response.status == 'SUCCESS'){
          this.accounts = response.data;
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
