const arr = [1, 2, 3];

// 추가/제거
arr.push(5);        // 끝에 추가: [1, 2, 3, 5]
arr.pop();          // 끝에서 제거: [1, 2, 3]
arr.unshift(0);     // 앞에 추가: [0, 1, 2, 3]
arr.shift();        // 앞에서 제거: [1, 2, 3]

console.log(arr);  // [1, 2, 3]

// 연결과 분할
const arr2 = [5, 6];
const combined = arr.concat(arr2);  // [1, 2, 3, 5, 6]
const sliced = combined.slice(1, 4);  // [2, 3]

console.log(combined);
console.log(sliced);
