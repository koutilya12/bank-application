import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../Domain/Account';

const BACKEND_API = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

const gethttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  createAccount(account : Account) : Observable<any> {
    return this.http.post(BACKEND_API + 'createAccount', account, httpOptions);

  }

  getAccountDetailsByUserId(userId : String) : Observable<any> {
    return this.http.get(BACKEND_API + 'getAccountDetailsByUser/' + userId,gethttpOptions);
  }

  constructor(private http: HttpClient) { }
}
