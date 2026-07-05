"use client";

import { useRef, useState } from "react";
import { Plus, Settings2, ShoppingCart } from "lucide-react";
import { EmptyState } from "@/components/common/EmptyState";
import { SearchBar } from "@/components/common/SearchBar";
import { Skeleton } from "@/components/common/Skeleton";
import { useToast } from "@/components/common/Toast";
import { Button } from "@/components/ui/Button";
import { Drawer } from "@/components/ui/Drawer";
import { Modal } from "@/components/ui/Modal";
import { Cart } from "@/features/cart/components/Cart";
import { CategoryButton } from "@/features/menu/components/CategoryButton";
import { CategoryForm } from "@/features/menu/components/CategoryForm";
import { MenuCard } from "@/features/menu/components/MenuCard";
import { MenuForm } from "@/features/menu/components/MenuForm";
import { ALL_CATEGORY_ID, useMenuFilters } from "@/features/menu/hooks/useMenuFilters";
import { PaymentModal } from "@/features/payment/components/PaymentModal";
import { ReceiptModal } from "@/features/receipt/components/ReceiptModal";
import { useKeyboardShortcut } from "@/hooks/useKeyboardShortcut";
import { useCartStore } from "@/store/cartStore";
import { useMenuStore } from "@/store/menuStore";
import { useOrderStore } from "@/store/orderStore";
import type { Category, MenuItem, Order, PaymentMethod } from "@/types";
import { calculateCartSummary } from "@/utils/calculations";
import { generateId, generateOrderNumber } from "@/utils/id";

interface MenuModalState {
  open: boolean;
  editing?: MenuItem;
}

interface CategoryModalState {
  open: boolean;
  editing?: Category;
}

export default function Home() {
  const {
    categories,
    menuItems,
    hasHydrated,
    addCategory,
    updateCategory,
    deleteCategory,
    addMenuItem,
    updateMenuItem,
    deleteMenuItem,
    reorderMenuItems,
  } = useMenuStore();
  const { showToast } = useToast();
  const { items: cartItems, discountRate, addItem, clearCart } = useCartStore();
  const { addOrder } = useOrderStore();
  const searchInputRef = useRef<HTMLInputElement>(null);

  const { selectedCategoryId, setSelectedCategoryId, searchQuery, setSearchQuery, filteredItems } =
    useMenuFilters(menuItems);

  const [isManaging, setIsManaging] = useState(false);
  const [hasManagingChanges, setHasManagingChanges] = useState(false);
  const [isCartDrawerOpen, setIsCartDrawerOpen] = useState(false);
  const [isPaymentModalOpen, setIsPaymentModalOpen] = useState(false);
  const [completedOrder, setCompletedOrder] = useState<Order | null>(null);
  const [isReceiptModalOpen, setIsReceiptModalOpen] = useState(false);
  const [menuModalState, setMenuModalState] = useState<MenuModalState>({ open: false });
  const [categoryModalState, setCategoryModalState] = useState<CategoryModalState>({ open: false });

  useKeyboardShortcut([{ key: "f", ctrlOrCmd: true, handler: () => searchInputRef.current?.focus() }]);

  const cartItemCount = cartItems.reduce((sum, item) => sum + item.quantity, 0);

  function handleSelectMenuItem(menuItem: MenuItem) {
    if (isManaging) {
      setMenuModalState({ open: true, editing: menuItem });
      return;
    }
    addItem(menuItem);
    showToast(`${menuItem.name} 담았습니다.`);
  }

  function handleCheckout() {
    if (cartItems.length === 0) return;
    setIsPaymentModalOpen(true);
  }

  function handlePaymentComplete(method: PaymentMethod) {
    const summary = calculateCartSummary(cartItems, discountRate);
    const order: Order = {
      id: generateId(),
      orderNumber: generateOrderNumber(),
      items: cartItems.map((cartItem) => ({
        menuItemId: cartItem.menuItem.id,
        name: cartItem.menuItem.name,
        price: cartItem.menuItem.price,
        quantity: cartItem.quantity,
        lineTotal: cartItem.menuItem.price * cartItem.quantity,
      })),
      subtotal: summary.subtotal,
      discount: summary.discount,
      total: summary.total,
      paymentMethod: method,
      createdAt: new Date().toISOString(),
    };

    addOrder(order);
    clearCart();
    setIsPaymentModalOpen(false);
    setIsCartDrawerOpen(false);
    setCompletedOrder(order);
    setIsReceiptModalOpen(true);
    showToast("결제가 완료되었습니다.");
  }

  function handleMenuSubmit(values: Parameters<typeof addMenuItem>[0]) {
    if (menuModalState.editing) {
      updateMenuItem(menuModalState.editing.id, values);
      showToast("메뉴를 수정했습니다.");
    } else {
      addMenuItem(values);
      showToast("메뉴를 추가했습니다.");
    }
    setHasManagingChanges(true);
    setMenuModalState({ open: false });
  }

  function handleMenuDelete(menuItem: MenuItem) {
    if (!window.confirm(`'${menuItem.name}'을(를) 삭제할까요?`)) return;
    deleteMenuItem(menuItem.id);
    setHasManagingChanges(true);
    showToast("메뉴를 삭제했습니다.", "info");
  }

  function handleMenuReorder(draggedId: string, targetId: string) {
    reorderMenuItems(draggedId, targetId);
    setHasManagingChanges(true);
  }

  function handleCategorySubmit(values: Parameters<typeof addCategory>[0]) {
    if (categoryModalState.editing) {
      updateCategory(categoryModalState.editing.id, values);
      showToast("카테고리를 수정했습니다.");
    } else {
      addCategory(values);
      showToast("카테고리를 추가했습니다.");
    }
    setHasManagingChanges(true);
    setCategoryModalState({ open: false });
  }

  function handleCategoryDelete(category: Category) {
    const result = deleteCategory(category.id);
    if (!result.success) {
      showToast(result.message ?? "삭제할 수 없습니다.", "error");
      return;
    }
    setHasManagingChanges(true);
    showToast("카테고리를 삭제했습니다.", "info");
  }

  function handleToggleManaging() {
    if (isManaging && hasManagingChanges) {
      const confirmed = window.confirm("변경 사항이 있습니다. 메뉴 관리를 종료하시겠습니까?");
      if (!confirmed) return;
    }
    setIsManaging((prev) => !prev);
    setHasManagingChanges(false);
  }

  if (!hasHydrated) {
    return (
      <div className="grid grid-cols-2 gap-4 sm:grid-cols-3 xl:grid-cols-4">
        {Array.from({ length: 8 }).map((_, index) => (
          <Skeleton key={index} className="h-32" />
        ))}
      </div>
    );
  }

  return (
    <div className="flex flex-1 gap-6">
      <div className="flex flex-1 flex-col gap-4">
        <div className="flex flex-wrap items-center justify-between gap-3">
          <SearchBar
            ref={searchInputRef}
            value={searchQuery}
            onChange={setSearchQuery}
            className="max-w-xs"
          />
          <Button variant={isManaging ? "primary" : "outline"} size="sm" onClick={handleToggleManaging}>
            <Settings2 size={16} />
            {isManaging ? "관리 종료" : "메뉴 관리"}
          </Button>
        </div>

        <div className="flex flex-wrap items-center gap-2">
          <CategoryButton
            label="전체"
            isActive={selectedCategoryId === ALL_CATEGORY_ID}
            onClick={() => setSelectedCategoryId(ALL_CATEGORY_ID)}
          />
          {categories.map((category) => (
            <CategoryButton
              key={category.id}
              label={category.name}
              isActive={selectedCategoryId === category.id}
              onClick={() => setSelectedCategoryId(category.id)}
              isEditable={isManaging}
              onEdit={() => setCategoryModalState({ open: true, editing: category })}
              onDelete={() => handleCategoryDelete(category)}
            />
          ))}
          {isManaging && (
            <button
              onClick={() => setCategoryModalState({ open: true })}
              className="flex items-center gap-1 rounded-xl border border-dashed border-slate-300 px-3 py-2 text-sm text-slate-400 transition-colors hover:border-indigo-400 hover:text-indigo-500 dark:border-slate-600"
            >
              <Plus size={14} /> 카테고리 추가
            </button>
          )}
        </div>

        {filteredItems.length === 0 ? (
          <EmptyState
            title="메뉴가 없습니다"
            description={
              isManaging ? "메뉴 추가 버튼으로 새 메뉴를 등록하세요." : "검색어나 카테고리를 확인해 주세요."
            }
            action={
              isManaging ? (
                <Button size="sm" onClick={() => setMenuModalState({ open: true })}>
                  <Plus size={16} /> 메뉴 추가
                </Button>
              ) : undefined
            }
          />
        ) : (
          <div className="grid grid-cols-2 gap-4 sm:grid-cols-3 xl:grid-cols-4">
            {filteredItems.map((menuItem) => (
              <MenuCard
                key={menuItem.id}
                menuItem={menuItem}
                onSelect={handleSelectMenuItem}
                isEditable={isManaging}
                onEdit={(item) => setMenuModalState({ open: true, editing: item })}
                onDelete={handleMenuDelete}
                onReorder={handleMenuReorder}
              />
            ))}
            {isManaging && (
              <button
                onClick={() => setMenuModalState({ open: true })}
                className="flex min-h-32 flex-col items-center justify-center gap-2 rounded-xl border border-dashed border-slate-300 text-slate-400 transition-colors hover:border-indigo-400 hover:text-indigo-500 dark:border-slate-600"
              >
                <Plus size={20} />
                메뉴 추가
              </button>
            )}
          </div>
        )}

        <Modal
          isOpen={menuModalState.open}
          onClose={() => setMenuModalState({ open: false })}
          title={menuModalState.editing ? "메뉴 수정" : "메뉴 추가"}
        >
          <MenuForm
            categories={categories}
            defaultValues={menuModalState.editing}
            onSubmit={handleMenuSubmit}
            onCancel={() => setMenuModalState({ open: false })}
          />
        </Modal>

        <Modal
          isOpen={categoryModalState.open}
          onClose={() => setCategoryModalState({ open: false })}
          title={categoryModalState.editing ? "카테고리 수정" : "카테고리 추가"}
        >
          <CategoryForm
            defaultValues={categoryModalState.editing}
            onSubmit={handleCategorySubmit}
            onCancel={() => setCategoryModalState({ open: false })}
          />
        </Modal>
      </div>

      <aside className="hidden w-80 shrink-0 rounded-xl bg-white p-4 shadow-lg xl:flex dark:bg-slate-900">
        <Cart onCheckout={handleCheckout} />
      </aside>

      <button
        onClick={() => setIsCartDrawerOpen(true)}
        className="fixed bottom-20 right-4 flex h-14 w-14 items-center justify-center rounded-full bg-indigo-600 text-white shadow-lg transition-transform hover:scale-105 xl:hidden"
        aria-label="장바구니 열기"
      >
        <ShoppingCart size={22} />
        {cartItemCount > 0 && (
          <span className="absolute -right-1 -top-1 flex h-5 min-w-5 items-center justify-center rounded-full bg-red-500 px-1 text-xs font-bold">
            {cartItemCount}
          </span>
        )}
      </button>

      <Drawer isOpen={isCartDrawerOpen} onClose={() => setIsCartDrawerOpen(false)} title="장바구니">
        <Cart onCheckout={handleCheckout} />
      </Drawer>

      <PaymentModal
        isOpen={isPaymentModalOpen}
        onClose={() => setIsPaymentModalOpen(false)}
        totalAmount={calculateCartSummary(cartItems, discountRate).total}
        onComplete={handlePaymentComplete}
      />

      <ReceiptModal
        isOpen={isReceiptModalOpen}
        onClose={() => setIsReceiptModalOpen(false)}
        order={completedOrder}
      />
    </div>
  );
}
