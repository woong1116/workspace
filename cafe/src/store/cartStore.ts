import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";
import type { CartItem, MenuItem } from "@/types";
import { STORAGE_KEYS } from "@/utils/constants";

interface CartState {
  items: CartItem[];
  discountRate: number;
  hasHydrated: boolean;
  setHasHydrated: (hasHydrated: boolean) => void;
  addItem: (menuItem: MenuItem) => void;
  incrementQuantity: (menuItemId: string) => void;
  decrementQuantity: (menuItemId: string) => void;
  setQuantity: (menuItemId: string, quantity: number) => void;
  removeItem: (menuItemId: string) => void;
  clearCart: () => void;
  setDiscountRate: (rate: number) => void;
}

export const useCartStore = create<CartState>()(
  persist(
    (set) => ({
      items: [],
      discountRate: 0,
      hasHydrated: false,
      setHasHydrated: (hasHydrated) => set({ hasHydrated }),

      addItem: (menuItem) => {
        set((state) => {
          const existing = state.items.find((item) => item.menuItem.id === menuItem.id);
          if (existing) {
            return {
              items: state.items.map((item) =>
                item.menuItem.id === menuItem.id ? { ...item, quantity: item.quantity + 1 } : item
              ),
            };
          }
          return { items: [...state.items, { menuItem, quantity: 1 }] };
        });
      },

      incrementQuantity: (menuItemId) => {
        set((state) => ({
          items: state.items.map((item) =>
            item.menuItem.id === menuItemId ? { ...item, quantity: item.quantity + 1 } : item
          ),
        }));
      },

      decrementQuantity: (menuItemId) => {
        set((state) => ({
          items: state.items.map((item) =>
            item.menuItem.id === menuItemId
              ? { ...item, quantity: Math.max(1, item.quantity - 1) }
              : item
          ),
        }));
      },

      setQuantity: (menuItemId, quantity) => {
        set((state) => {
          if (quantity <= 0) {
            return { items: state.items.filter((item) => item.menuItem.id !== menuItemId) };
          }
          return {
            items: state.items.map((item) =>
              item.menuItem.id === menuItemId ? { ...item, quantity } : item
            ),
          };
        });
      },

      removeItem: (menuItemId) => {
        set((state) => ({
          items: state.items.filter((item) => item.menuItem.id !== menuItemId),
        }));
      },

      clearCart: () => set({ items: [], discountRate: 0 }),

      setDiscountRate: (rate) => set({ discountRate: Math.min(1, Math.max(0, rate)) }),
    }),
    {
      name: STORAGE_KEYS.CART,
      storage: createJSONStorage(() => localStorage),
      onRehydrateStorage: () => (state) => {
        state?.setHasHydrated(true);
      },
    }
  )
);
