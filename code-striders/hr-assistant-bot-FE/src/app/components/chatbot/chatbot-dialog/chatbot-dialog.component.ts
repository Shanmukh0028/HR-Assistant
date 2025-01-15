import { Component } from '@angular/core';

@Component({
  selector: 'app-chatbot-dialog',
  templateUrl: './chatbot-dialog.component.html',
  styleUrls: ['./chatbot-dialog.component.scss'],
})
export class ChatbotDialogComponent {
  isChatOpen = false;
  userMessage = '';

  toggleChat(): void {
    this.isChatOpen = !this.isChatOpen;
  }

  sendMessage(): void {
    if (this.userMessage.trim()) {
      console.log('User message:', this.userMessage);
      this.userMessage = '';
    }
  }
}
