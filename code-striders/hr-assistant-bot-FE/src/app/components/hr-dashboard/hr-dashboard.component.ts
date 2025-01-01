import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Import Router
import { QueryService } from '../../services/query.service';

@Component({
  selector: 'app-hr-dashboard',
  templateUrl: './hr-dashboard.component.html',
  styleUrls: ['./hr-dashboard.component.scss'],
})
export class HrDashboardComponent {
  queries: any[] = [];

  constructor(private queryService: QueryService, private router: Router) { // Inject Router
    this.queryService.getPendingQueries().subscribe((data) => {
      this.queries = data;
    });
  }

  submitAnswer(query: any) {
    this.queryService.submitAnswer(query).subscribe(() => {
      alert('Answer submitted!');
      this.queries = this.queries.filter((q) => q.id !== query.id);
    });
  }

  logout() {
    // Logic to clear user session or token if necessary
    this.router.navigate(['/login']); // Navigate to the login page
  }
}