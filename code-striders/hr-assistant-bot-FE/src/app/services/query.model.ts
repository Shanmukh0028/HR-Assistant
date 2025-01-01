// src/app/models/query.model.ts
export interface Query {
    Id: number; // Assuming this is a number, adjust if necessary
    title: string;
    status: string;
    createdAt: string; // You might consider using Date type if you're going to manipulate dates
    resolvedAt: string | null; // Can be null if not resolved
    id: number; // Assuming this is the same as Id, consider renaming if necessary
  }