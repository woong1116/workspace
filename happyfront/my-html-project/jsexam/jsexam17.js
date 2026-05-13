console.log('시작');

setTimeout(() => {
    console.log('2초 후 실행');
}, 2000);

console.log('끝');

// 출력 순서:
// 시작
// 끝
// 2초 후 실행