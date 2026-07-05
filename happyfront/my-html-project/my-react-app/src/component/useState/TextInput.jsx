import { useState } from "react";

const TextInput = () => {
  console.log("👌👌TextInput 랜더링!!");
  const [text, setText] = useState("");

  const changeHandler = (e) => {
    console.log(e.target);
    setText(e.target.value);
  };

  return (
    <div>
      <input
        type="text"
        value={text}
        onChange={changeHandler}
        placeholder="텍스트를 입력하세요."
      />

      <p>입력한 내용 : {text} </p>
    </div>
  );
};

export default TextInput;
