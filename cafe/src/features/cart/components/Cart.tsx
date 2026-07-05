"use client";

import { useState } from "react";
import { Trash2 } from "lucide-react";
import { EmptyState } from "@/components/common/EmptyState";
import { useToast } from "@/components/common/Toast";
import { Button } from "@/components/ui/Button";
import { useKeyboardShortcut } from "@/hooks/useKeyboardShortcut";
import { useCartStore } from "@/store/cartStore";
import { calculateCartSummary } from "@/utils/calculations";
import { cn } from "@/utils/cn";
import { formatCurrency, formatPercent } from "@/utils/format";
import { CartItem } from "./CartItem";

interface CartProps {
  onCheckout: () => void;
}

const DISCOUNT_OPTIONS = [0, 0.05, 0.1] as const;

export function Cart({ onCheckout }: CartProps) {
  const {
    items,
    discountRate,
    incrementQuantity,
    decrementQuantity,
    removeItem,
    clearCart,
    setDiscountRate,
  } = useCartStore();
  const { showToast } = useToast();
  const [selectedId, setSelectedId] = useState<string | null>(null);

  useKeyboardShortcut([
    {
      key: "Delete",
      enabled: selectedId !== null,
      handler: () => {
        if (!selectedId) return;
        removeItem(selectedId);
        showToast("상품을 삭제했습니다.", "info");
        setSelectedId(null);
      },
    },
  ]);

  const summary = calculateCartSummary(items, discountRate);

  function handleClear() {
    if (items.length === 0) return;
    if (!window.confirm("장바구니를 모두 비울까요?")) return;
    clearCart();
    setSelectedId(null);
    showToast("장바구니를 비웠습니다.", "info");
  }

  return (
    <div className="flex h-full flex-col">
      <div className="flex items-center justify-between border-b border-slate-100 pb-3 dark:border-slate-800">
        <h2 className="text-base font-semibold text-slate-900 dark:text-slate-100">
          장바구니 ({summary.itemCount})
        </h2>
        <button
          onClick={handleClear}
          disabled={items.length === 0}
          className="flex items-center gap-1 text-xs text-slate-400 transition-colors hover:text-red-500 disabled:opacity-40"
        >
          <Trash2 size={14} /> 전체 비우기
        </button>
      </div>

      <div className="flex-1 space-y-2 overflow-y-auto py-3">
        {items.length === 0 ? (
          <EmptyState title="장바구니가 비어있습니다" description="메뉴를 선택해 주문을 시작하세요." />
        ) : (
          items.map((cartItem) => (
            <CartItem
              key={cartItem.menuItem.id}
              cartItem={cartItem}
              isSelected={selectedId === cartItem.menuItem.id}
              onSelect={() => setSelectedId(cartItem.menuItem.id)}
              onIncrement={() => incrementQuantity(cartItem.menuItem.id)}
              onDecrement={() => decrementQuantity(cartItem.menuItem.id)}
              onRemove={() => removeItem(cartItem.menuItem.id)}
            />
          ))
        )}
      </div>

      <div className="space-y-3 border-t border-slate-100 pt-3 dark:border-slate-800">
        <div className="flex items-center justify-between text-xs text-slate-500 dark:text-slate-400">
          <span>할인</span>
          <div className="flex gap-1">
            {DISCOUNT_OPTIONS.map((rate) => (
              <button
                key={rate}
                onClick={() => setDiscountRate(rate)}
                className={cn(
                  "rounded-lg px-2 py-1 transition-colors",
                  discountRate === rate
                    ? "bg-indigo-600 text-white"
                    : "bg-slate-100 hover:bg-slate-200 dark:bg-slate-800 dark:hover:bg-slate-700"
                )}
              >
                {rate === 0 ? "없음" : formatPercent(rate)}
              </button>
            ))}
          </div>
        </div>

        <dl className="space-y-1 text-sm">
          <div className="flex justify-between text-slate-500 dark:text-slate-400">
            <dt>소계</dt>
            <dd>{formatCurrency(summary.subtotal)}</dd>
          </div>
          {summary.discount > 0 && (
            <div className="flex justify-between text-slate-500 dark:text-slate-400">
              <dt>할인</dt>
              <dd>-{formatCurrency(summary.discount)}</dd>
            </div>
          )}
          <div className="flex justify-between border-t border-slate-100 pt-1 text-base font-bold text-slate-900 dark:border-slate-800 dark:text-slate-100">
            <dt>총 결제금액</dt>
            <dd>{formatCurrency(summary.total)}</dd>
          </div>
        </dl>

        <Button fullWidth size="lg" disabled={items.length === 0} onClick={onCheckout}>
          결제하기
        </Button>
      </div>
    </div>
  );
}
