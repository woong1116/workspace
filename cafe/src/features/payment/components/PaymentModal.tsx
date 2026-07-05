"use client";

import { useState } from "react";
import { Banknote, CreditCard } from "lucide-react";
import { Button } from "@/components/ui/Button";
import { Modal } from "@/components/ui/Modal";
import { useKeyboardShortcut } from "@/hooks/useKeyboardShortcut";
import type { PaymentMethod } from "@/types";
import { PAYMENT_METHOD_LABELS } from "@/utils/constants";
import { cn } from "@/utils/cn";
import { formatCurrency } from "@/utils/format";

interface PaymentModalProps {
  isOpen: boolean;
  onClose: () => void;
  totalAmount: number;
  onComplete: (method: PaymentMethod) => void;
}

const METHOD_ICONS: Record<PaymentMethod, typeof CreditCard> = {
  card: CreditCard,
  cash: Banknote,
};

const METHODS: PaymentMethod[] = ["card", "cash"];

export function PaymentModal({ isOpen, onClose, totalAmount, onComplete }: PaymentModalProps) {
  return (
    <Modal isOpen={isOpen} onClose={onClose} title="결제하기">
      {/* Modal은 닫힐 때 children을 완전히 언마운트하므로, 매번 열릴 때마다
          아래 선택 상태가 깨끗하게 초기화된다. */}
      <PaymentMethodPicker totalAmount={totalAmount} onCancel={onClose} onComplete={onComplete} />
    </Modal>
  );
}

interface PaymentMethodPickerProps {
  totalAmount: number;
  onCancel: () => void;
  onComplete: (method: PaymentMethod) => void;
}

function PaymentMethodPicker({ totalAmount, onCancel, onComplete }: PaymentMethodPickerProps) {
  const [selectedMethod, setSelectedMethod] = useState<PaymentMethod | null>(null);

  useKeyboardShortcut([
    {
      key: "Enter",
      enabled: selectedMethod !== null,
      ignoreWhenTyping: false,
      handler: () => {
        if (selectedMethod) onComplete(selectedMethod);
      },
    },
  ]);

  return (
    <div className="flex flex-col gap-5">
      <div className="text-center">
        <p className="text-sm text-slate-400 dark:text-slate-500">결제 금액</p>
        <p className="mt-1 text-3xl font-bold text-slate-900 dark:text-slate-100">
          {formatCurrency(totalAmount)}
        </p>
      </div>

      <div className="grid grid-cols-2 gap-2">
        {METHODS.map((method) => {
          const Icon = METHOD_ICONS[method];
          const isSelected = selectedMethod === method;
          return (
            <button
              key={method}
              onClick={() => setSelectedMethod(method)}
              className={cn(
                "flex flex-col items-center gap-2 rounded-xl border p-4 transition-all",
                isSelected
                  ? "border-indigo-500 bg-indigo-50 text-indigo-600 dark:bg-indigo-500/10 dark:text-indigo-400"
                  : "border-slate-200 text-slate-500 hover:border-slate-300 hover:bg-slate-50 dark:border-slate-700 dark:text-slate-400 dark:hover:bg-slate-800"
              )}
            >
              <Icon size={22} />
              <span className="text-sm font-medium">{PAYMENT_METHOD_LABELS[method]}</span>
            </button>
          );
        })}
      </div>

      <div className="flex justify-end gap-2">
        <Button type="button" variant="ghost" onClick={onCancel}>
          취소
        </Button>
        <Button type="button" disabled={!selectedMethod} onClick={() => selectedMethod && onComplete(selectedMethod)}>
          결제 완료
        </Button>
      </div>
      <p className="text-center text-xs text-slate-300 dark:text-slate-600">
        결제 수단 선택 후 Enter 키로도 완료할 수 있습니다.
      </p>
    </div>
  );
}
