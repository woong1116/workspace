"use client";

import { useState } from "react";
import { EmptyState } from "@/components/common/EmptyState";
import { Skeleton } from "@/components/common/Skeleton";
import { Table, type TableColumn } from "@/components/ui/Table";
import { ReceiptModal } from "@/features/receipt/components/ReceiptModal";
import { useOrderStore } from "@/store/orderStore";
import type { Order } from "@/types";
import { PAYMENT_METHOD_LABELS } from "@/utils/constants";
import { formatCurrency, formatDateTime } from "@/utils/format";

export function OrderHistory() {
  const { orders, hasHydrated } = useOrderStore();
  const [selectedOrder, setSelectedOrder] = useState<Order | null>(null);

  if (!hasHydrated) {
    return (
      <div className="space-y-2">
        {Array.from({ length: 5 }).map((_, index) => (
          <Skeleton key={index} className="h-12" />
        ))}
      </div>
    );
  }

  if (orders.length === 0) {
    return <EmptyState title="주문 내역이 없습니다" description="결제가 완료되면 이곳에 표시됩니다." />;
  }

  const columns: TableColumn<Order>[] = [
    { key: "createdAt", header: "날짜", render: (order) => formatDateTime(order.createdAt) },
    { key: "orderNumber", header: "주문번호", render: (order) => order.orderNumber },
    {
      key: "paymentMethod",
      header: "결제수단",
      align: "center",
      render: (order) => PAYMENT_METHOD_LABELS[order.paymentMethod],
    },
    {
      key: "itemCount",
      header: "품목수",
      align: "center",
      render: (order) => `${order.items.length}종`,
    },
    { key: "total", header: "금액", align: "right", render: (order) => formatCurrency(order.total) },
  ];

  return (
    <>
      <Table
        columns={columns}
        data={orders}
        rowKey={(order) => order.id}
        onRowClick={setSelectedOrder}
        emptyMessage="주문 내역이 없습니다."
      />

      <ReceiptModal
        isOpen={selectedOrder !== null}
        onClose={() => setSelectedOrder(null)}
        order={selectedOrder}
      />
    </>
  );
}
