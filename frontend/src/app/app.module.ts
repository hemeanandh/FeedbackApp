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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AddfeedbackComponent,
    ViewfeedbackComponent,
    PreparereportComponent,
    MyprofileComponent
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
      
    ])
    
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
