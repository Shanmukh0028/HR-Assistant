import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { LoginResponse } from '../../components/login/login-response.model'; // Adjust the path as necessary

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.username, this.password).pipe(
      catchError(error => {
        this.errorMessage = 'Login failed. Please check your credentials.';
        console.error('Login error:', error);
        return of(null); // Return null in case of error
      })
    ).subscribe((response: LoginResponse | null) => { // Specify the response type
      // Check if response is valid and an object
      if (response && typeof response === 'object') {
        const userId = response.userId; // Safely access userId
        const role = response.role; // Safely access role

        // Check for null or undefined userId and role
        if (userId == null || role == null) {
          this.errorMessage = 'Invalid response from the server. Please try again.';
          return; // Exit the method if either value is null
        }

        // Proceed based on the role
        if (role === 'EMPLOYEE') {
          sessionStorage.setItem('employeeId', userId); // Store employeeId in session storage
          this.router.navigate(['/chatbot']);
        } else if (role === 'HR') {
          sessionStorage.setItem('hrId', userId); // Store hrId in session storage
          this.router.navigate(['/hr-dashboard']);
        } else {
          this.errorMessage = 'Unknown role. Access denied.';
        }
      } else {
        this.errorMessage = 'Login failed. Please check your credentials.';
      }
    });
  }
}