// 단축평가
// &&  -  두개의 피연산자가 모두 true 일만 true
// 첫번째 피연산자가 false 라면???  두 번째 식이 전체값에 영향을 줄까요??

let food = { foodName: "피자" };

function getFoodName(food) {
  return food.foodName;
}

console.log(getFoodName(food));

food = null;

console.log(getFoodName(food));

function getFoodName(food) {
  if (!food) return;
  return food.foodName;
}

function getFoodName(food) {
  return food && food.foodName;
}

console.log("=====================");
console.log(true && "hello");
console.log(false && "hello");

// || 둘중 하나만 true 면 true

console.log(true || "hello");
console.log(false || "hello");

// 자바스크립트가 false로 판단하는 값들!!
console.log("==========자바 스크립트가 false로 판단하는 값들===========");
console.log(null && "hello");
console.log(undefined && "hello");
console.log("" && "hello");
console.log(0 && "hello");

console.log("==========자바 스크립트가 true로 판단하는 값들===========");
console.log("a" && "hello");
console.log(1 && "hello");

let a;
let b = null;
let c = undefined;
let d = 100;
let e = "aa";

let f = a || b || c || d || e;

console.log(f);

const name = null;
// const username;
// if (!name) username = "guest";
// else username = name;

const username = name || "guest";

console.log(username);

// ?? - 거짓을 판단하는 기준이 다르다!!

console.log(
  "==========?? ---  '', 0 을 값자체로 판단해줌.  true로 판단해줌   나머지는 || 와 동일 ===========",
);
console.log(null ?? "hello");
console.log(undefined ?? "hello");
console.log("" ?? "hello");
console.log(0 ?? "hello");

console.log("a" ?? "hello");
console.log(1 ?? "hello");
