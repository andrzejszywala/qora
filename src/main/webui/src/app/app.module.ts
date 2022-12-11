import { ScrollingModule } from '@angular/cdk/scrolling';
import { registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import en from '@angular/common/locales/en';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { ProductService } from './productservice';
import { SqlResource } from './business/sql/boundary/sql.resource';
import { MessagesComponent } from './messages/messages.component';
import { QueryResultsComponent } from './query-results/query-results.component';
import { ExplainPlanComponent } from './explain-plan/explain-plan.component';
import {CalendarModule} from 'primeng/calendar';
import {SplitterModule} from 'primeng/splitter';
import {MenubarModule} from 'primeng/menubar';
import {PanelModule} from 'primeng/panel';
import {TabViewModule} from 'primeng/tabview';
import { MonacoEditorModule } from 'ngx-monaco-editor';
import {ToolbarModule} from 'primeng/toolbar';
import {DividerModule} from 'primeng/divider';
import {TreeModule} from 'primeng/tree';
import {TableModule} from 'primeng/table';
import {ScrollPanelModule} from 'primeng/scrollpanel';
import { DbTreeComponent } from './db-tree/db-tree.component';
import { SessionsResource } from './business/oracle/sessions/boundary/sessions.resource';
import { UsersResource } from './business/oracle/users/boundary/users.resource';
import { ProfilesResource } from './business/oracle/profiles/boundary/profiles.resource';
import { RolesResource } from './business/oracle/roles/boundary/roles.resource';
import { TablesResource } from './business/oracle/tables/boundary/tables.resource';
import { IndexesResource } from './business/oracle/indexes/boundary/indexes.resource';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    QueryResultsComponent,
    ExplainPlanComponent, 
    DbTreeComponent
  ],
  imports: [
    ScrollPanelModule,
    TableModule,
    DividerModule,
    ToolbarModule,
    TabViewModule,
    PanelModule,
    MenubarModule,
    SplitterModule,
    CalendarModule,
    TreeModule,
    BrowserModule,
    BrowserAnimationsModule,
    //AppRoutingModule,
    HttpClientModule,
    ScrollingModule,
    FormsModule,
    MonacoEditorModule.forRoot()
  ],
  providers: [SqlResource, SessionsResource, ProductService, UsersResource, ProfilesResource, RolesResource,
  	TablesResource, IndexesResource],
  bootstrap: [AppComponent]
})
export class AppModule { }
