import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import {EmpserviceService} from '../src/service/empservice.service'

import {FeedbackserviceService} from '../src/service/feedbackservice.service'
import { Employee } from '../Model/Employee';
import { FeedbackAttr } from '../Model/FeedbackAttr';

@Component({
  selector: 'app-addfeedback',
  templateUrl: './addfeedback.component.html',
  styleUrls: ['./addfeedback.component.css']
})

export class AddfeedbackComponent implements OnInit {

  
  
  attr : string[]=['Competence','Productivity','resourcefullness'];
  myFormGroup:FormGroup;
  employees: Employee[] = [];
  userDetails:Employee;
  feedbackAttr: FeedbackAttr[] = [];
  val:boolean=false;
  
    constructor( private route: Router, private empservice:EmpserviceService,
      private feedbackservice:FeedbackserviceService) { 
     
        this.userDetails = this.route.getCurrentNavigation().extras.state.dat;
        console.log(this.userDetails)
  }

  getFormGroupWithAttr() {
    let group={}   ;
    this.feedbackAttr.map(attrItem => {
      //group[attrItem.attr_name] = new FormControl('');
      this.myFormGroup.addControl(attrItem.attr_name, new FormControl(''));
    
    });
  }

  getFormGroup() {
    let group={}   ;
   
    group['employee'] = new FormControl('');
    

   this.myFormGroup =  new FormGroup(group);

   
   console.log(this.myFormGroup);

  }


  
  ngOnInit() {
    console.log('test');
   
    //console.log(this.route.getCurrentNavigation());
    this.empservice.getMyProjectMemebers(this.userDetails.empId).subscribe(data => {
      this.employees = data;
      this.getFormGroup();
     
    });

    
   
   
  }

  getFeedbackAttrs():void {
    this.feedbackservice.getFeedbackAttrs(this.userDetails.designationId,2).subscribe(  (data : any) => {
    
      data._embedded.feedbackAttrs.map(item => {
        this.feedbackAttr.push(item);
  
      });

     
      this.val = true;
      
      });
  }

  onSubmit() {
   
   Object.keys(this.myFormGroup.controls).forEach(i => {
    console.log(i+ " : " +this.myFormGroup.get(i).value);
   });

  // this.myFormGroup.get('employee').valueChanges.subscribe(val => {
  //   console.log(val)
  // })
  }

}
