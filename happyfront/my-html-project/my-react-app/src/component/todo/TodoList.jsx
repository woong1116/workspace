// src/TodoList.jsx
function TodoList({ todos, onDelete, onToggle }) {
  if (todos.length === 0) {
    return <p className="empty">할 일이 없습니다. 추가해보세요!</p>;
  }

  return (
    <ul className="todo-list">
      {todos.map((todo) => (
        <li key={todo.id} className={todo.completed ? 'completed' : ''}>
          <input
            type="checkbox"
            checked={todo.completed}
            onChange={() => onToggle(todo.id)}
          />
          <span>{todo.text}</span>
          <button onClick={() => onDelete(todo.id)}>삭제</button>
        </li>
      ))}
    </ul>
  );
}

export default TodoList;