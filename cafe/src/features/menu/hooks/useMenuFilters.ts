"use client";

import { useMemo, useState } from "react";
import type { MenuItem } from "@/types";

export const ALL_CATEGORY_ID = "all";

export function useMenuFilters(menuItems: MenuItem[]) {
  const [selectedCategoryId, setSelectedCategoryId] = useState<string>(ALL_CATEGORY_ID);
  const [searchQuery, setSearchQuery] = useState("");

  const filteredItems = useMemo(() => {
    const query = searchQuery.trim().toLowerCase();
    return menuItems.filter((item) => {
      const matchesCategory =
        selectedCategoryId === ALL_CATEGORY_ID || item.categoryId === selectedCategoryId;
      const matchesSearch = query === "" || item.name.toLowerCase().includes(query);
      return matchesCategory && matchesSearch;
    });
  }, [menuItems, selectedCategoryId, searchQuery]);

  return {
    selectedCategoryId,
    setSelectedCategoryId,
    searchQuery,
    setSearchQuery,
    filteredItems,
  };
}
