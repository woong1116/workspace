// 기본 에러 처리
try {
    // 에러가 발생할 수 있는 코드
    const result = someFunction();
    console.log(result);
} catch (error) {
    // 에러 처리
    console.error('에러 발생:', error.message);
} finally {
    // 항상 실행되는 코드
    console.log('정리 작업');
}

// 에러 던지기
function divide(a, b) {
    if (b === 0) {
        throw new Error('0으로 나눌 수 없습니다!');
    }
    return a / b;
}

try {
    const result = divide(10, 0);
} catch (error) {
    console.error(error.message);
}