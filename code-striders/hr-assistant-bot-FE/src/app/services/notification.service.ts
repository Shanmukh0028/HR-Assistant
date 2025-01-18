import { computed, Injectable, signal } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
// import { environment } from '../../environments/environment';

export interface Notification{
  id: BigInteger,
  userId: BigInteger,
  message: string,
  read: boolean
}

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  
  public notifications$ = signal<Notification[]>([]);

  public unreadCount$ = computed(() => this.notifications$().filter((n) => !n.read).length);

  private apiUrl = `http://localhost:8080/users/notifications`; // Adjust API endpoint

  constructor(private http: HttpClient) {}

  // Fetch notifications from the backend
  fetchNotifications(userId: number): void {
    this.http.get<Notification[]>(`${this.apiUrl}?userId=${userId}`).subscribe((notifications) => {
      this.notifications$.set(notifications);
      // this.notificationsSubject.next(notifications);
      // this.updateUnreadCount(notifications);
    });
  }

  // Update unread count
  // private updateUnreadCount(notifications: any[]): void {
  //   const unreadCount = notifications.filter((n) => !n.read).length;
  //   this.unreadCountSubject.next(unreadCount);
  // }

  // Mark notification as read
  markAsRead(notificationId: BigInteger): void {
    this.http.post(`${this.apiUrl}/${notificationId}/read`, {}).subscribe(() => {
      const updatedNotifications = this.notifications$().map((notification) =>
        notification.id === notificationId ? { ...notification, read: true } : notification
      );
      this.notifications$.set(updatedNotifications);
    });
  }

  // Push notifications using WebSocket or SSE
  // addNotification(newNotification: any): void {
  //   const updatedNotifications = [newNotification, ...this.notificationsSubject.value];
  //   this.notificationsSubject.next(updatedNotifications);
  //   this.updateUnreadCount(updatedNotifications);
  // }
}
