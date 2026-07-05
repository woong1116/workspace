import { Inbox, type LucideIcon } from "lucide-react";
import type { ReactNode } from "react";

interface EmptyStateProps {
  icon?: LucideIcon;
  title: string;
  description?: string;
  action?: ReactNode;
}

export function EmptyState({ icon: Icon = Inbox, title, description, action }: EmptyStateProps) {
  return (
    <div className="flex flex-col items-center justify-center gap-3 rounded-xl border border-dashed border-slate-200 py-16 text-center dark:border-slate-700">
      <Icon size={40} className="text-slate-300 dark:text-slate-600" />
      <div>
        <p className="font-medium text-slate-600 dark:text-slate-300">{title}</p>
        {description && (
          <p className="mt-1 text-sm text-slate-400 dark:text-slate-500">{description}</p>
        )}
      </div>
      {action}
    </div>
  );
}
