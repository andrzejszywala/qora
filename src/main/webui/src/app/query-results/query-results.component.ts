import { Component, Input, OnInit } from '@angular/core';
import { ZbiorDanych } from '../zbiordanych';

@Component({
  selector: 'q-query-results',
  templateUrl: './query-results.component.html',
  styleUrls: ['./query-results.component.scss']
})
export class QueryResultsComponent implements OnInit {

  @Input()
  dataSet: ZbiorDanych[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
