package day06;

public class Dice {
    int face;
    int eye;
    int count = 0;

    public void roll() {
        eye = (int) ((Math.random() * 6) + 1);

        if (eye == 3) {
            count++;
        }
    }
    public static void main(String[] args) {

        Dice dice = new Dice();

        for (int i = 0; i < 100; i++) {
            dice.roll();
        }
        System.out.println("눈이 3이 나온 횟수: " + dice.count);
    }
}
