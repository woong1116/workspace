import type { Category, CategorySalesCount, MenuItem, MenuSalesCount, Order, SalesSummary } from "@/types";

function isSameDay(a: Date, b: Date): boolean {
  return a.getFullYear() === b.getFullYear() && a.getMonth() === b.getMonth() && a.getDate() === b.getDate();
}

function startOfDay(date: Date): Date {
  const copy = new Date(date);
  copy.setHours(0, 0, 0, 0);
  return copy;
}

export function sumOrdersTotal(orders: Order[]): number {
  return orders.reduce((sum, order) => sum + order.total, 0);
}

export function filterOrdersToday(orders: Order[], now: Date): Order[] {
  return orders.filter((order) => isSameDay(new Date(order.createdAt), now));
}

export function filterOrdersThisWeek(orders: Order[], now: Date): Order[] {
  const weekAgo = startOfDay(now);
  weekAgo.setDate(weekAgo.getDate() - 6);
  return orders.filter((order) => new Date(order.createdAt) >= weekAgo);
}

export function filterOrdersThisMonth(orders: Order[], now: Date): Order[] {
  return orders.filter((order) => {
    const createdAt = new Date(order.createdAt);
    return createdAt.getFullYear() === now.getFullYear() && createdAt.getMonth() === now.getMonth();
  });
}

export function getBestSellingMenu(orders: Order[]): MenuSalesCount | null {
  const counts = new Map<string, MenuSalesCount>();

  for (const order of orders) {
    for (const item of order.items) {
      const existing = counts.get(item.menuItemId);
      if (existing) {
        existing.quantity += item.quantity;
        existing.revenue += item.lineTotal;
      } else {
        counts.set(item.menuItemId, {
          menuItemId: item.menuItemId,
          name: item.name,
          quantity: item.quantity,
          revenue: item.lineTotal,
        });
      }
    }
  }

  let best: MenuSalesCount | null = null;
  for (const entry of counts.values()) {
    if (!best || entry.quantity > best.quantity) best = entry;
  }
  return best;
}

const UNCATEGORIZED_LABEL = "기타";
const UNCATEGORIZED_ID = "uncategorized";

export function getCategorySales(
  orders: Order[],
  menuItems: MenuItem[],
  categories: Category[]
): CategorySalesCount[] {
  const categoryIdByMenuId = new Map(menuItems.map((item) => [item.id, item.categoryId]));
  const categoryNameById = new Map(categories.map((category) => [category.id, category.name]));

  const totals = new Map<string, CategorySalesCount>();
  for (const order of orders) {
    for (const item of order.items) {
      const categoryId = categoryIdByMenuId.get(item.menuItemId) ?? UNCATEGORIZED_ID;
      const categoryName = categoryNameById.get(categoryId) ?? UNCATEGORIZED_LABEL;
      const existing = totals.get(categoryId);
      if (existing) {
        existing.quantity += item.quantity;
        existing.revenue += item.lineTotal;
      } else {
        totals.set(categoryId, { categoryId, categoryName, quantity: item.quantity, revenue: item.lineTotal });
      }
    }
  }

  return Array.from(totals.values()).sort((a, b) => b.quantity - a.quantity);
}

export function buildSalesSummary(orders: Order[], menuItems: MenuItem[], categories: Category[]): SalesSummary {
  const now = new Date();
  return {
    todayTotal: sumOrdersTotal(filterOrdersToday(orders, now)),
    weekTotal: sumOrdersTotal(filterOrdersThisWeek(orders, now)),
    monthTotal: sumOrdersTotal(filterOrdersThisMonth(orders, now)),
    bestSellingMenu: getBestSellingMenu(orders),
    salesByCategory: getCategorySales(orders, menuItems, categories),
  };
}
