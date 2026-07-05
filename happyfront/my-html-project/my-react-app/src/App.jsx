import Hello from "./Hello";
import Welcome from "./Welcome";
import ChildrenExam from "./component/exam/ChildrenExam";
import UseStateExam01 from "./component/useState/UseStateExam01";
import Time from "./component/useState/Time";
import TextInput from "./component/useState/TextInput";
import UseStateExam02 from "./component/useState/UseStateExam02";
import LoginStatus from "./component/exam/LoginStatus";
import FruitList from "./component/exam/FruitList";
import Counter from "./component/exam/Counter";
import TodoComponent from "./component/todo/TodoComponent";
import UseRefExam01 from "./component/useRef/UseRefExam01";
import UseEffectExam from "./component/useEffect/UseEffectExam";

function App() {
  console.log("App 실행")
  return (
    <div>
      {/* <ChildrenExam /> */}
      {/* <UseStateExam01 /> */}
      {/* <Time />
      </div> */}
      {/* <TextInput /> */}
      {/* <UseStateExam02 /> */}
      {/* <LoginStatus />
      <FruitList /> */}
      {/* <Counter /> */}
      <TodoComponent />
      {/* <UseRefExam01 /> */}
      {/* <UseEffectExam /> */}
    </div>
  );
}

export default App
