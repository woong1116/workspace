"use client";

import { useEffect } from "react";
import { useSettingStore } from "@/store/settingStore";

export function ThemeSync() {
  const { settings, hasHydrated } = useSettingStore();

  useEffect(() => {
    if (!hasHydrated) return;
    document.documentElement.classList.toggle("dark", settings.darkMode);
  }, [settings.darkMode, hasHydrated]);

  return null;
}
