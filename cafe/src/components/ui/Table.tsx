import type { ReactNode } from "react";
import { cn } from "@/utils/cn";

export interface TableColumn<T> {
  key: string;
  header: string;
  align?: "left" | "center" | "right";
  render: (row: T) => ReactNode;
}

interface TableProps<T> {
  columns: TableColumn<T>[];
  data: T[];
  rowKey: (row: T) => string;
  emptyMessage?: string;
  onRowClick?: (row: T) => void;
}

const ALIGN_CLASSES = {
  left: "text-left",
  center: "text-center",
  right: "text-right",
} as const;

export function Table<T>({
  columns,
  data,
  rowKey,
  emptyMessage = "데이터가 없습니다.",
  onRowClick,
}: TableProps<T>) {
  return (
    <div className="overflow-hidden rounded-xl border border-slate-200 shadow-lg dark:border-slate-800">
      <table className="w-full text-sm">
        <thead className="bg-slate-50 dark:bg-slate-800/60">
          <tr>
            {columns.map((column) => (
              <th
                key={column.key}
                className={cn(
                  "px-4 py-3 font-semibold text-slate-600 dark:text-slate-300",
                  ALIGN_CLASSES[column.align ?? "left"]
                )}
              >
                {column.header}
              </th>
            ))}
          </tr>
        </thead>
        <tbody className="divide-y divide-slate-100 dark:divide-slate-800">
          {data.length === 0 ? (
            <tr>
              <td colSpan={columns.length} className="px-4 py-10 text-center text-slate-400">
                {emptyMessage}
              </td>
            </tr>
          ) : (
            data.map((row) => (
              <tr
                key={rowKey(row)}
                onClick={() => onRowClick?.(row)}
                className={cn(
                  "transition-colors",
                  onRowClick && "cursor-pointer hover:bg-slate-50 dark:hover:bg-slate-800/60"
                )}
              >
                {columns.map((column) => (
                  <td
                    key={column.key}
                    className={cn(
                      "px-4 py-3 text-slate-700 dark:text-slate-200",
                      ALIGN_CLASSES[column.align ?? "left"]
                    )}
                  >
                    {column.render(row)}
                  </td>
                ))}
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}
