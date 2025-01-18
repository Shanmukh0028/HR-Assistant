import { Component, ViewEncapsulation } from '@angular/core';
import { QueryService } from '../../services/query.service';
import { Router } from '@angular/router'; 


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
    query: string = '';
    response: string | null = null;
  
    showNotifications: boolean;
    notifications = [
      { message: 'New message from the HR bot.', read: false },
      { message: 'Your request has been processed.', read: false },
      { message: 'There is a new update available.', read: false },
    ];

  unreadCount = this.notifications.filter(n=>!n.read).length;
    constructor(private queryService: QueryService, private router: Router) {
      this.showNotifications = false;
    } // Inject Router
  
    askQuery() {
      this.queryService.askQuery(this.query).subscribe((res) => {
        this.response = res.answer;
      });
    }

    toggleNotifications(){
      this.showNotifications = !this.showNotifications;
    }
  
  
    logout() {
      // Logic to clear user session or token if necessary
      this.router.navigate(['/login']); // Navigate to the login page
    }
}
