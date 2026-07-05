import { useState } from "react";

const Time = () => {
  const [time, setTime] = useState(1);

  const updateHandler = () => {
    setTime((prevTime) => (prevTime >= 12 ? 1 : prevTime + 1));
  };
  return (
    <div>
      <span>시간 : {time} 시</span>
      <button onClick={updateHandler}>up</button>
    </div>
  );
};

export default Time;
