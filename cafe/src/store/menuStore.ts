import { create } from "zustand";
import { createJSONStorage, persist } from "zustand/middleware";
import type { Category, CategoryFormValues, MenuFormValues, MenuItem } from "@/types";
import { DEFAULT_CATEGORIES, STORAGE_KEYS } from "@/utils/constants";
import { generateId } from "@/utils/id";

interface DeleteResult {
  success: boolean;
  message?: string;
}

interface MenuState {
  categories: Category[];
  menuItems: MenuItem[];
  hasHydrated: boolean;
  setHasHydrated: (hasHydrated: boolean) => void;
  addCategory: (values: CategoryFormValues) => void;
  updateCategory: (id: string, values: CategoryFormValues) => void;
  deleteCategory: (id: string) => DeleteResult;
  addMenuItem: (values: MenuFormValues) => void;
  updateMenuItem: (id: string, values: MenuFormValues) => void;
  deleteMenuItem: (id: string) => void;
  toggleSoldOut: (id: string) => void;
  reorderMenuItems: (draggedId: string, targetId: string) => void;
}

function createDefaultCategories(): Category[] {
  const now = new Date().toISOString();
  return DEFAULT_CATEGORIES.map((category, index) => ({
    id: generateId(),
    name: category.name,
    order: index,
    createdAt: now,
  }));
}

function createDefaultMenuItems(categories: Category[]): MenuItem[] {
  const now = new Date().toISOString();
  const [coffee, drink, dessert] = categories;

  const seeds: Array<Omit<MenuItem, "id" | "createdAt" | "updatedAt">> = [
    { name: "아메리카노", price: 4000, categoryId: coffee.id, isSoldOut: false },
    { name: "카페라떼", price: 4500, categoryId: coffee.id, isSoldOut: false },
    { name: "카푸치노", price: 4800, categoryId: coffee.id, isSoldOut: false },
    { name: "자몽에이드", price: 5500, categoryId: drink.id, isSoldOut: false },
    { name: "레몬에이드", price: 5500, categoryId: drink.id, isSoldOut: false },
    { name: "티라미수", price: 6500, categoryId: dessert.id, isSoldOut: false },
  ];

  return seeds.map((seed) => ({ ...seed, id: generateId(), createdAt: now, updatedAt: now }));
}

const initialCategories = createDefaultCategories();

export const useMenuStore = create<MenuState>()(
  persist(
    (set, get) => ({
      categories: initialCategories,
      menuItems: createDefaultMenuItems(initialCategories),
      hasHydrated: false,
      setHasHydrated: (hasHydrated) => set({ hasHydrated }),

      addCategory: (values) => {
        const now = new Date().toISOString();
        set((state) => ({
          categories: [
            ...state.categories,
            { id: generateId(), name: values.name, order: state.categories.length, createdAt: now },
          ],
        }));
      },

      updateCategory: (id, values) => {
        set((state) => ({
          categories: state.categories.map((category) =>
            category.id === id ? { ...category, name: values.name } : category
          ),
        }));
      },

      deleteCategory: (id) => {
        const hasMenuItems = get().menuItems.some((item) => item.categoryId === id);
        if (hasMenuItems) {
          return { success: false, message: "메뉴가 있는 카테고리는 삭제할 수 없습니다." };
        }
        set((state) => ({
          categories: state.categories.filter((category) => category.id !== id),
        }));
        return { success: true };
      },

      addMenuItem: (values) => {
        const now = new Date().toISOString();
        set((state) => ({
          menuItems: [
            ...state.menuItems,
            {
              id: generateId(),
              name: values.name,
              price: values.price,
              categoryId: values.categoryId,
              description: values.description,
              isSoldOut: values.isSoldOut,
              createdAt: now,
              updatedAt: now,
            },
          ],
        }));
      },

      updateMenuItem: (id, values) => {
        const now = new Date().toISOString();
        set((state) => ({
          menuItems: state.menuItems.map((item) =>
            item.id === id
              ? {
                  ...item,
                  name: values.name,
                  price: values.price,
                  categoryId: values.categoryId,
                  description: values.description,
                  isSoldOut: values.isSoldOut,
                  updatedAt: now,
                }
              : item
          ),
        }));
      },

      deleteMenuItem: (id) => {
        set((state) => ({
          menuItems: state.menuItems.filter((item) => item.id !== id),
        }));
      },

      toggleSoldOut: (id) => {
        set((state) => ({
          menuItems: state.menuItems.map((item) =>
            item.id === id
              ? { ...item, isSoldOut: !item.isSoldOut, updatedAt: new Date().toISOString() }
              : item
          ),
        }));
      },

      reorderMenuItems: (draggedId, targetId) => {
        if (draggedId === targetId) return;
        set((state) => {
          const items = [...state.menuItems];
          const fromIndex = items.findIndex((item) => item.id === draggedId);
          const toIndex = items.findIndex((item) => item.id === targetId);
          if (fromIndex === -1 || toIndex === -1) return state;

          const [dragged] = items.splice(fromIndex, 1);
          items.splice(toIndex, 0, dragged);
          return { menuItems: items };
        });
      },
    }),
    {
      name: STORAGE_KEYS.MENU,
      storage: createJSONStorage(() => localStorage),
      onRehydrateStorage: () => (state) => {
        state?.setHasHydrated(true);
      },
    }
  )
);
