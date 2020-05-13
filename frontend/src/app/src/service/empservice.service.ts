import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Employee} from '../../Model/Employee';
import { template } from '@angular/core/src/render3';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class EmpserviceService {

  //employees:Employee[] = [];
  constructor(
    private http: HttpClient
  ) { }


  getUserDetails(id:Number) :Observable<any>{
  
    
     return this.http.get("http://localhost:8080/api/v1/users/"+id,{withCredentials:true});
  }

  getMyProjectMemebers(id:Number) : Observable<any> {
    return this.http.get("http://localhost:8080/api/v1/getMyProjectMembers?userid="+id,{withCredentials:true});
  }

  getLoginService(Username:String,pwd:String) : Observable<any>{

    let obj :any= {
      "id" : "1",
      "password" : "user1"}
    return this.http.post('http://localhost:8080/login',obj,{withCredentials:true ,observe:'body',responseType:'json'});

  }
}
