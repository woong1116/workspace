"use client";

import { Moon, Sun } from "lucide-react";
import { Skeleton } from "@/components/common/Skeleton";
import { useSettingStore } from "@/store/settingStore";
import { cn } from "@/utils/cn";

export default function SettingsPage() {
  const { settings, hasHydrated, toggleDarkMode } = useSettingStore();

  if (!hasHydrated) {
    return (
      <div className="max-w-lg space-y-3">
        <Skeleton className="h-20" />
      </div>
    );
  }

  return (
    <div className="flex flex-1 flex-col gap-6">
      <h1 className="text-lg font-semibold text-slate-900 dark:text-slate-100">설정</h1>

      <div className="flex max-w-lg items-center justify-between rounded-xl bg-white p-5 shadow-lg dark:bg-slate-900">
        <div>
          <h2 className="text-sm font-semibold text-slate-700 dark:text-slate-200">다크 모드</h2>
          <p className="mt-1 text-xs text-slate-400 dark:text-slate-500">
            어두운 화면 테마로 전환합니다.
          </p>
        </div>
        <button
          onClick={toggleDarkMode}
          aria-pressed={settings.darkMode}
          aria-label="다크 모드 전환"
          className={cn(
            "relative flex h-8 w-14 items-center rounded-full transition-colors",
            settings.darkMode ? "bg-indigo-600" : "bg-slate-200 dark:bg-slate-700"
          )}
        >
          <span
            className={cn(
              "flex h-6 w-6 items-center justify-center rounded-full bg-white shadow-md transition-transform",
              settings.darkMode ? "translate-x-7" : "translate-x-1"
            )}
          >
            {settings.darkMode ? (
              <Moon size={13} className="text-indigo-600" />
            ) : (
              <Sun size={13} className="text-amber-500" />
            )}
          </span>
        </button>
      </div>
    </div>
  );
}
