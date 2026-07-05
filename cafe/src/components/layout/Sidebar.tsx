"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { BarChart3, LayoutGrid, Receipt, Settings } from "lucide-react";
import { cn } from "@/utils/cn";

const NAV_ITEMS = [
  { href: "/", label: "주문", icon: LayoutGrid },
  { href: "/orders", label: "주문 내역", icon: Receipt },
  { href: "/statistics", label: "통계", icon: BarChart3 },
  { href: "/settings", label: "설정", icon: Settings },
] as const;

export function Sidebar() {
  const pathname = usePathname();

  return (
    <aside className="hidden w-56 shrink-0 flex-col gap-1 border-r border-slate-200 bg-white p-4 dark:border-slate-800 dark:bg-slate-900 md:flex">
      {NAV_ITEMS.map((item) => {
        const isActive = pathname === item.href;
        const Icon = item.icon;
        return (
          <Link
            key={item.href}
            href={item.href}
            className={cn(
              "flex items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-medium transition-colors",
              isActive
                ? "bg-indigo-50 text-indigo-600 dark:bg-indigo-500/10 dark:text-indigo-400"
                : "text-slate-500 hover:bg-slate-100 hover:text-slate-700 dark:text-slate-400 dark:hover:bg-slate-800 dark:hover:text-slate-200"
            )}
          >
            <Icon size={18} />
            {item.label}
          </Link>
        );
      })}
    </aside>
  );
}
