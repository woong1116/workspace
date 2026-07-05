export function getStorageItem<T>(key: string, fallback: T): T {
  if (typeof window === "undefined") return fallback;

  try {
    const raw = window.localStorage.getItem(key);
    if (!raw) return fallback;
    return JSON.parse(raw) as T;
  } catch {
    return fallback;
  }
}

export function setStorageItem<T>(key: string, value: T): void {
  if (typeof window === "undefined") return;

  try {
    window.localStorage.setItem(key, JSON.stringify(value));
  } catch {
    // localStorage 용량 초과 등은 무시하고 메모리 상태만 유지한다.
  }
}

export function removeStorageItem(key: string): void {
  if (typeof window === "undefined") return;
  window.localStorage.removeItem(key);
}
