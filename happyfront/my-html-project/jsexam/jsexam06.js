function add(a) {
    console.log("a+a");
    return a + a;
}

function add(a, b) {
    console.log("a+b");
    return a + b;
}

add(10);      // a+a
add(10, 20);  // a+b
