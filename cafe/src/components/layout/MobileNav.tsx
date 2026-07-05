"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { BarChart3, LayoutGrid, Receipt, Settings } from "lucide-react";
import { cn } from "@/utils/cn";

const NAV_ITEMS = [
  { href: "/", label: "주문", icon: LayoutGrid },
  { href: "/orders", label: "내역", icon: Receipt },
  { href: "/statistics", label: "통계", icon: BarChart3 },
  { href: "/settings", label: "설정", icon: Settings },
] as const;

export function MobileNav() {
  const pathname = usePathname();

  return (
    <nav className="flex border-t border-slate-200 bg-white dark:border-slate-800 dark:bg-slate-900 md:hidden">
      {NAV_ITEMS.map((item) => {
        const isActive = pathname === item.href;
        const Icon = item.icon;
        return (
          <Link
            key={item.href}
            href={item.href}
            className={cn(
              "flex flex-1 flex-col items-center gap-1 py-2.5 text-xs font-medium transition-colors",
              isActive
                ? "text-indigo-600 dark:text-indigo-400"
                : "text-slate-400 hover:text-slate-600 dark:hover:text-slate-300"
            )}
          >
            <Icon size={20} />
            {item.label}
          </Link>
        );
      })}
    </nav>
  );
}
