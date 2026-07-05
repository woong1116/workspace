import { useEffect } from "react";

const Timer = () => {
  console.log("⏱️ 컴포넌트 실행 (마운트)");

  // 1. 마운트 될 때 타이머 시작!!
  //   return 에 넘겨준 함수는 컴포넌트가 언마운팅될때 실행됨!!
  useEffect(() => {
    console.log("useEffect[] 실행!!");

    const timer = setInterval(() => {
      console.log("타이머 실행중!!!");
    }, 1000);

    return () => {
      console.log("타이머 컴포넌트 언마운팅!!!");
      clearInterval(timer);
    };
  }, []);

  return (
    <div>
      <h3>타이머 작동중 ⏱️⏱️</h3>
    </div>
  );
};

export default Timer;
