const express = require('express');
const app = express();
const port = 3001; // Express 서버를 3001 포트로 실행

// 정적 파일을 제공할 디렉토리를 설정
app.use(express.static('public'));

// 나머지 라우트 및 미들웨어 정의

app.listen(port, () => {
  console.log(`서버가 ${port} 포트에서 실행 중입니다.`);
});