import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router'
import { ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AddfeedbackComponent } from './addfeedback/addfeedback.component';
import { ViewfeedbackComponent } from './viewfeedback/viewfeedback.component';
import { PreparereportComponent } from './preparereport/preparereport.component';
import { MyprofileComponent } from './myprofile/myprofile.component';
import { ViewfeedbacksComponent } from './viewfeedbacks/viewfeedbacks.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddfeedbackComponent,
    ViewfeedbackComponent,
    PreparereportComponent,
    MyprofileComponent,
    ViewfeedbacksComponent
  ],
  imports: [
    BrowserModule,
     ReactiveFormsModule,
     HttpClientModule,

    RouterModule.forRoot([
   
      {
        path: 'add',
        component: AddfeedbackComponent,
        
      },
      { path: 'login', component: LoginComponent },

      { path: 'profile', component: MyprofileComponent },

      { path: 'view', component: ViewfeedbackComponent },
      
    ])
    
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
