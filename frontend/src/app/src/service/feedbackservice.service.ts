import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FeedbackAttr } from 'src/app/Model/FeedbackAttr';

@Injectable({
  providedIn: 'root'
})
export class FeedbackserviceService {
  feedbackattr:FeedbackAttr[] = [];
  
  constructor( private http: HttpClient) { }


  getFeedbackAttrs(srcDesignId:Number,destDesignId:Number) {
    let params = new HttpParams();
    params.append("giverDesignation",srcDesignId+'');
    params.append("receiverDesignation",srcDesignId+'');
    const data = {
      src:srcDesignId,
      dest:destDesignId
    }
    //Get the attributes that the feedbackgiver has access for the receiver
    return this.http.get("http://localhost:8080/api/v1/eligibleFeedbackAttrs",{params:params} )
    //return this.feedbackattr;
  }
}
