import { OrderHistory } from "@/features/order/components/OrderHistory";

export default function OrdersPage() {
  return (
    <div className="flex flex-1 flex-col gap-4">
      <h1 className="text-lg font-semibold text-slate-900 dark:text-slate-100">주문 내역</h1>
      <OrderHistory />
    </div>
  );
}
