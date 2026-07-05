const TodoItem = ({ todo, onDelete, onToggle }) => {
  return (
    <li
      style={{
        padding: "10px",
        margin: "5px 0",
        backgroundColor: "#f5f5f5",
        display: "flex",
        alignItems: "center",
      }}
    >
      <input
        type="checkbox"
        checked={todo.completed}
        onChange={() => onToggle(todo.id)}
      />
      <span
        style={{
          textDecoration: todo.completed ? "line-through" : "none",
          flex: 1,
          marginLeft: "10px",
        }}
      >
        {todo.text}
      </span>
      <button
        onClick={() => onDelete(todo.id)}
        style={{
          padding: "5px 10px",
          backgroundColor: "#ff4444",
          color: "white",
          border: "none",
          borderRadius: "4px",
        }}
      >
        삭제
      </button>
    </li>
  );
};

export default TodoItem;
