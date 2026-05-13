// // 객체 생성자 함수
// function Animal(type, name, sound) {
//     this.type = type;
//     this.name = name;
//     this.sound = sound;
//     this.say = function() {
//         console.log(this.sound);
//     };
// }

// // 인스턴스 생성
// const dog = new Animal('개', '멍멍이', '멍멍');
// const cat = new Animal('고양이', '야옹이', '야옹');

// dog.say();  // 멍멍
// cat.say();  // 야옹


function Animal(type, name, sound) {
    this.type = type;
    this.name = name;
    this.sound = sound;
}

// 프로토타입에 메서드 추가
Animal.prototype.say = function() {
    console.log(this.sound);
};

// 프로토타입에 공유 값 추가
Animal.prototype.sharedValue = 1;

const dog = new Animal('개', '멍멍이', '멍멍');
const cat = new Animal('고양이', '야옹이', '야옹');

dog.say();  // 멍멍
cat.say();  // 야옹
console.log(dog.sharedValue);  // 1
console.log(cat.sharedValue);  // 1


