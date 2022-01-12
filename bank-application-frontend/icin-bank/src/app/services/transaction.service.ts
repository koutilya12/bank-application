import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';



const BACKEND_API = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  
  createTransaction(transaction : any) : Observable<any> {
      return this.http.post(BACKEND_API + 'createTransaction',  transaction, httpOptions);
    }
  
    constructor(private http: HttpClient) { }
}
  
