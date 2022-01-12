import { Component, Input, OnInit } from '@angular/core';
import { Account } from 'src/app/Domain/Account';
import { Transaction } from 'src/app/Domain/Transaction';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  @Input('accounts') accounts: Array<Account> | undefined;

  isSuccessful = false;
  isFailed     : boolean | undefined;
  errorMessage : any;
 
  toAccount : Account = {
    accountId: undefined,
    user: undefined,
    status: undefined,
    accountType: undefined,
    balance: undefined,
    lastUpdated: undefined
  }

  fromAccount : Account = {
    accountId: undefined,
    user: undefined,
    status: undefined,
    accountType: undefined,
    balance: undefined,
    lastUpdated: undefined
  }

  transfer  : Transaction  ={
    transactionId: undefined,
    fromAccount: this.fromAccount,
    toAccount : this.toAccount,
    transactionAmount: undefined,
    TransactionType: undefined,
    transactionTime: undefined,
    status: undefined,
    createdBy: undefined
  };

  ngOnInit(): void {
  }

  constructor(private transactionService : TransactionService) { }


  setFromAccount(target : any) : void {
    if(this.transfer.fromAccount)
    this.transfer.fromAccount.accountId = target.value;
  }

  onSubmit(): void {
    this.transactionService.createTransaction(this.transfer).subscribe(
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
