import { useState } from "react";

const UseStateExam02 = () => {
  const [inputName, setinputName] = useState("");
  const [names, setNames] = useState(["kang", "hong", "kim"]);

  const inputChangeHandler = (e) => {
    setinputName(e.target.value);
  };

  const keyDownHandler = (e) => {
    console.log("key::: " + e.key);
    if (e.key === "Enter") {
      addHandler();
      e.target.focus();
    }
  };

  const addHandler = () => {
    setNames((prevSate) => {
      return [inputName, ...prevSate];
    });
    setinputName("");
  };

  return (
    <div>
      <input
        type="text"
        value={inputName}
        onChange={inputChangeHandler}
        onKeyDown={keyDownHandler}
      />
      <button onClick={addHandler}>입력</button>

      {/* 입력받을 이름들을 랜더링하고 싶어요.  */}
      {names.map((name, index) => (
        <p key={index}>{name}</p>
      ))}

      {/* <p>kang</p>
    <p>hong</p>
    <p>kim</p> */}
    </div>
  );
};

export default UseStateExam02;
