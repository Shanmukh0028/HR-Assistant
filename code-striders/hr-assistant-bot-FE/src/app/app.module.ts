import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { MatInputModule} from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTabsModule } from '@angular/material/tabs';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { QueryListComponent } from './services/query.list.component';
import { ChatbotDialogComponent } from './components/chatbot/chatbot-dialog/chatbot-dialog.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NotificationBellComponent } from './components/notification/notification-bell.component';
import { NotificationPaneComponent } from './components/notification/notification-pane/notification-pane.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    QueryListComponent,
    ChatbotDialogComponent,
    DashboardComponent,
    NotificationBellComponent,
    NotificationPaneComponent
    ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatTabsModule,
    MatButtonModule,
    MatTableModule,
    MatMenuModule,
    MatIconModule,
    MatListModule,
    MatPaginatorModule,
    MatCardModule,
    MatToolbarModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
