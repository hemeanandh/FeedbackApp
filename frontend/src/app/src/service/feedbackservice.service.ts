import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FeedbackAttr } from 'src/app/Model/FeedbackAttr';
import { FeedbackEntries } from 'src/app/Model/FeedbackEntries';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackserviceService {
  feedbackattr:FeedbackAttr[] = [];
  
  constructor( private http: HttpClient) { }


  submitFeedback(feedbackEntries: FeedbackEntries[]): Observable<any> {
      return this.http.post('http://localhost:8080/api/v1/submitFeedback',feedbackEntries,{withCredentials:true ,observe:'body',responseType:'text'})
  }


  getFeedbackAttrs(srcDesignId:Number,destDesignId:Number) {
    let params = new HttpParams();
    params.set("giverDesignation",srcDesignId+'');
    params.set("receiverDesignation",srcDesignId+'');
    const data = {
      giverDesignation:srcDesignId+'',
      receiverDesignation:destDesignId+''
    }
    //Get the attributes that the feedbackgiver has access for the receiver
    return this.http.get("http://localhost:8080/api/v1/eligibleFeedbackAttrs",
   {
    withCredentials:true,
     params:data})
    //return this.feedbackattr;
  }
}
