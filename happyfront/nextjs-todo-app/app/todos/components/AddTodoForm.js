"use client";

import { useState } from "react";

export default function AddTodoForm() {
  const [text, setText] = useState("");
  return (
    <form>
      <input
        type="text"
        value={text}
        onChange={(e) => e.target.value}
        placeholder="할일을 입력하세요"
      />
      <button>할일추가</button>
    </form>
  );
}
