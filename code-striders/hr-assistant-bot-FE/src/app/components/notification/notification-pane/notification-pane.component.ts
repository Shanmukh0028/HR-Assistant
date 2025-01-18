import { Component } from '@angular/core';
import { NotificationService } from '../../../services/notification.service';

@Component({
  selector: 'app-notification-pane',
  templateUrl: './notification-pane.component.html',
  styleUrls: ['./notification-pane.component.scss'],
})
export class NotificationPaneComponent {
  notifications = this.notificationService.notifications$;

  constructor(private notificationService: NotificationService) {}

  markAsRead(notificationId: BigInteger): void {
    this.notificationService.markAsRead(notificationId);
  }
}
