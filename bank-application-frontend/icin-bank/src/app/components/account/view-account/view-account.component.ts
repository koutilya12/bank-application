import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Account } from 'src/app/Domain/Account';
import { User } from 'src/app/Domain/User';
import { AccountService } from 'src/app/services/account.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';


@Component({
  selector: 'app-view-account',
  templateUrl: './view-account.component.html',
  styleUrls: ['./view-account.component.css']
})
export class ViewAccountComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService,private accountService: AccountService) { }


  displayedColumns: string[] = [ 'Id','Name', 'Type', 'Balance','Status'];

  accounts : Array<Account> | undefined

  user : User | undefined
  

  isSuccessful = false;
  isFailed     : boolean | undefined;
  errorMessage : any;

  dataSource: MatTableDataSource<any> | undefined;

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  ngAfterViewInit() {
    if (this.accounts) {
      this.dataSource = new MatTableDataSource<any>(this.accounts);
    }
    if (this.paginator && this.dataSource) {
      this.dataSource.paginator = this.paginator;
    }
  }


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




