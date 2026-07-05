import { useRef } from "react";

const UseRefExam02 = () => {
  const inputRef = useRef(null);
  const clickHandler = () => {
    console.log(inputRef.current.value);
    console.log(inputRef.current.placeholder);
  };
  return (
    <div>
      <h2>useRef로 DOM 찾기</h2>
      <input type="text" placeholder="이름을 입력하세요." ref={inputRef} />
      <button onClick={clickHandler}>입력</button>
    </div>
  );
};

export default UseRefExam02;
