import { Component, Input, OnInit, OnChanges } from '@angular/core';
import { QueryService } from '../services/query.service';
import { Query } from '../services/query.model'; // Import the Query interface
import { QueryResponse } from '../services/query-response.model'; // Import the QueryResponse interface

@Component({
  selector: 'app-query-list',
  templateUrl: './query.list.component.html',
  styleUrls: ['./query.list.component.scss'],
})
export class QueryListComponent implements OnInit, OnChanges {
  @Input() queryType: string = 'open'; // Default to 'open'
  queries: Query[] = []; // Use the Query interface for type safety
  
  constructor(private queryService: QueryService) {}

  ngOnInit() {
    this.loadQueries(); // Load queries based on the input
  }

  ngOnChanges() {
    this.loadQueries(); // Reload queries when queryType changes
  }

  loadQueries() {
    const employeeId = sessionStorage.getItem('employeeId'); // Retrieve employeeId from session storage

    if (!employeeId) {
      console.error('No employeeId found in session storage');
      return; // Exit if no employeeId is found
    }

    if (this.queryType === 'open') {
      this.queryService.getOpenQueries(employeeId).subscribe((data: QueryResponse) => { // Use QueryResponse interface
        if (data && data.status_code === 200) {
          this.queries = data.response; // Directly assign response to queries
        } else {
          console.error('Failed to load open queries:', data.status_msg);
          this.queries = []; // Reset queries if the response is not successful
        }
      });
    } else if (this.queryType === 'completed') {
      this.queryService.getCompletedQueries(employeeId).subscribe((data: QueryResponse) => { // Use QueryResponse interface
        if (data && data.status_code === 200) {
          this.queries = data.response; // Directly assign response to queries
        } else {
          console.error('Failed to load completed queries:', data.status_msg);
          this.queries = []; // Reset queries if the response is not successful
        }
      });
    }
  }

  onQueryClick(query: Query) {
    // Navigate to the query detail page
    // this.router.navigate(['/query-detail', query.id]);
  }
}