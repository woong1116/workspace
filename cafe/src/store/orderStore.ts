import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";
import type { Order } from "@/types";
import { STORAGE_KEYS } from "@/utils/constants";

interface OrderState {
  orders: Order[];
  hasHydrated: boolean;
  setHasHydrated: (hasHydrated: boolean) => void;
  addOrder: (order: Order) => void;
}

export const useOrderStore = create<OrderState>()(
  persist(
    (set) => ({
      orders: [],
      hasHydrated: false,
      setHasHydrated: (hasHydrated) => set({ hasHydrated }),
      addOrder: (order) => set((state) => ({ orders: [order, ...state.orders] })),
    }),
    {
      name: STORAGE_KEYS.ORDER,
      storage: createJSONStorage(() => localStorage),
      onRehydrateStorage: () => (state) => {
        state?.setHasHydrated(true);
      },
    }
  )
);
