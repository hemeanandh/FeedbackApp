import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import {EmpserviceService} from '../src/service/empservice.service'

import {FeedbackserviceService} from '../src/service/feedbackservice.service'
import { Employee } from '../Model/Employee';
import { FeedbackAttr } from '../Model/FeedbackAttr';


import { FeedbackEntries } from '../Model/FeedbackEntries';

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
  empval:boolean=false; attrval:boolean=false;
  
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

      console.log(this.myFormGroup.get('employee'))
      this.myFormGroup.get('employee').valueChanges.subscribe(data => {
       this.removeFeedbackFormControlElements();
       this.getFeedbackAttrs(data);
       this.feedbackAttr = [];this.attrval = false; 
      })
     
      this.empval = true;
    });

   
    
   
   
  }

  removeFeedbackFormControlElements() {
    this.feedbackAttr.map(item => {
      this.myFormGroup.removeControl(item.attr_name);

    });
  }

  getFeedbackAttrs(targetEmpId:Number):void {

   let targetEmployeeObj =  this.employees.find(item => item.empId == targetEmpId);
    this.feedbackservice.getFeedbackAttrs(this.userDetails.designationId,targetEmployeeObj.designationId).subscribe(  (data : any) => {
    
      data.map(item => {
        this.feedbackAttr.push(item);
        
      });

      this.feedbackAttr.map(item => {
        this.myFormGroup.addControl(item.attr_name,new FormControl(''));
  
      });
     
      this.attrval = true;
      
      });
  }

  onSubmit() {
    let entriesList:FeedbackEntries[] = [];
   
   let isValid = this.validateFields(entriesList);

   if(isValid) {
   this.feedbackservice.submitFeedback(entriesList).subscribe(val => {
     if(val == 'success') {
       alert('feedback succesfully submitted')
     } else {
       alert('some issue occurred')
     }
   })
  }
  // this.myFormGroup.get('employee').valueChanges.subscribe(val => {
  //   console.log(val)
  // })
  }


  private validateFields(entriesList: FeedbackEntries[]) : boolean{
    let isValid = false;
    let keysArray: string[] = Object.keys(this.myFormGroup.controls);
    if(keysArray) {
    keysArray.forEach(i => {
      let myattr: FeedbackAttr = this.feedbackAttr.find(j => j.attr_name == i);
      if (myattr) {
        isValid = true;
        if (this.myFormGroup.get(i).value) {
          let rating = this.myFormGroup.get(i).value;
          let feedbackEntries: FeedbackEntries = new FeedbackEntries(this.userDetails.empId, this.employees.find(i => i.empId == this.myFormGroup.get('employee').value).empId, myattr.attr_id, this.myFormGroup.get(i).value);
          console.log(feedbackEntries);
          entriesList.push(feedbackEntries);
        }
        else {
          alert('please rate : ' + this.myFormGroup.get(i));
          isValid =  false;
          return;
        }
      }
    });
    return isValid;
  } else {
    alert('please choose employee and rate his/her skill')
    return false;
  }
  }
}
