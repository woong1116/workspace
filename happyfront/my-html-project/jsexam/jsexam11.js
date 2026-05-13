// 스프레드 연산자 (...)
const arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];
const combined = [...arr1, ...arr2];  // [1, 2, 3, 4, 5, 6]

// 배열 복사
const original = [1, 2, 3];
const copy = [...original];  // 새로운 배열 생성

console.log(copy);

// 배열 구조분해 할당
const [first, second, ...rest] = [1, 2, 3, 4, 5, 6, 7];
console.log(first);  // 1
console.log(second); // 2
console.log(rest);   // [3, 4, 5]

// 값 교환
let x = 10, y = 20;
[x, y] = [y, 20];
console.log(x, y);  // 20, 10