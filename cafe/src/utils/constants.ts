import type { PaymentMethod, StoreSettings } from "@/types";

export const STORAGE_KEYS = {
  MENU: "cafe-pos:menu",
  CART: "cafe-pos:cart",
  ORDER: "cafe-pos:order",
  SETTING: "cafe-pos:setting",
} as const;

export const DEFAULT_STORE_SETTINGS: StoreSettings = {
  darkMode: false,
};

export const PAYMENT_METHOD_LABELS: Record<PaymentMethod, string> = {
  card: "카드",
  cash: "현금",
};

export const DEFAULT_CATEGORIES = [
  { name: "커피" },
  { name: "음료" },
  { name: "디저트" },
] as const;
