package day10;

import java.util.ArrayList;
import java.util.List;

public class MyStack<T> {

    private List<T> stack = new ArrayList<>();
    private int capacity;

    public MyStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalStateException("capacity 값은 반드시 1 이상");
        }
        this.capacity = capacity;
    }

    // push
    public void push(T value) {
        if (stack.size() == capacity) {
            throw new IllegalStateException("스택이 가득 참");
        }
        stack.add(value);
    }

    // pop
    public T pop() {
        if (stack.size() == 0) {
            throw new IllegalStateException("스택이 비어있음");
        }
        return stack.remove(stack.size() - 1);
    }

    // peek
    public T peek() {
        if (stack.size() == 0) {
            throw new IllegalStateException("스택이 비어 있습니다.");
        }
        return stack.get(stack.size() - 1);
    }

    // isEmpty
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    // isFull
    public boolean isFull() {
        return stack.size() == capacity;
    }

    // size
    public int size() {
        return stack.size();
    }


    public static void main(String[] args) {

        // Integer 스택
        MyStack<Integer> intStack = new MyStack<>(3);
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        System.out.println(intStack.pop());  // 3
        System.out.println(intStack.peek()); // 2

        // String 스택
        MyStack<String> strStack = new MyStack<>(2);
        strStack.push("A");
        strStack.push("B");

        System.out.println(strStack.pop());  // B
        System.out.println(strStack.size()); // 1

        // 예외 테스트
        strStack.pop();
        strStack.pop(); // 여기서 예외 발생
    }
}
