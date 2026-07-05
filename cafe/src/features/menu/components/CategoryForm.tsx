"use client";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import type { z } from "zod";
import type { Category, CategoryFormValues } from "@/types";
import { Button } from "@/components/ui/Button";
import { Input } from "@/components/ui/Input";
import { categoryFormSchema } from "../schema";

type CategoryFormInput = z.infer<typeof categoryFormSchema>;

interface CategoryFormProps {
  defaultValues?: Category;
  onSubmit: (values: CategoryFormValues) => void;
  onCancel: () => void;
}

export function CategoryForm({ defaultValues, onSubmit, onCancel }: CategoryFormProps) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<CategoryFormInput>({
    resolver: zodResolver(categoryFormSchema),
    defaultValues: { name: defaultValues?.name ?? "" },
  });

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="flex flex-col gap-4">
      <Input label="카테고리명" placeholder="커피" error={errors.name?.message} {...register("name")} />
      <div className="mt-2 flex justify-end gap-2">
        <Button type="button" variant="ghost" onClick={onCancel}>
          취소
        </Button>
        <Button type="submit">{defaultValues ? "수정" : "추가"}</Button>
      </div>
    </form>
  );
}
