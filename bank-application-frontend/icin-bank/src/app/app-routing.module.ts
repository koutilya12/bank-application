import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AccountComponent } from './components/account/account.component';
import { ChequeBookComponent } from './components/cheque-book/cheque-book.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { TransactComponent } from './components/transact/transact.component';

const routes: Routes = [
  {path: 'home', component: AppComponent},
  // {path: 'profile', component: ProfileComponent},
  // {path: 'account', component: AccountComponent},
  // {path: 'transact', component: TransactComponent},
  // {path: 'cheque-book', component: ChequeBookComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
