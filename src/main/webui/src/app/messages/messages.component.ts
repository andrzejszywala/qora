import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'q-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit {

  @Input()
  messages: string[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
