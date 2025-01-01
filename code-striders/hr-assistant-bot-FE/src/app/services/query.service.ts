import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { QueryResponse } from './query-response.model';

@Injectable({
  providedIn: 'root',
})
export class QueryService {
  constructor(private http: HttpClient) {}

  // Method to ask a query
  askQuery(question: string): Observable<any> {
    return this.http.post<any>('http://localhost:8080/query/ask', { question });
  }

  getOpenQueries(employeeId: string): Observable<QueryResponse> { // Replace 'any' with the appropriate type
    return this.http.get<QueryResponse>(`http://localhost:8080/query/open?employeeId=${employeeId}`);
  }

  getCompletedQueries(employeeId: string): Observable<QueryResponse> { // Replace 'any' with the appropriate type
    return this.http.get<QueryResponse>(`http://localhost:8080/query/closed?employeeId=${employeeId}`);
  }
  // Method to submit an answer to a query
  submitAnswer(query: any): Observable<void> {
    return this.http.post<void>('http://localhost:8080/query/answer', query);
  }

  // Optional: Method to fetch pending queries if needed
  getPendingQueries(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/query/pending'); // Replace with your actual API endpoint
  }
}