import { useState } from "react";

const UseStateExam01 = () => {
  console.log("👍 UseStateExam01 실행!!");
  //   let count = 0;  -- 리액트는 이 변수의 상태는 추적하지 않아요.
  const [count, setCount] = useState(0);
  const handleClick = () => {
    // count += 1;
    // setCount(count + 1);
    setCount((prevCount) => {
      console.log("(리액트가 관리하는 최신 state 값)prevCount::" + prevCount);
      return prevCount + 1;
    });

    console.log(count + 1);
  };
  return (
    <div>
      <p>카운트 : {count} </p>
      <button onClick={handleClick}>up</button>
    </div>
  );
};

export default UseStateExam01;
