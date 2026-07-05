"use client";

import { useState, type DragEvent } from "react";
import { GripVertical, Pencil, Trash2 } from "lucide-react";
import type { MenuItem } from "@/types";
import { cn } from "@/utils/cn";
import { formatCurrency } from "@/utils/format";

interface MenuCardProps {
  menuItem: MenuItem;
  onSelect?: (menuItem: MenuItem) => void;
  isEditable?: boolean;
  onEdit?: (menuItem: MenuItem) => void;
  onDelete?: (menuItem: MenuItem) => void;
  onReorder?: (draggedId: string, targetId: string) => void;
}

export function MenuCard({
  menuItem,
  onSelect,
  isEditable,
  onEdit,
  onDelete,
  onReorder,
}: MenuCardProps) {
  const isDisabled = menuItem.isSoldOut && !isEditable;
  const [isDragOver, setIsDragOver] = useState(false);

  function handleDragStart(event: DragEvent<HTMLDivElement>) {
    event.dataTransfer.setData("text/plain", menuItem.id);
    event.dataTransfer.effectAllowed = "move";
  }

  function handleDragOver(event: DragEvent<HTMLDivElement>) {
    if (!isEditable) return;
    event.preventDefault();
    setIsDragOver(true);
  }

  function handleDrop(event: DragEvent<HTMLDivElement>) {
    if (!isEditable) return;
    event.preventDefault();
    setIsDragOver(false);
    const draggedId = event.dataTransfer.getData("text/plain");
    if (draggedId && draggedId !== menuItem.id) {
      onReorder?.(draggedId, menuItem.id);
    }
  }

  return (
    <div
      draggable={isEditable}
      onClick={() => !isDisabled && onSelect?.(menuItem)}
      onDragStart={handleDragStart}
      onDragOver={handleDragOver}
      onDragLeave={() => setIsDragOver(false)}
      onDrop={handleDrop}
      className={cn(
        "group relative flex min-h-32 flex-col justify-between rounded-xl bg-white p-4 shadow-lg transition-all duration-150 dark:bg-slate-800",
        isDisabled
          ? "cursor-not-allowed opacity-50"
          : isEditable
            ? "cursor-grab active:cursor-grabbing"
            : "cursor-pointer hover:-translate-y-0.5 hover:shadow-xl active:scale-[0.98]",
        isDragOver && "ring-2 ring-indigo-400"
      )}
    >
      {menuItem.isSoldOut && (
        <span className="absolute right-3 top-3 rounded-full bg-slate-900/80 px-2 py-0.5 text-xs text-white">
          품절
        </span>
      )}
      {isEditable && (
        <GripVertical
          size={14}
          className="absolute left-1.5 top-1.5 text-slate-300 dark:text-slate-600"
        />
      )}
      <div className={isEditable ? "pl-3" : undefined}>
        <p className="font-semibold text-slate-900 dark:text-slate-100">{menuItem.name}</p>
        {menuItem.description && (
          <p className="mt-1 line-clamp-2 text-xs text-slate-400 dark:text-slate-500">
            {menuItem.description}
          </p>
        )}
      </div>
      <p className="mt-3 text-lg font-bold text-indigo-600 dark:text-indigo-400">
        {formatCurrency(menuItem.price)}
      </p>

      {isEditable && (
        <div className="absolute inset-x-0 bottom-0 flex justify-end gap-1 rounded-b-xl bg-gradient-to-t from-black/40 to-transparent p-2 opacity-0 transition-opacity group-hover:opacity-100">
          <button
            onClick={(event) => {
              event.stopPropagation();
              onEdit?.(menuItem);
            }}
            className="rounded-lg bg-white/90 p-1.5 text-slate-700 hover:bg-white"
            aria-label="메뉴 수정"
          >
            <Pencil size={14} />
          </button>
          <button
            onClick={(event) => {
              event.stopPropagation();
              onDelete?.(menuItem);
            }}
            className="rounded-lg bg-white/90 p-1.5 text-red-600 hover:bg-white"
            aria-label="메뉴 삭제"
          >
            <Trash2 size={14} />
          </button>
        </div>
      )}
    </div>
  );
}
