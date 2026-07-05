"use client";

import { useEffect, useState } from "react";

export function Header() {
  const [now, setNow] = useState<Date | null>(null);

  useEffect(() => {
    // 서버 렌더링 시각과 어긋나는 하이드레이션 불일치를 막기 위해 마운트 이후에만 시계를 채운다.
    // eslint-disable-next-line react-hooks/set-state-in-effect
    setNow(new Date());
    const timer = setInterval(() => setNow(new Date()), 1000);
    return () => clearInterval(timer);
  }, []);

  return (
    <header className="flex h-16 shrink-0 items-center justify-between border-b border-slate-200 bg-white px-6 dark:border-slate-800 dark:bg-slate-900">
      <h1 className="text-lg font-bold text-slate-900 dark:text-slate-100">라이언 카페</h1>
      <p className="text-sm tabular-nums text-slate-400 dark:text-slate-500">
        {now ? now.toLocaleString("ko-KR") : ""}
      </p>
    </header>
  );
}
