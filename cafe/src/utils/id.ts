import { v4 as uuidv4 } from "uuid";

export function generateId(): string {
  return uuidv4();
}

export function generateOrderNumber(date: Date = new Date()): string {
  const yyyymmdd = date
    .toISOString()
    .slice(0, 10)
    .replace(/-/g, "");
  const uniquePart = date.getTime().toString().slice(-6);
  return `ORD-${yyyymmdd}-${uniquePart}`;
}
