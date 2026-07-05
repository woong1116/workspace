"use client";

import { Printer } from "lucide-react";
import { Button } from "@/components/ui/Button";
import { Modal } from "@/components/ui/Modal";
import type { Order } from "@/types";
import { PAYMENT_METHOD_LABELS } from "@/utils/constants";
import { formatCurrency, formatDateTime } from "@/utils/format";

interface ReceiptModalProps {
  isOpen: boolean;
  onClose: () => void;
  order: Order | null;
}

export function ReceiptModal({ isOpen, onClose, order }: ReceiptModalProps) {
  if (!order) return null;

  return (
    <Modal isOpen={isOpen} onClose={onClose} title="영수증" className="max-w-sm">
      <div
        id="print-area"
        className="flex flex-col gap-3 font-mono text-sm text-slate-700 dark:text-slate-200"
      >
        <p className="text-center text-base font-bold">라이언 카페</p>

        <div className="space-y-1 border-t border-dashed border-slate-300 pt-2 text-xs text-slate-500 dark:border-slate-600">
          <div className="flex justify-between">
            <span>주문번호</span>
            <span>{order.orderNumber}</span>
          </div>
          <div className="flex justify-between">
            <span>주문시간</span>
            <span>{formatDateTime(order.createdAt)}</span>
          </div>
          <div className="flex justify-between">
            <span>결제수단</span>
            <span>{PAYMENT_METHOD_LABELS[order.paymentMethod]}</span>
          </div>
        </div>

        <div className="space-y-1 border-t border-dashed border-slate-300 pt-2 dark:border-slate-600">
          {order.items.map((item) => (
            <div key={item.menuItemId} className="flex justify-between">
              <span>
                {item.name} x{item.quantity}
              </span>
              <span>{formatCurrency(item.lineTotal)}</span>
            </div>
          ))}
        </div>

        <div className="space-y-1 border-t border-dashed border-slate-300 pt-2 dark:border-slate-600">
          <div className="flex justify-between">
            <span>합계</span>
            <span>{formatCurrency(order.subtotal)}</span>
          </div>
          {order.discount > 0 && (
            <div className="flex justify-between">
              <span>할인</span>
              <span>-{formatCurrency(order.discount)}</span>
            </div>
          )}
          <div className="flex justify-between border-t border-slate-300 pt-1 text-base font-bold text-slate-900 dark:border-slate-600 dark:text-slate-100">
            <span>총 결제금액</span>
            <span>{formatCurrency(order.total)}</span>
          </div>
        </div>
      </div>

      <div className="mt-4 flex justify-end gap-2">
        <Button variant="outline" onClick={() => window.print()}>
          <Printer size={16} /> 영수증 출력
        </Button>
        <Button onClick={onClose}>닫기</Button>
      </div>
    </Modal>
  );
}
