import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Domain/User';

const BACKEND_API = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  changePassword(userId: String, oldPassword: string, newPassword: string) : Observable<any> {
    return this.http.post(BACKEND_API + 'changePassword',  {
      userId,
      oldPassword,
      newPassword
    }, httpOptions);
  }

  updateContactDetails(user : any) : Observable<any> {
    return this.http.post(BACKEND_API + 'updateContactDetails',  user, httpOptions);
  }

  constructor(private http: HttpClient) { }
}
