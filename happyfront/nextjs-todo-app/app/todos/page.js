// 서버 컴포넌트 (기본) - 'use client' 없음!
import { fetchTodos } from '@/lib/api';
import TodoList from './components/TodoList';
import AddTodoForm from './components/AddTodoForm';
import styles from './todos.module.css';

// ⭐ async 함수 사용 가능! (서버 컴포넌트이므로)
async function TodosPage() {
  // 서버에서 직접 데이터 가져오기
  const todos = await fetchTodos();

  return (
    <div className={styles.container}>
      <h1 className={styles.title}>📝 Todo App (Next.js)</h1>

      {/* 클라이언트 컴포넌트: 입력 폼 */}
      <AddTodoForm />

      {/* 서버 컴포넌트: 목록 표시 */}
      <TodoList todos={todos} />

      {todos.length === 0 && (
        <p className={styles.empty}>할 일이 없습니다. 추가해보세요!</p>
      )}
    </div>
  );
}

export default TodosPage;