import { useRef, useState } from "react";

const UseRefExam01 = () => {
  console.log("😍 UseRefExam01컴포넌트 랜더링!!!");
  let countLet = 0;
  const [countState, setCountState] = useState(0);
  const countRef = useRef(0);

  const increaseState = () => {
    setCountState(countState + 1);
    console.log("useState :::: " + (countState + 1));
  };
  const increaseLet = () => {
    countLet++;
    console.log("Let::::" + countLet);
  };
  const increaseRef = () => {
    countRef.current++;
    console.log("useRef::::" + countRef.current);
  };
  return (
    <div>
      <p>useState:: {countState}</p>
      <p>Let :: {countLet}</p>
      <p>useRef :: {countRef.current}</p>

      <button onClick={increaseState}>useState UP</button>
      <button onClick={increaseLet}>Let UP</button>
      <button onClick={increaseRef}>useRef UP</button>
    </div>
  );
};

export default UseRefExam01;
