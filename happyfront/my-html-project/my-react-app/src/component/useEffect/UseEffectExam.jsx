import { useEffect, useState } from "react";

const UseEffectExam = () => {
  console.log("😍 UseEffectExam 컴포넌트 실행!!");
  const [count, setCount] = useState(0);
  const [text, setText] = useState("");

  //   1. 의존성 배열 없음.  매번 랜더링시마다 실행!!  (거의 안쓴다.)
  useEffect(() => {
    console.log("렌더링시에 매번 수행됨!!!");
  });
  // 2. 의존성 배열이 [] 빈 배열 일 경우!!  -- 딱 한 번 만 실행됨!!
  useEffect(() => {
    console.log("해당 컴포넌트가 처음(마운트) 실행될 때 딱 한 번만 수행됨!!!");
  }, []);
  //3. 의존성배열 [count] : count 값이 바뀔 때 만 실행!!
  useEffect(() => {
    console.log("count가 변경 될 때 실행!!!");
  }, [count]);

  //4. 의존성배열 [text] : text 값이 바뀔 때 만 실행!!
  useEffect(() => {
    console.log("text 변경 될 때 실행!!!");
  }, [text]);

  return (
    <div>
      <h2>useEffect exam</h2>

      <div>
        <span>count : {count}</span>
        <button onClick={() => setCount(count + 1)}>count 증가</button>
      </div>

      <div>
        <span>Text : {text}</span>
        <input
          type="text"
          value={text}
          onChange={(e) => setText(e.target.value)}
          placeholder="글자를 입력해주세요."
        />
      </div>
    </div>
  );
};

export default UseEffectExam;
