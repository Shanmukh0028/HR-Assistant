import { Component } from '@angular/core';
import { Router } from '@angular/router'; // Import Router
import { QueryService } from '../../services/query.service';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.scss'],
})
export class ChatbotComponent {
  query: string = '';
  response: string | null = null;

  constructor(private queryService: QueryService, private router: Router) {} // Inject Router

  askQuery() {
    this.queryService.askQuery(this.query).subscribe((res) => {
      this.response = res.answer;
    });
  }

  logout() {
    // Logic to clear user session or token if necessary
    this.router.navigate(['/login']); // Navigate to the login page
  }
}