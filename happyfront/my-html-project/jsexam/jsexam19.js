function work(callback) {
    console.log('work started')
    setTimeout(() => {
        console.log('work 실행');

        // work가 끝난 후 callback 호출
        callback();
    })
}


work(() => {
    console.log('work()가 끝나면 실행');
});