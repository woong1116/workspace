"use client";

import { Pencil, Trash2 } from "lucide-react";
import { cn } from "@/utils/cn";

interface CategoryButtonProps {
  label: string;
  isActive: boolean;
  onClick: () => void;
  isEditable?: boolean;
  onEdit?: () => void;
  onDelete?: () => void;
}

export function CategoryButton({
  label,
  isActive,
  onClick,
  isEditable,
  onEdit,
  onDelete,
}: CategoryButtonProps) {
  return (
    <div
      className={cn(
        "group flex items-center gap-1 rounded-xl px-4 py-2 text-sm font-medium shadow-sm transition-all duration-150 hover:scale-[1.03]",
        isActive
          ? "bg-indigo-600 text-white shadow-lg shadow-indigo-600/20"
          : "bg-white text-slate-600 hover:bg-slate-50 dark:bg-slate-800 dark:text-slate-300 dark:hover:bg-slate-700"
      )}
    >
      <button onClick={onClick} className="cursor-pointer">
        {label}
      </button>
      {isEditable && (
        <span className="ml-1 hidden items-center gap-1 group-hover:flex">
          <button
            onClick={(event) => {
              event.stopPropagation();
              onEdit?.();
            }}
            className="rounded p-0.5 hover:bg-black/10"
            aria-label="카테고리 수정"
          >
            <Pencil size={12} />
          </button>
          <button
            onClick={(event) => {
              event.stopPropagation();
              onDelete?.();
            }}
            className="rounded p-0.5 hover:bg-black/10"
            aria-label="카테고리 삭제"
          >
            <Trash2 size={12} />
          </button>
        </span>
      )}
    </div>
  );
}
