import { useState } from 'react'
import './App.css'

function Input({ onAdd }) {
  const [text, setText] = useState('')

  const handleAdd = () => {
    const trimmed = text.trim()
    if (!trimmed) return
    onAdd(trimmed)
    setText('')
  }

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') handleAdd()
  }

  return (
    <div className="todo-input">
      <input
        type="text"
        value={text}
        onChange={(e) => setText(e.target.value)}
        onKeyDown={handleKeyDown}
        placeholder="할 일을 입력하세요"
      />
      <button type="button" onClick={handleAdd}>
        추가
      </button>
    </div>
  )
}

function TodoItem({ index, todo, onToggle, onDelete }) {
  const handleDelete = () => {
    if (window.confirm('정말 삭제하시겠습니까?')) {
      onDelete(todo.id)
    }
  }

  return (
    <li className="todo-item">
      <span className="todo-index">{index + 1}.</span>
      <input
        type="checkbox"
        checked={todo.completed}
        onChange={() => onToggle(todo.id)}
      />
      <span className={`todo-text${todo.completed ? ' completed' : ''}`}>
        {todo.text}
      </span>
      <button type="button" onClick={handleDelete}>
        삭제
      </button>
    </li>
  )
}

function TodoList({ todos, onToggle, onDelete }) {
  if (todos.length === 0) {
    return <p className="todo-empty">할 일이 없습니다.</p>
  }

  return (
    <ul className="todo-list">
      {todos.map((todo, index) => (
        <TodoItem
          key={todo.id}
          index={index}
          todo={todo}
          onToggle={onToggle}
          onDelete={onDelete}
        />
      ))}
    </ul>
  )
}

function App() {
  const [todos, setTodos] = useState([])

  const handleAdd = (text) => {
    const newTodo = {
      id: Date.now(),
      text,
      completed: false,
    }
    setTodos((prev) => [...prev, newTodo])
  }

  const handleDelete = (id) => {
    setTodos((prev) => prev.filter((todo) => todo.id !== id))
  }

  const handleToggle = (id) => {
    setTodos((prev) =>
      prev.map((todo) =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    )
  }

  return (
    <div className="todo-app">
      <h1>Todo List</h1>
      <Input onAdd={handleAdd} />
      <TodoList todos={todos} onToggle={handleToggle} onDelete={handleDelete} />
    </div>
  )
}

export default App
