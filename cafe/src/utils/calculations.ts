import type { CartItem, CartSummary } from "@/types";

export function calculateSubtotal(items: Pick<CartItem, "menuItem" | "quantity">[]): number {
  return items.reduce((sum, item) => sum + item.menuItem.price * item.quantity, 0);
}

export function calculateDiscountAmount(subtotal: number, discountRate: number): number {
  if (discountRate <= 0) return 0;
  return Math.round(subtotal * discountRate);
}

export function calculateCartSummary(items: CartItem[], discountRate: number): CartSummary {
  const itemCount = items.reduce((sum, item) => sum + item.quantity, 0);
  const subtotal = calculateSubtotal(items);
  const discount = calculateDiscountAmount(subtotal, discountRate);
  const total = subtotal - discount;

  return { itemCount, subtotal, discount, total };
}
