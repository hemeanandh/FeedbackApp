import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-addfeedback',
  templateUrl: './addfeedback.component.html',
  styleUrls: ['./addfeedback.component.css']
})
export class AddfeedbackComponent implements OnInit {

  constructor( private route: ActivatedRoute) { 
    console.log('test');
  }

  ngOnInit() {
    console.log('test');
    this.route.paramMap.subscribe(params => {
      console.log(params.get('productId'));
    });
  }

}
