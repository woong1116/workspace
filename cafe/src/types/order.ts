export type PaymentMethod = "card" | "cash";

export interface OrderItem {
  menuItemId: string;
  name: string;
  price: number;
  quantity: number;
  lineTotal: number;
}

export interface Order {
  id: string;
  orderNumber: string;
  items: OrderItem[];
  subtotal: number;
  discount: number;
  total: number;
  paymentMethod: PaymentMethod;
  createdAt: string;
}
