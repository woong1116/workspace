import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";
import type { StoreSettings } from "@/types";
import { DEFAULT_STORE_SETTINGS, STORAGE_KEYS } from "@/utils/constants";

interface SettingState {
  settings: StoreSettings;
  hasHydrated: boolean;
  setHasHydrated: (hasHydrated: boolean) => void;
  toggleDarkMode: () => void;
}

export const useSettingStore = create<SettingState>()(
  persist(
    (set) => ({
      settings: DEFAULT_STORE_SETTINGS,
      hasHydrated: false,
      setHasHydrated: (hasHydrated) => set({ hasHydrated }),

      toggleDarkMode: () =>
        set((state) => ({ settings: { ...state.settings, darkMode: !state.settings.darkMode } })),
    }),
    {
      name: STORAGE_KEYS.SETTING,
      storage: createJSONStorage(() => localStorage),
      onRehydrateStorage: () => (state) => {
        state?.setHasHydrated(true);
      },
    }
  )
);
