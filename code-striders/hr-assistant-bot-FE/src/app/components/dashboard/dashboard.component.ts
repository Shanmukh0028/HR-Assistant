import { Component, HostListener, ViewChild, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  @ViewChild('sidenav') sidenav!: MatSidenav; // Binding to the sidenav element

  query: string = '';
  response: string | null = null;

  showNotifications: boolean;
  notifications = [
    { message: 'New message from the HR bot.', read: false },
    { message: 'Your request has been processed.', read: false },
    { message: 'There is a new update available.', read: false },
  ];

  unreadCount = this.notifications.filter(n => !n.read).length;

  constructor(private router: Router) {
    this.showNotifications = false;
  }

  // @HostListener('document:click', ['$event'])
  // onClickOutside(event: Event): void {
  //   const targetElement = event.target as HTMLElement;

  //   // Close the sidenav if it is open and the click is outside the sidenav
  //   if (
  //     this.sidenav.opened &&
  //     !targetElement.closest('.sidenav') &&
  //     !targetElement.closest('.mat-icon-button') // Exclude the button for toggling the sidenav
  //   ) {
  //     this.sidenav.close();
  //   }
  // }

  askQuery() {
    // Implement your query logic here
  }

  toggleNotifications() {
    this.showNotifications = !this.showNotifications;
  }

  navigateTo(route: string): void {
    this.router.navigate([route]);
  }

  logout() {
    // Implement your logout logic here
    this.router.navigate(['/login']);
  }
}