import { useState } from "react";

function ChildButton({ onButtonClick }) {
  return (
    <button onClick={() => onButtonClick("자식 버튼 클릭됨!")}>
      클릭하세요
    </button>
  );
}

function ParentComponent() {
  const [log, setLog] = useState("");

  const handleChildClick = (message) => {
    setLog(message);
  };

  return (
    <div>
      <p>자식으로부터 받은 메시지: {log}</p>
      <ChildButton onButtonClick={handleChildClick} />
    </div>
  );
}

export default ParentComponent;
