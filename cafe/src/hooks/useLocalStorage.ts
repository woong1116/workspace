"use client";

import { useCallback, useEffect, useState } from "react";
import { getStorageItem, setStorageItem } from "@/utils/storage";

type SetValue<T> = T | ((prev: T) => T);

export function useLocalStorage<T>(key: string, initialValue: T) {
  const [value, setValue] = useState<T>(initialValue);
  const [isHydrated, setIsHydrated] = useState(false);

  useEffect(() => {
    // 서버 렌더링과 클라이언트 초기 렌더링을 일치시키기 위해 마운트 이후에만 localStorage 값을 반영한다.
    // eslint-disable-next-line react-hooks/set-state-in-effect
    setValue(getStorageItem(key, initialValue));
    setIsHydrated(true);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [key]);

  const update = useCallback(
    (next: SetValue<T>) => {
      setValue((prev) => {
        const resolved = typeof next === "function" ? (next as (prev: T) => T)(prev) : next;
        setStorageItem(key, resolved);
        return resolved;
      });
    },
    [key]
  );

  return [value, update, isHydrated] as const;
}
