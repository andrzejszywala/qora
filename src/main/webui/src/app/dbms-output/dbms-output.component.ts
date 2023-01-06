import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'p-dbms-output',
  templateUrl: './dbms-output.component.html',
  styleUrls: ['./dbms-output.component.scss']
})
export class DbmsOutputComponent implements OnInit {

  pollingFrequency: number = 5;
  displayBufferSizeDialog: boolean = false;
  currentBufferSize: number = 20000;
  bufferSize: number = this.currentBufferSize;


  constructor() { }

  ngOnInit(): void {
  }

  showBufferSizeDialog() {
    this.bufferSize = this.currentBufferSize;
    this.displayBufferSizeDialog = true;
  }

  changeBufferSize() {
    this.currentBufferSize = this.bufferSize;
    this.displayBufferSizeDialog = false;
  }

  printOutput() {


  }
}
