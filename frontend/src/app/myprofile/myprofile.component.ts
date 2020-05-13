import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../Model/Employee';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent implements OnInit {
   emp:Employee;
   userDetails:Employee;
  constructor(private route: Router) {
    
    this.userDetails = this.route.getCurrentNavigation().extras.state.dat;  }

  ngOnInit() {
  }

  routerToAddFeedbackComponent() {
    console.log(this.userDetails);
    this.route.navigate(['/add'],{state:{dat:this.userDetails} } ); 
  }

  routerToViewFeedbackComponent() {
    this.route.navigate(['/view'],{state:{dat:this.userDetails} } ); 
  }

}
