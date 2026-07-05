import type { LucideIcon } from "lucide-react";

interface StatsCardProps {
  label: string;
  value: string;
  icon?: LucideIcon;
}

export function StatsCard({ label, value, icon: Icon }: StatsCardProps) {
  return (
    <div className="flex items-center gap-4 rounded-xl bg-white p-5 shadow-lg dark:bg-slate-900">
      {Icon && (
        <div className="flex h-11 w-11 shrink-0 items-center justify-center rounded-xl bg-indigo-50 text-indigo-600 dark:bg-indigo-500/10 dark:text-indigo-400">
          <Icon size={20} />
        </div>
      )}
      <div className="min-w-0">
        <p className="text-sm text-slate-400 dark:text-slate-500">{label}</p>
        <p className="mt-0.5 truncate text-xl font-semibold text-slate-900 dark:text-slate-100">{value}</p>
      </div>
    </div>
  );
}
