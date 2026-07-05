"use client";

import { Minus, Plus, Trash2 } from "lucide-react";
import type { CartItem as CartItemType } from "@/types";
import { cn } from "@/utils/cn";
import { formatCurrency } from "@/utils/format";

interface CartItemProps {
  cartItem: CartItemType;
  isSelected: boolean;
  onSelect: () => void;
  onIncrement: () => void;
  onDecrement: () => void;
  onRemove: () => void;
}

export function CartItem({
  cartItem,
  isSelected,
  onSelect,
  onIncrement,
  onDecrement,
  onRemove,
}: CartItemProps) {
  const { menuItem, quantity } = cartItem;

  return (
    <div
      onClick={onSelect}
      className={cn(
        "flex cursor-pointer items-center gap-3 rounded-xl border p-3 transition-colors",
        isSelected
          ? "border-indigo-400 bg-indigo-50 dark:border-indigo-500 dark:bg-indigo-500/10"
          : "border-transparent bg-slate-50 hover:bg-slate-100 dark:bg-slate-800 dark:hover:bg-slate-700/70"
      )}
    >
      <div className="min-w-0 flex-1">
        <p className="truncate text-sm font-medium text-slate-800 dark:text-slate-100">{menuItem.name}</p>
        <p className="text-xs text-slate-400 dark:text-slate-500">{formatCurrency(menuItem.price)}</p>
      </div>

      <div className="flex items-center gap-1.5">
        <button
          onClick={(event) => {
            event.stopPropagation();
            onDecrement();
          }}
          disabled={quantity <= 1}
          className="flex h-7 w-7 items-center justify-center rounded-lg bg-white text-slate-500 shadow-sm transition-colors hover:bg-slate-100 disabled:opacity-40 dark:bg-slate-900 dark:text-slate-300"
          aria-label="수량 감소"
        >
          <Minus size={14} />
        </button>
        <span className="w-6 text-center text-sm font-semibold tabular-nums text-slate-700 dark:text-slate-200">
          {quantity}
        </span>
        <button
          onClick={(event) => {
            event.stopPropagation();
            onIncrement();
          }}
          className="flex h-7 w-7 items-center justify-center rounded-lg bg-white text-slate-500 shadow-sm transition-colors hover:bg-slate-100 dark:bg-slate-900 dark:text-slate-300"
          aria-label="수량 증가"
        >
          <Plus size={14} />
        </button>
      </div>

      <p className="w-20 shrink-0 text-right text-sm font-semibold text-slate-800 dark:text-slate-100">
        {formatCurrency(menuItem.price * quantity)}
      </p>

      <button
        onClick={(event) => {
          event.stopPropagation();
          onRemove();
        }}
        className="text-slate-300 transition-colors hover:text-red-500"
        aria-label="삭제"
      >
        <Trash2 size={16} />
      </button>
    </div>
  );
}
