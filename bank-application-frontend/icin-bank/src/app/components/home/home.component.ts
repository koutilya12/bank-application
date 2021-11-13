import {MediaMatcher} from '@angular/cdk/layout';
import {ChangeDetectorRef, Component, OnDestroy,OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit,OnDestroy {
  selectedNav: String = 'home';


  ngOnInit(): void {
  }

  mobileQuery: MediaQueryList;



  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher, private tokenStorage: TokenStorageService, private router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }
  changeTab(tab : String): void {
    this.selectedNav = tab;
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  logout(): void {
    this.tokenStorage.signOut();
    this.router.navigate(['/login']);
  }


  shouldRun = /(^|.)(stackblitz|webcontainer).(io|com)$/.test(window.location.host);

}
