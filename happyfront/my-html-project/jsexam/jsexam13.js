// for...of (배열 요소 순회)
const fruits = ['사과', '바나나', '오렌지'];
for (let fruit of fruits) {
    console.log(fruit);
}

// for...in (객체 속성 순회)
const person = { name: '김철수', age: 25 };
for (let key in person) {
    console.log(`${key}: ${person[key]}`);
}