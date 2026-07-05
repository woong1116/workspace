import { useState } from "react";
import Timer from "./Timer";

const TimerToggle = () => {
  const [showTimer, setShowTimer] = useState(false);

  return (
    <div>
      <button onClick={() => setShowTimer(!showTimer)}>
        {showTimer ? "타이머 숨기기" : "타이머 보기"}
      </button>

      {showTimer && <Timer />}
    </div>
  );
};

export default TimerToggle;
