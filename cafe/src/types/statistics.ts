export interface DailySales {
  date: string;
  total: number;
}

export interface MenuSalesCount {
  menuItemId: string;
  name: string;
  quantity: number;
  revenue: number;
}

export interface CategorySalesCount {
  categoryId: string;
  categoryName: string;
  quantity: number;
  revenue: number;
}

export interface SalesSummary {
  todayTotal: number;
  weekTotal: number;
  monthTotal: number;
  bestSellingMenu: MenuSalesCount | null;
  salesByCategory: CategorySalesCount[];
}
