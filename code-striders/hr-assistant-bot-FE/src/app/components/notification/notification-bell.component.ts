import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-notification-bell',
  templateUrl: './notification-bell.component.html',
  styleUrls: ['./notification-bell.component.scss'],
})
export class NotificationBellComponent implements OnInit {

  unreadCount$ = this.notificationService.unreadCount$;

  constructor(private notificationService: NotificationService) {}

  ngOnInit(): void {
    // Fetch notifications when the component initializes
    this.notificationService.fetchNotifications(1);
  }
}
