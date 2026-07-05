export interface Category {
  id: string;
  name: string;
  order: number;
  createdAt: string;
}

export interface MenuItem {
  id: string;
  name: string;
  price: number;
  categoryId: string;
  description?: string;
  isSoldOut: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface MenuFormValues {
  name: string;
  price: number;
  categoryId: string;
  description?: string;
  isSoldOut: boolean;
}

export interface CategoryFormValues {
  name: string;
}
