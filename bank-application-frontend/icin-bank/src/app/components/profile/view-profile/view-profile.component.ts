import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/Domain/User';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService) { }

  user : User | undefined

  ngOnInit(): void {
    this.user = this.tokenStorage.getUserDetails();

  }

}
