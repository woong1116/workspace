// 서버 컴포넌트
import styles from '../todos.module.css';

function TodoList({ todos }) {
  return (
    <ul className={styles.list}>
      {todos.map(todo => (
        <TodoItem key={todo.id} todo={todo} />
      ))}
    </ul>
  );
}

export default TodoList;