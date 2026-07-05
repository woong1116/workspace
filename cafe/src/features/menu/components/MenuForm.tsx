"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import type { z } from "zod";
import type { Category, MenuFormValues, MenuItem } from "@/types";
import { Button } from "@/components/ui/Button";
import { Input } from "@/components/ui/Input";
import { menuFormSchema } from "../schema";

type MenuFormInput = z.input<typeof menuFormSchema>;
type MenuFormOutput = z.output<typeof menuFormSchema>;

interface MenuFormProps {
  categories: Category[];
  defaultValues?: MenuItem;
  onSubmit: (values: MenuFormValues) => void;
  onCancel: () => void;
}

export function MenuForm({ categories, defaultValues, onSubmit, onCancel }: MenuFormProps) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<MenuFormInput, unknown, MenuFormOutput>({
    resolver: zodResolver(menuFormSchema),
    defaultValues: {
      name: defaultValues?.name ?? "",
      price: defaultValues?.price ?? 0,
      categoryId: defaultValues?.categoryId ?? categories[0]?.id ?? "",
      description: defaultValues?.description ?? "",
      isSoldOut: defaultValues?.isSoldOut ?? false,
    },
  });

  const submit = handleSubmit((values) => {
    onSubmit({ ...values, description: values.description || undefined });
  });

  return (
    <form onSubmit={submit} className="flex flex-col gap-4">
      <Input label="메뉴명" placeholder="아메리카노" error={errors.name?.message} {...register("name")} />
      <Input
        label="가격"
        type="number"
        placeholder="4500"
        error={errors.price?.message}
        {...register("price")}
      />
      <div className="flex flex-col gap-1.5">
        <label className="text-sm font-medium text-slate-700 dark:text-slate-300">카테고리</label>
        <select
          className="h-10 rounded-xl border border-slate-300 bg-white px-3 text-sm text-slate-900 outline-none focus:border-indigo-500 focus:ring-2 focus:ring-indigo-500/30 dark:border-slate-600 dark:bg-slate-800 dark:text-slate-100"
          {...register("categoryId")}
        >
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>
        {errors.categoryId && <p className="text-xs text-red-500">{errors.categoryId.message}</p>}
      </div>
      <div className="flex flex-col gap-1.5">
        <label className="text-sm font-medium text-slate-700 dark:text-slate-300">설명 (선택)</label>
        <textarea
          rows={2}
          className="rounded-xl border border-slate-300 bg-white px-3 py-2 text-sm text-slate-900 outline-none focus:border-indigo-500 focus:ring-2 focus:ring-indigo-500/30 dark:border-slate-600 dark:bg-slate-800 dark:text-slate-100"
          {...register("description")}
        />
      </div>
      <label className="flex items-center gap-2 text-sm text-slate-600 dark:text-slate-300">
        <input type="checkbox" className="h-4 w-4 rounded border-slate-300" {...register("isSoldOut")} />
        품절 처리
      </label>
      <div className="mt-2 flex justify-end gap-2">
        <Button type="button" variant="ghost" onClick={onCancel}>
          취소
        </Button>
        <Button type="submit">{defaultValues ? "수정" : "추가"}</Button>
      </div>
    </form>
  );
}
