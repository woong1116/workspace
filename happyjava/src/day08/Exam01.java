package day08;

abstract class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    abstract public void makeSound();

    public void eat() {
        System.out.println("동물이 먹이를 먹습니다.");
    }
}

    class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }

        @Override
        public void makeSound() {
            System.out.println("멍멍");
        }


        public void eat() {
            super.eat();
        }
    }
    class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }

        @Override
        public void makeSound() {
            System.out.println("야옹");
        }

        @Override
        public void eat() {
            super.eat();
        }
    }

    public class Exam01 {
        public static void main(String[] args) {
            Animal dog = new Dog("강아지");
            Animal cat = new Cat("고양이");

            dog.makeSound();
            dog.eat();

            cat.makeSound();
            cat.eat();
        }
    }

