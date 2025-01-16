import { Component } from '@angular/core';

@Component({
  selector: 'app-chatbot-dialog',
  templateUrl: './chatbot-dialog.component.html',
  styleUrls: ['./chatbot-dialog.component.scss'],
})
export class ChatbotDialogComponent {
  isChatOpen = false;
  userMessage = '';
  chatMessages: {text: string,sender: "customer"|"bot"}[] = [];

  ngOnInit(){
    this.chatMessages.push({text: "Hi, How Can I help you?",sender:"bot"})
  }

  toggleChat(): void {
    this.isChatOpen = !this.isChatOpen;
  }

  sendMessage(): void {
    if (this.userMessage.trim()) {
      this.chatMessages.push({text:this.userMessage,sender:"customer"});
      console.log('User message:', this.userMessage);
      this.userMessage = '';

      setTimeout(()=>{
        this.chatMessages.push({text: "Sure,I am happy to help you",sender:"bot"});
      },2000)
    }
  }
}
