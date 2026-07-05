'use client'; // 1. 훅(useState)을 써야 하므로 클라이언트 컴포넌트로 선언

import { useState, useEffect } from 'react';

export default function AboutPage() {
  const [isDark, setIsDark] = useState(false);

  // 2. 다크 모드 상태에 따라 HTML 태그에 'dark' 클래스를 넣었다 뺐다 합니다.
  useEffect(() => {
    if (isDark) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }, [isDark]);

  return (
    // 3. 부모 컨테이너: 전체 배경과 글자색 설정
    // bg-white (기본) <-> dark:bg-gray-900 (다크모드)
    <div className={`min-h-screen flex flex-col items-center justify-center transition-colors duration-300 ${isDark ? "dark" : ""}`}>
      
      {/* 실제 다크모드 적용 영역 */}
      <div className="bg-white dark:bg-gray-900 text-gray-900 dark:text-white p-10 rounded-xl shadow-xl text-center transition-colors duration-300 border dark:border-gray-700">
        
        <h1 className="text-3xl font-bold mb-4">
          Tailwind 다크 모드 실습
        </h1>
        
        <p className="mb-6 text-gray-600 dark:text-gray-300">
          현재 모드: <span className="font-bold">{isDark ? '다크 모드 🌙' : '라이트 모드 ☀️'}</span>
        </p>

        {/* 토글 버튼 */}
        <button
          onClick={() => setIsDark(!isDark)}
          className="px-6 py-2 rounded-full font-bold bg-blue-500 text-white hover:bg-blue-600 dark:bg-yellow-500 dark:text-black dark:hover:bg-yellow-400 transition-all"
        >
          모드 전환하기
        </button>

      </div>
    </div>
  );
}