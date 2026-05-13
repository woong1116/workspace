// const numbers = [1, 2, 3, 4, 5];

// numbers.forEach(num => {
//     console.log(num * 2);
// });
// // 2, 4, 6, 8, 10

// console.log("================================");

// for (let i = 0; i < numbers.length; i++) {
//     console.log(numbers[i]);
// }

// console.log("================================");

// numbers.forEach((num) => {
//     console.log(num * 2);
// });

// console.log("================================");

// const doubled = numbers.map(num => num * 2);
// console.log(doubled);  // [2, 4, 6, 8, 10]

// const evens = numbers.filter(num => num % 2 === 0);
// console.log(evens);  // [2, 4]

// console.log("================================");

// const sum = numbers.reduce((acc, cur) => acc + cur, 0);
// console.log(sum);  // 15

// // 평균 구하기
// const avg = numbers.reduce((acc, cur, idx, arr) => {
//     if (idx === arr.length - 1) {
//         return (acc + cur) / arr.length;
//     }
//     return acc + cur;
// }, 0);
// console.log(avg);  // 3


// const users = [
//     { id: 1, name: '김철수' },
//     { id: 2, name: '이영희' },
//     { id: 3, name: '박민수' }
// ];

// const user = users.find(u => u.id === 2);
// console.log(user);  // { id: 2, name: '이영희' }

// const index = users.findIndex(u => u.id === 2);
// console.log(index);  // 1


// // splice - 원본 배열 수정
// const numbers = [10, 20, 30, 40];
// const index = numbers.indexOf(30);
// numbers.splice(index, 2);  // index부터 1개 제거
// console.log(numbers);  // [10, 20, 40]

// // slice - 원본 배열 유지
// const arr = [10, 20, 30, 40];
// const sliced = arr.slice(0, 2);  // 0부터 2 전까지
// console.log(sliced);  // [10, 20]
// console.log(arr);     // [10, 20, 30, 40] (원본 유지)


// const numbers = [10, 20, 30, 40];

// // shift - 첫 번째 요소 제거 및 반환
// const first = numbers.shift();
// console.log(first);    // 10
// console.log(numbers);  // [20, 30, 40]

// // pop - 마지막 요소 제거 및 반환
// const last = numbers.pop();
// console.log(last);     // 40
// console.log(numbers);  // [20, 30]

// // unshift - 앞에 추가
// numbers.unshift(5);
// console.log(numbers);  // [5, 20, 30]


// // concat - 배열 합치기
// const arr1 = [1, 2, 3];
// const arr2 = [4, 5, 6];
// const combined = arr1.concat(arr2);
// console.log(combined);  // [1, 2, 3, 4, 5, 6]

// // join - 문자열로 합치기
// const array = [1, 2, 3, 4, 5];
// console.log(array.join());      // "1,2,3,4,5"
// console.log(array.join(' '));   // "1 2 3 4 5"
// console.log(array.join(' - '));  // "1 - 2 - 3 - 4 - 5"


const fruits = ['사과', '바나나', '오렌지', '포도'];

// indexOf - 인덱스 찾기
console.log(fruits.indexOf('바나나'));  // 1
console.log(fruits.indexOf('수박'));    // -1 (없음)

// includes - 포함 여부 확인
console.log(fruits.includes('오렌지'));  // true
console.log(fruits.includes('수박'));    // false