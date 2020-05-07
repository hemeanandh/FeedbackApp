import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import {Router, NavigationExtras} from '@angular/router';
import {EmpserviceService} from '../src/service/empservice.service'
import { Employee } from '../Model/Employee';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  loginform = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  
  
  submitted:boolean = false;
  constructor(
    private router: Router,
    private empservice:EmpserviceService
  ) { }

  ngOnInit() {

    // this.loginform.valueChanges.subscribe(val => {
    //   alert(val);
    // });
    // this.loginform.get('username').valueChanges.subscribe(val => {
    //   alert(val);
    // });
  }

  isUserNameValid( user:String) :Boolean{
    if(user.length > 0) {
      return true;
    }
    return false;
  }

  isPasswordValid( pwd:String) :Boolean{
    if(pwd.length > 0 && pwd) {
      return true;
    }
    return false;
  }
  onSubmit() {

   let user:String =  this.loginform.get('username').value;
   let pwd:String = this.loginform.get('password').value;
   
   let emp:Employee;


   if(this.isUserNameValid(user) && this.isPasswordValid(pwd)) {
    this.empservice.getLoginService(user,pwd).subscribe( (data:any) => {
      let userId = data.id;
      if(userId) {
       this.empservice.getUserDetails(userId).subscribe(  (data : any) => {
    
        console.log(data)
        emp = data;
       
        const routerData:NavigationExtras  = {
          state : emp
          }
            this.router.navigate(['/add'],{state:{dat:emp} } ); 
          });

        }
        
        });
        
      }
      

   
   }


    
  
  }

