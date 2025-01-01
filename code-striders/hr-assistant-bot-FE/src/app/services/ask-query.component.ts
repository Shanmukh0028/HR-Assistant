import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QueryService } from './query.service';

@Component({
  selector: 'app-ask-query',
  template: `
    <div class="ask-query-container">
      <mat-card>
        <mat-card-header>
          <mat-card-title>Ask a Question</mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <form [formGroup]="queryForm" (ngSubmit)="onSubmit()">
            <mat-form-field appearance="outline">
              <mat-label>Your Question</mat-label>
              <textarea 
                matInput
                formControlName="question"
                rows="4"
                placeholder="Type your question here...">
              </textarea>
              <mat-error *ngIf="queryForm.get('question')?.hasError('required')">
                Question is required
              </mat-error>
            </mat-form-field>
            <button 
              mat-raised-button 
              color="primary" 
              type="submit"
              [disabled]="queryForm.invalid || isSubmitting">
              Submit Question
            </button>
          </form>
        </mat-card-content>
      </mat-card>
    </div>
  `,
  styles: [`
    .ask-query-container {
      position: fixed;
      right: 20px;
      top: 20px;
      width: 300px;
      z-index: 1000;
    }

    mat-card {
      padding: 16px;
    }

    mat-card-content {
      margin-top: 16px;
    }

    form {
      display: flex;
      flex-direction: column;
      gap: 16px;
    }

    mat-form-field {
      width: 100%;
    }
  `]
})
export class AskQueryComponent {
  queryForm: FormGroup;
  isSubmitting = false;

  constructor(
    private fb: FormBuilder,
    private queryService: QueryService
  ) {
    this.queryForm = this.fb.group({
      question: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.queryForm.valid && !this.isSubmitting) {
      this.isSubmitting = true;
      const question = this.queryForm.get('question')?.value;

      this.queryService.askQuery(question).subscribe({
        next: (response) => {
          this.queryForm.reset();
          this.isSubmitting = false;
          // You can add a success notification here
        },
        error: (error) => {
          console.error('Error submitting question:', error);
          this.isSubmitting = false;
          // You can add an error notification here
        }
      });
    }
  }
}