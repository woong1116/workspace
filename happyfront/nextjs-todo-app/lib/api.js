// API 기본 URL
const API_URL = 'http://localhost:8080/api/todos';

/**
 * 전체 할 일 목록 조회
 * GET /api/todos
 */
export async function fetchTodos() {
  const response = await fetch(API_URL, {
    cache: 'no-store'  // 항상 최신 데이터 가져오기
  });

  if (!response.ok) {
    throw new Error('할 일 목록을 가져오는데 실패했습니다');
  }

  return response.json();
}

/**
 * 새 할 일 추가
 * POST /api/todos
 */
export async function createTodo(data) {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error('할 일 추가에 실패했습니다');
  }

  return response.json();
}

/**
 * 할 일 수정
 * PUT /api/todos/:id
 */
export async function updateTodo(id, data) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error('할 일 수정에 실패했습니다');
  }

  return response.json();
}

/**
 * 할 일 완료 상태 토글
 * PATCH /api/todos/:id/toggle
 */
export async function toggleTodo(id) {
  const response = await fetch(`${API_URL}/${id}/toggle`, {
    method: 'PATCH',
  });

  if (!response.ok) {
    throw new Error('상태 변경에 실패했습니다');
  }

  return response.json();
}

/**
 * 할 일 삭제
 * DELETE /api/todos/:id
 */
export async function deleteTodo(id) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'DELETE',
  });

  if (!response.ok) {
    throw new Error('할 일 삭제에 실패했습니다');
  }
}