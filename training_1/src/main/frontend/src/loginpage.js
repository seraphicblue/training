import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom'; // React Router 사용

function LoginForm() {
  const [email, setEmail] = useState(''); // 이메일 입력 필드 상태
  const [password, setPassword] = useState(''); // 비밀번호 입력 필드 상태
  const history = useHistory(); // React Router의 history 객체

  // 로그인 버튼 클릭 시 실행되는 함수
  async function handleLogin() {
    try {
      const response = await fetch('/api/user/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });

      if (!response.ok) {
        throw new Error('Login failed');
      }

      const result = await response.json();
      console.log('로그인 성공:', result);
      // 로그인 성공 후 처리 (예: 사용자 정보 저장)
      // 사용자 정보 저장 후 리다이렉트
      history.push('/dashboard'); // 예시: 대시보드 페이지로 이동
    } catch (error) {
      console.error('로그인 오류:', error);
      // 오류 처리 로직
    }
  }

  return (
    <div>
      <input
        type="text"
        placeholder="이메일"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>로그인</button>
    </div>
  );
}

export default LoginForm;

