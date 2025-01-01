// src/app/models/login-response.model.ts
export interface LoginResponse {
    userId: string; // or number, depending on your API
    role: 'EMPLOYEE' | 'HR'; // or any other roles you might have
  }