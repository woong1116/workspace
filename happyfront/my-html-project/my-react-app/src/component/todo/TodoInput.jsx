import { useState } from "react";

const TodoInput = ({ onAdd }) => {
  const [text, setText] = useState("");

  const handleSubmit = () => {
    if (text.trim()) {
      onAdd(text);
      setText("");
    }
  };

  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      handleSubmit();
    }
  };
  return (
    <div>
      <input
        type="text"
        value={text}
        placeholder="할 일을 입력하세요."
        onChange={(e) => setText(e.target.value)}
        onKeyDown={handleKeyDown}
      ></input>

      <button onClick={handleSubmit}>할일 추가</button>
    </div>
  );
};

export default TodoInput;
