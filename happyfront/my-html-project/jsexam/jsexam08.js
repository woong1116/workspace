const user = {
    username: 'john',
    email: 'john@email.com',
    age: 30
};

// 구조 분해 할당
const { username, email, age } = user;
console.log(username);  // john

// 함수 파라미터에서 구조 분해
function printUser({ username, age }) {
    console.log(`${username}님은 ${age}살입니다.`);
}

printUser(user);   