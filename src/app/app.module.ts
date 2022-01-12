import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenubarModule } from 'primeng/menubar';
import { TreeModule } from 'primeng/tree';
import { HttpClientModule } from '@angular/common/http';
import { NodeService } from './nodeservice';
import {VirtualScrollerModule} from 'primeng/virtualscroller';
import { ScrollingModule } from '@angular/cdk/scrolling';
import {PanelModule} from 'primeng/panel';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import {TableModule} from 'primeng/table';
import { ProductService } from './productservice';
import {TabViewModule} from 'primeng/tabview';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MenubarModule,
    TreeModule,
    VirtualScrollerModule,
    HttpClientModule,
    ScrollingModule,
    PanelModule,
    ScrollPanelModule,
    TableModule,
    TabViewModule
  ],
  providers: [NodeService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
