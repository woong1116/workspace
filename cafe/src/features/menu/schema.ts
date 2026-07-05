import { z } from "zod";

export const menuFormSchema = z.object({
  name: z.string().min(1, "메뉴명을 입력하세요.").max(30, "30자 이내로 입력하세요."),
  price: z.coerce.number().int("정수로 입력하세요.").min(0, "0원 이상이어야 합니다."),
  categoryId: z.string().min(1, "카테고리를 선택하세요."),
  description: z.string().max(100, "100자 이내로 입력하세요.").optional().or(z.literal("")),
  isSoldOut: z.boolean(),
});

export const categoryFormSchema = z.object({
  name: z.string().min(1, "카테고리명을 입력하세요.").max(15, "15자 이내로 입력하세요."),
});
