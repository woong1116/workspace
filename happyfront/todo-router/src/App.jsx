import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { Route, Routes } from 'react-router-dom'
import TodoList from './pages/TodoList'
import TodoDetail from './pages/TodoDetail'
import About from './pages/About'
import Home from './pages/Home'
import { Link } from 'react-router-dom'
import { NavLink } from 'react-router-dom'
import MyComponent from './pages/MyComponent'


function App() {
    const [todos, setTodos] = useState([]);

// 할 일 추가 함수
  const addTodo = (text) => {
    const newTodo = {
      id: Date.now(), // 고유한 ID 생성
      text,
      completed: false
    };
    setTodos([...todos, newTodo]);
  };

  // 할 일 삭제 함수
  const deleteTodo = (id) => {
    setTodos(todos.filter(todo => todo.id !== id));
  };

  return (<div>
    <h1>Todo App with Router</h1>

    <MyComponent />

    <Routes>
      <Route path="/" element={<TodoList todos={todos} onAdd={addTodo} onDelete={deleteTodo} />} />
      <Route path="/todos/:id" element={<TodoDetail />} />
      <Route path="/about" element={<About />} />
      <Route path="/home" element={<Home />} />
    </Routes>

    <Link to="/">todo</Link><br></br>
    <Link to={"/todos/1"}>todo detail</Link><br></br>
    <Link to="/about">about</Link><br></br>
    <Link to="/home">home</Link><br></br>

    <NavLink
     to="/"
     style={({ isActive }) => ({
      fontWeight: isActive ? 'bold' : 'normal',
      color: isActive ? 'blue' : 'black'
     })}
    >
     홈
    </NavLink>



  </div>
  );
}

export default App
