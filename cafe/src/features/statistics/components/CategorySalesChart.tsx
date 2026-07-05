import type { CategorySalesCount } from "@/types";
import { formatCurrency } from "@/utils/format";

interface CategorySalesChartProps {
  data: CategorySalesCount[];
}

export function CategorySalesChart({ data }: CategorySalesChartProps) {
  const maxQuantity = Math.max(1, ...data.map((item) => item.quantity));

  return (
    <div className="space-y-4">
      {data.map((item) => {
        const widthPercent = Math.round((item.quantity / maxQuantity) * 100);
        return (
          <div
            key={item.categoryId}
            tabIndex={0}
            className="group relative flex items-center gap-3 rounded-lg outline-none focus-visible:ring-2 focus-visible:ring-indigo-400"
          >
            <span className="w-16 shrink-0 truncate text-sm text-slate-500 dark:text-slate-400">
              {item.categoryName}
            </span>
            <div className="h-6 flex-1 rounded-md bg-slate-100 dark:bg-slate-800">
              <div
                className="h-6 rounded-r-md bg-[#2a78d6] transition-[width,filter] group-hover:brightness-110 group-focus-visible:brightness-110 dark:bg-[#3987e5]"
                style={{ width: `${widthPercent}%` }}
              />
            </div>
            <span className="w-10 shrink-0 text-right text-sm font-semibold tabular-nums text-slate-700 dark:text-slate-200">
              {item.quantity}
            </span>

            <div className="pointer-events-none absolute -top-8 left-16 z-10 whitespace-nowrap rounded-lg bg-slate-900 px-2 py-1 text-xs text-white opacity-0 shadow-lg transition-opacity group-hover:opacity-100 group-focus-visible:opacity-100">
              {item.categoryName} · {item.quantity}개 · {formatCurrency(item.revenue)}
            </div>
          </div>
        );
      })}
    </div>
  );
}
