// src/app/models/query-response.model.ts
import { Query } from './query.model';

export interface QueryResponse {
  status_code: number;
  status_msg: string;
  error: null | string; // Assuming error can be a string or null
  response: Query[]; // This will be an array of Query objects
}