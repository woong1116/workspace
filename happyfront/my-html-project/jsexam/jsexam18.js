function hi() {
  console.log("hello~~");
}

console.log("Start~~");
//비동기함수
setTimeout(hi, 2000);

console.log("end~~");