"use client";

import { useEffect } from "react";

export interface KeyboardShortcut {
  key: string;
  ctrlOrCmd?: boolean;
  enabled?: boolean;
  preventDefault?: boolean;
  /** 입력 필드에 포커스된 상태에서는 무시할지 여부 (기본값 true). ESC로 모달을 닫는 경우처럼
   * 입력 중에도 반드시 동작해야 하면 false로 지정한다. */
  ignoreWhenTyping?: boolean;
  handler: (event: KeyboardEvent) => void;
}

function isTypingInField(target: EventTarget | null): boolean {
  if (!(target instanceof HTMLElement)) return false;
  return ["INPUT", "TEXTAREA", "SELECT"].includes(target.tagName) || target.isContentEditable;
}

export function useKeyboardShortcut(shortcuts: KeyboardShortcut[]) {
  useEffect(() => {
    function onKeyDown(event: KeyboardEvent) {
      for (const shortcut of shortcuts) {
        const {
          key,
          ctrlOrCmd = false,
          enabled = true,
          preventDefault = true,
          ignoreWhenTyping = true,
          handler,
        } = shortcut;
        if (!enabled) continue;

        const matchesKey = event.key.toLowerCase() === key.toLowerCase();
        const hasModifier = event.ctrlKey || event.metaKey;
        const matchesModifier = ctrlOrCmd ? hasModifier : true;

        if (!ctrlOrCmd && ignoreWhenTyping && isTypingInField(event.target)) continue;

        if (matchesKey && matchesModifier) {
          if (preventDefault) event.preventDefault();
          handler(event);
        }
      }
    }

    window.addEventListener("keydown", onKeyDown);
    return () => window.removeEventListener("keydown", onKeyDown);
  }, [shortcuts]);
}
