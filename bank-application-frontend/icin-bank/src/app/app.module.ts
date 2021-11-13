import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTabsModule} from '@angular/material/tabs';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatFormFieldControl, MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';










import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileComponent } from './components/profile/profile.component';
import { AccountComponent } from './components/account/account.component';
import { TransferComponent } from './components/transact/transfer/transfer.component';
import { DepositComponent } from './components/transact/deposit/deposit.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { LogoutComponent } from './components/logout/logout.component';
import { TransactComponent } from './components/transact/transact.component';
import { WithdrawComponent } from './components/transact/withdraw/withdraw.component';
import { ViewProfileComponent } from './components/profile/view-profile/view-profile.component';
import { UpdateContactDetailsComponent } from './components/profile/update-contact-details/update-contact-details.component';
import { ChangePasswordComponent } from './components/profile/change-password/change-password.component';
import { CreateAccountComponent } from './components/account/create-account/create-account.component';
import { ViewAccountComponent } from './components/account/view-account/view-account.component';
import { TransactionHistoryComponent } from './components/account/transaction-history/transaction-history.component';
import { ChequeBookComponent } from './components/cheque-book/cheque-book.component';
import { CreateChequeBookComponent } from './components/cheque-book/create-cheque-book/create-cheque-book.component';
import { GetChequeBookComponent } from './components/cheque-book/get-cheque-book/get-cheque-book.component';
import { authInterceptorProviders } from './services/httpInterceptor';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfileComponent,
    AccountComponent,
    TransferComponent,
    DepositComponent,
    LoginComponent,
    RegisterComponent,
    LogoutComponent,
    TransactComponent,
    WithdrawComponent,
    ViewProfileComponent,
    UpdateContactDetailsComponent,
    ChangePasswordComponent,
    CreateAccountComponent,
    ViewAccountComponent,
    TransactionHistoryComponent,
    ChequeBookComponent,
    CreateChequeBookComponent,
    GetChequeBookComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatTabsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
