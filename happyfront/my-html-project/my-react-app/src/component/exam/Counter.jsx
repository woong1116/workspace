import { useState } from "react";

function Button({ value, onButtonClick, disabled }) {
  return (
    <button
      type="button"
      onClick={onButtonClick}
      disabled={disabled}
      style={{
        margin: "5px",
        padding: "10px 16px",
        border: "1px solid #333",
        borderRadius: "6px",
        cursor: disabled ? "not-allowed" : "pointer",
        opacity: disabled ? 0.5 : 1,
      }}
    >
      {value}
    </button>
  );
}

function Counter() {
  const [count, setCount] = useState(0);
  const [message, setMessage] = useState("점수를 올려보세요.");

  const increment = () => {
    setCount((prevCount) => prevCount + 1);
    setMessage("1점 증가했습니다.");
  };

  const decrement = () => {
    setCount((prevCount) => prevCount - 1);
    setMessage("1점 감소했습니다.");
  };

  const addRandomBonus = () => {
    const bonus = Math.floor(Math.random() * 10) + 1;

    setCount((prevCount) => prevCount + bonus);
    setMessage(`${bonus}점 보너스를 받았습니다.`);
  };

  const reset = () => {
    setCount(0);
    setMessage("점수가 초기화되었습니다.");
  };

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <h2>점수 게임</h2>

      <p style={{ fontSize: "2em", margin: "10px" }}>{count}</p>
      <p>{message}</p>

      {count > 0 && <p style={{ color: "blue" }}>양수입니다!</p>}
      {count < 0 && <p style={{ color: "red" }}>음수입니다!</p>}
      {count === 0 && <p style={{ color: "gray" }}>0입니다.</p>}

      <Button value="증가" onButtonClick={increment} />
      <Button value="감소" onButtonClick={decrement} disabled={count === 0} />
      <Button value="랜덤 보너스" onButtonClick={addRandomBonus} />
      <Button value="초기화" onButtonClick={reset} />
    </div>
  );
}

export default Counter;
