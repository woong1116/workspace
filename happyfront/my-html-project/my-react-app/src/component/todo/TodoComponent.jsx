// src/TodoComponent.jsx
import { useState, useEffect } from "react";
import TodoInput from "./TodoInput";
import TodoList from "./TodoList";

const API_URL = 'http://localhost:8080/api/todos';

function TodoComponent() {
  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 컴포넌트 마운트 시 데이터 가져오기
  useEffect(() => {
    fetchTodos();
  }, []);

  // READ: 목록 조회
  const fetchTodos = async () => {
    try {
      setLoading(true);
      setError(null);

      const response = await fetch(API_URL);
      if (!response.ok) throw new Error('서버 응답 오류');

      const data = await response.json();
      setTodos(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  // CREATE: 추가
  const addTodo = async (text) => {
    try {
      const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ text }),
      });

      if (!response.ok) throw new Error('추가 실패');

      const newTodo = await response.json();
      setTodos([...todos, newTodo]);
    } catch (err) {
      alert('할 일 추가에 실패했습니다.');
    }
  };

  // UPDATE: 완료 토글
  const toggleTodo = async (id) => {
    const todo = todos.find((t) => t.id === id);

    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ ...todo, completed: !todo.completed }),
      });

      if (!response.ok) throw new Error('수정 실패');

      const updatedTodo = await response.json();
      setTodos(todos.map((t) => (t.id === id ? updatedTodo : t)));
    } catch (err) {
      alert('상태 변경에 실패했습니다.');
    }
  };

  // DELETE: 삭제
  const deleteTodo = async (id) => {
    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
      });

      if (!response.ok) throw new Error('삭제 실패');

      setTodos(todos.filter((todo) => todo.id !== id));
    } catch (err) {
      alert('할 일 삭제에 실패했습니다.');
    }
  };

  if (loading) return <div className="loading">로딩 중...</div>;
  if (error) return (
    <div className="error">
      <p>에러: {error}</p>
      <button onClick={fetchTodos}>다시 시도</button>
    </div>
  );

  return (
    <div className="todo-app">
      <h1>Todo App</h1>
      <TodoInput onAdd={addTodo} />
      <TodoList
        todos={todos}
        onDelete={deleteTodo}
        onToggle={toggleTodo}
      />
    </div>
  );
}

export default TodoComponent;