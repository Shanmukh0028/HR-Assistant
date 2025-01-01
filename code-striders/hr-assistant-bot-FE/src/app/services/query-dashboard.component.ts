import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AskQueryDialogComponent } from './ask-query-dialog.component';
import { QueryListComponent } from './query.list.component'; // Import QueryListComponent

@Component({
  selector: 'app-query-dashboard',
  template: `
    <div class="query-container">
      <mat-card>
        <mat-card-header class="header">
          <div class="tabs">
            <button mat-button 
                    [class.active]="activeTab === 'open'"
                    (click)="setActiveTab('open')">
              Open Queries
            </button>
            <button mat-button 
                    [class.active]="activeTab === 'completed'"
                    (click)="setActiveTab('completed')">
              Completed Queries
            </button>
          </div>
          <button mat-raised-button 
                  color="primary" 
                  class="ask-button"
                  (click)="openAskQueryDialog()">
            Ask a Question
          </button>
        </mat-card-header>
        
        <mat-card-content>
          <app-query-list #queryList [queryType]="activeTab"></app-query-list>
        </mat-card-content>
      </mat-card>
    </div>
  `,
  styles: [`
    .query-container {
      padding: 20px;
      max-width: 1200px;
      margin: 0 auto;
    }
    
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
      width: 100%;
    }
    
    .tabs {
      display: flex;
      gap: 10px;
    }
    
    .active {
      background-color: rgba(0, 0, 0, 0.04);
      font-weight: bold;
    }
    
    .ask-button {
      margin-left: auto;
    }
  `]
})
export class QueryDashboardComponent {
  activeTab: 'open' | 'completed' = 'open';

  @ViewChild('queryList') queryList!: QueryListComponent; // Get reference to QueryListComponent

  constructor(private dialog: MatDialog) {}

  setActiveTab(tab: 'open' | 'completed') {
    this.activeTab = tab;
    this.queryList.loadQueries(); // Call loadQueries method to refresh data
  }

  openAskQueryDialog() {
    const dialogRef = this.dialog.open(AskQueryDialogComponent, {
      width: '500px'}}