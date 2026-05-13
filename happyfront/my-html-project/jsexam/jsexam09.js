let arr = [];

console.log(typeof arr);

// 배열 생성
const fruits = ['사과', '바나나', '오렌지'];
const numbers = [1, 2, 3, 4, 5];
const mixed = [1, 'hello', true, null];

// 요소 접근
console.log(fruits[0]);    // 사과
console.log(fruits[2]);    // 오렌지

// 요소 수정
fruits[1] = '포도';
console.log(fruits[1]); 

// 길이 확인
console.log(fruits.length);  // 3