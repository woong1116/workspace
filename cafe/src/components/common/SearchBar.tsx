import { forwardRef } from "react";
import { Search } from "lucide-react";
import { cn } from "@/utils/cn";

interface SearchBarProps {
  value: string;
  onChange: (value: string) => void;
  placeholder?: string;
  className?: string;
}

export const SearchBar = forwardRef<HTMLInputElement, SearchBarProps>(
  ({ value, onChange, placeholder = "메뉴 검색 (Ctrl+F)", className }, ref) => {
    return (
      <div className={cn("relative", className)}>
        <Search
          size={16}
          className="pointer-events-none absolute left-3 top-1/2 -translate-y-1/2 text-slate-400"
        />
        <input
          ref={ref}
          value={value}
          onChange={(event) => onChange(event.target.value)}
          placeholder={placeholder}
          className="h-10 w-full rounded-xl border border-slate-300 bg-white pl-9 pr-3 text-sm text-slate-900 outline-none transition-colors focus:border-indigo-500 focus:ring-2 focus:ring-indigo-500/30 dark:border-slate-600 dark:bg-slate-800 dark:text-slate-100"
        />
      </div>
    );
  }
);

SearchBar.displayName = "SearchBar";
