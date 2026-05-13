class Animal {
    constructor(type, name, sound) {
        this.type = type;
        this.name = name;
        this.sound = sound;
    }
    
    say() {
        console.log(this.sound);
    }
}

// extends로 상속
class Dog extends Animal {
    constructor(name, sound) {
        super('개', name, sound);  // 부모 생성자 호출
    }
}

class Cat extends Animal {
    constructor(name, sound) {
        super('고양이', name, sound);
    }
}

const dog = new Dog('멍멍이', '멍멍');
const cat = new Cat('야옹이', '야옹');
const dog2 = new Dog('왈왈이', '왈왈');
const cat2 = new Cat('냐옹이', '냐옹');

dog.say();   // 멍멍
cat.say();   // 야옹
dog2.say();  // 왈왈
cat2.say();  // 냐옹