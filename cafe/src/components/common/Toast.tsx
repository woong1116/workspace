"use client";

import {
  createContext,
  useCallback,
  useContext,
  useMemo,
  useState,
  type ReactNode,
} from "react";
import { CheckCircle2, Info, X, XCircle } from "lucide-react";
import { generateId } from "@/utils/id";

type ToastType = "success" | "error" | "info";

interface ToastItem {
  id: string;
  message: string;
  type: ToastType;
}

interface ToastContextValue {
  showToast: (message: string, type?: ToastType) => void;
}

const ToastContext = createContext<ToastContextValue | null>(null);

const ICONS: Record<ToastType, typeof CheckCircle2> = {
  success: CheckCircle2,
  error: XCircle,
  info: Info,
};

const ICON_COLOR_CLASSES: Record<ToastType, string> = {
  success: "text-emerald-500",
  error: "text-red-500",
  info: "text-indigo-500",
};

const TOAST_DURATION_MS = 2500;

export function ToastProvider({ children }: { children: ReactNode }) {
  const [toasts, setToasts] = useState<ToastItem[]>([]);

  const removeToast = useCallback((id: string) => {
    setToasts((prev) => prev.filter((toast) => toast.id !== id));
  }, []);

  const showToast = useCallback(
    (message: string, type: ToastType = "success") => {
      const id = generateId();
      setToasts((prev) => [...prev, { id, message, type }]);
      setTimeout(() => removeToast(id), TOAST_DURATION_MS);
    },
    [removeToast]
  );

  const value = useMemo(() => ({ showToast }), [showToast]);

  return (
    <ToastContext.Provider value={value}>
      {children}
      <div className="pointer-events-none fixed right-4 top-4 z-[100] flex flex-col gap-2">
        {toasts.map((toast) => {
          const Icon = ICONS[toast.type];
          return (
            <div
              key={toast.id}
              className="animate-toast-in pointer-events-auto flex items-center gap-2 rounded-xl bg-white px-4 py-3 shadow-lg ring-1 ring-slate-200 dark:bg-slate-800 dark:ring-slate-700"
            >
              <Icon size={18} className={ICON_COLOR_CLASSES[toast.type]} />
              <span className="text-sm text-slate-700 dark:text-slate-200">{toast.message}</span>
              <button
                onClick={() => removeToast(toast.id)}
                className="ml-2 text-slate-300 transition-colors hover:text-slate-500"
                aria-label="닫기"
              >
                <X size={14} />
              </button>
            </div>
          );
        })}
      </div>
    </ToastContext.Provider>
  );
}

export function useToast(): ToastContextValue {
  const context = useContext(ToastContext);
  if (!context) {
    throw new Error("useToast는 ToastProvider 내부에서만 사용할 수 있습니다.");
  }
  return context;
}
