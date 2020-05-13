import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../Model/Employee';
@Component({
  selector: 'app-viewfeedback',
  templateUrl: './viewfeedback.component.html',
  styleUrls: ['./viewfeedback.component.css']
})
export class ViewfeedbackComponent implements OnInit {
  userDetails:Employee;
  constructor(
    private route: Router
  ) { 
    this.userDetails = this.route.getCurrentNavigation().extras.state.dat;
    console.log(this.userDetails)
  }

  ngOnInit() {
  }

}
