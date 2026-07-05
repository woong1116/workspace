import type { MenuItem } from "./menu";

export interface CartItem {
  menuItem: MenuItem;
  quantity: number;
}

export interface CartSummary {
  itemCount: number;
  subtotal: number;
  discount: number;
  total: number;
}
