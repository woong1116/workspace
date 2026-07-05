"use client";

import { useEffect, useState, type ReactNode } from "react";
import { createPortal } from "react-dom";
import { X } from "lucide-react";
import { cn } from "@/utils/cn";
import { useKeyboardShortcut } from "@/hooks/useKeyboardShortcut";

interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  title?: string;
  children: ReactNode;
  className?: string;
  closeOnEsc?: boolean;
}

export function Modal({ isOpen, onClose, title, children, className, closeOnEsc = true }: ModalProps) {
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    // document.body에 렌더링하는 포탈이므로 클라이언트 마운트 이후에만 렌더링한다.
    // eslint-disable-next-line react-hooks/set-state-in-effect
    setMounted(true);
  }, []);

  useEffect(() => {
    if (!isOpen) return;
    document.body.style.overflow = "hidden";
    return () => {
      document.body.style.overflow = "";
    };
  }, [isOpen]);

  useKeyboardShortcut([
    { key: "Escape", enabled: isOpen && closeOnEsc, ignoreWhenTyping: false, handler: onClose },
  ]);

  if (!mounted || !isOpen) return null;

  return createPortal(
    <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
      <div className="absolute inset-0 animate-fade-in bg-black/50 backdrop-blur-sm" onClick={onClose} />
      <div
        className={cn(
          "relative z-10 w-full max-w-md animate-scale-in rounded-xl bg-white shadow-lg dark:bg-slate-900",
          className
        )}
        role="dialog"
        aria-modal="true"
      >
        {title && (
          <div className="flex items-center justify-between border-b border-slate-100 px-5 py-4 dark:border-slate-800">
            <h2 className="text-lg font-semibold text-slate-900 dark:text-slate-100">{title}</h2>
            <button
              onClick={onClose}
              className="rounded-lg p-1 text-slate-400 transition-colors hover:bg-slate-100 hover:text-slate-600 dark:hover:bg-slate-800"
              aria-label="닫기"
            >
              <X size={18} />
            </button>
          </div>
        )}
        <div className="p-5">{children}</div>
      </div>
    </div>,
    document.body
  );
}
