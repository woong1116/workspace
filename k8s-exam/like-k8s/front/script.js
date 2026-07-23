// 백엔드 API URL 설정
// 로컬 개발: http://localhost:30081
// Kubernetes (NodePort): http://localhost:30081
const API_URL = window.API_URL || "http://localhost:30081";

// 시간 업데이트 함수
function updateTime() {
  const now = new Date();
  const options = {
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit",
    hour12: false,
  };
  document.getElementById("time").textContent = now.toLocaleString(
    "ko-KR",
    options
  );
}

// 백엔드 헬스 체크
async function checkBackendHealth() {
  const statusEl = document.getElementById("api-status");
  try {
    const response = await fetch(`${API_URL}/api/health`);
    const data = await response.json();
    statusEl.textContent = `✅ ${data.message}`;
    statusEl.className = "success";
    return true;
  } catch (error) {
    statusEl.textContent = "❌ 백엔드 연결 실패";
    statusEl.className = "error";
    console.error("Health check failed:", error);
    return false;
  }
}

// 사용자 목록 로드
async function loadUsers() {
  const tbody = document.getElementById("userTableBody");
  try {
    const response = await fetch(`${API_URL}/api/users`);
    const users = await response.json();

    tbody.innerHTML = "";

    if (users.length === 0) {
      tbody.innerHTML =
        '<tr><td colspan="5" style="text-align:center;">등록된 사용자가 없습니다.</td></tr>';
      return;
    }

    users.forEach((user) => {
      const row = document.createElement("tr");
      const createdAt = user.createdAt
        ? new Date(user.createdAt).toLocaleString("ko-KR")
        : "-";
      row.innerHTML = `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${createdAt}</td>
                <td><button class="delete-btn" onclick="deleteUser(${user.id})">삭제</button></td>
            `;
      tbody.appendChild(row);
    });
  } catch (error) {
    tbody.innerHTML =
      '<tr><td colspan="5" class="error" style="text-align:center;">사용자 목록을 불러오는데 실패했습니다.</td></tr>';
    console.error("Failed to load users:", error);
  }
}

// 사용자 생성
async function createUser() {
  const nameInput = document.getElementById("userName");
  const emailInput = document.getElementById("userEmail");

  const name = nameInput.value.trim();
  const email = emailInput.value.trim();

  if (!name || !email) {
    alert("이름과 이메일을 모두 입력해주세요.");
    return;
  }

  try {
    const response = await fetch(`${API_URL}/api/users`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, email }),
    });

    if (response.ok) {
      nameInput.value = "";
      emailInput.value = "";
      loadUsers();
      alert("사용자가 추가되었습니다.");
    } else {
      const error = await response.text();
      alert("사용자 추가 실패: " + error);
    }
  } catch (error) {
    alert("사용자 추가 중 오류가 발생했습니다.");
    console.error("Failed to create user:", error);
  }
}

// 사용자 삭제
async function deleteUser(id) {
  if (!confirm("정말 이 사용자를 삭제하시겠습니까?")) {
    return;
  }

  try {
    const response = await fetch(`${API_URL}/api/users/${id}`, {
      method: "DELETE",
    });

    if (response.ok) {
      loadUsers();
      alert("사용자가 삭제되었습니다.");
    } else {
      alert("사용자 삭제에 실패했습니다.");
    }
  } catch (error) {
    alert("사용자 삭제 중 오류가 발생했습니다.");
    console.error("Failed to delete user:", error);
  }
}

// 페이지 로드 시 초기화
window.onload = async function () {
  updateTime();
  setInterval(updateTime, 1000);

  const isHealthy = await checkBackendHealth();
  if (isHealthy) {
    loadUsers();
  }
};