"use client";

import { Award, CalendarDays, CalendarRange, Coins } from "lucide-react";
import { EmptyState } from "@/components/common/EmptyState";
import { Skeleton } from "@/components/common/Skeleton";
import { CategorySalesChart } from "@/features/statistics/components/CategorySalesChart";
import { StatsCard } from "@/features/statistics/components/StatsCard";
import { useMenuStore } from "@/store/menuStore";
import { useOrderStore } from "@/store/orderStore";
import { formatCurrency } from "@/utils/format";
import { buildSalesSummary } from "@/utils/statistics";

export default function StatisticsPage() {
  const { orders, hasHydrated: ordersHydrated } = useOrderStore();
  const { menuItems, categories, hasHydrated: menuHydrated } = useMenuStore();

  if (!ordersHydrated || !menuHydrated) {
    return (
      <div className="grid grid-cols-2 gap-4 lg:grid-cols-4">
        {Array.from({ length: 4 }).map((_, index) => (
          <Skeleton key={index} className="h-20" />
        ))}
      </div>
    );
  }

  const summary = buildSalesSummary(orders, menuItems, categories);

  return (
    <div className="flex flex-1 flex-col gap-6">
      <h1 className="text-lg font-semibold text-slate-900 dark:text-slate-100">통계</h1>

      <div className="grid grid-cols-2 gap-4 lg:grid-cols-4">
        <StatsCard label="오늘 매출" value={formatCurrency(summary.todayTotal)} icon={CalendarDays} />
        <StatsCard label="주간 매출" value={formatCurrency(summary.weekTotal)} icon={CalendarRange} />
        <StatsCard label="월간 매출" value={formatCurrency(summary.monthTotal)} icon={Coins} />
        <StatsCard
          label="가장 많이 팔린 메뉴"
          value={
            summary.bestSellingMenu
              ? `${summary.bestSellingMenu.name} · ${summary.bestSellingMenu.quantity}개`
              : "-"
          }
          icon={Award}
        />
      </div>

      <div className="rounded-xl bg-white p-5 shadow-lg dark:bg-slate-900">
        <h2 className="mb-4 text-sm font-semibold text-slate-700 dark:text-slate-200">카테고리별 판매량</h2>
        {summary.salesByCategory.length === 0 ? (
          <EmptyState title="판매 데이터가 없습니다" description="결제가 완료되면 통계가 표시됩니다." />
        ) : (
          <CategorySalesChart data={summary.salesByCategory} />
        )}
      </div>
    </div>
  );
}
