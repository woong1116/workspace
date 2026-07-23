// 백엔드 API URL (기본값: 로컬호스트, Kubernetes 환경에서는 Service 이름 사용)
const API_URL = window.API_URL || 'http://localhost:8080';

// 현재 시간 업데이트 함수
function updateTime() {
    const now = new Date();
    const timeString = now.toLocaleString('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit'
    });
    document.getElementById('time').textContent = timeString;
}

// 백엔드 API 호출 함수
async function callBackendAPI() {
    try {
        const response = await fetch(`${API_URL}/hello`);
        const message = await response.text();
        document.getElementById('api-response').textContent = message;
        document.getElementById('api-status').textContent = '✅ 연결 성공';
        document.getElementById('api-status').style.color = 'green';
    } catch (error) {
        document.getElementById('api-response').textContent = '연결 실패';
        document.getElementById('api-status').textContent = '❌ 백엔드 연결 실패';
        document.getElementById('api-status').style.color = 'red';
        console.error('API 호출 실패:', error);
    }
}

// 페이지 로드 시 실행
window.onload = function() {
    // 시간 업데이트
    updateTime();
    setInterval(updateTime, 1000);

    // 호스트명 표시
    document.getElementById('hostname').textContent = 'web-app-pod-' + Math.random().toString(36).substr(2, 9);

    // 백엔드 API 호출
    callBackendAPI();
};