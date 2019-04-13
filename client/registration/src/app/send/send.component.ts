import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-send',
  templateUrl: './send.component.html',
  styleUrls: ['./send.component.scss'],
})
export class SendComponent implements OnInit {

  post = {
    name:  '',
    details:  '',
    date:  '',
    address: '',
    contact: ''
  };

  constructor() { }

  ngOnInit() {}

}
