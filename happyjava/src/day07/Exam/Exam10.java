package day07.Exam;

import java.util.Arrays;

class Ball {
    int num;
}

public class Exam10 {

    Ball[] lottoBalls = new Ball[45];

    public void putBall() {
        for (int i = 0; i < lottoBalls.length; i++) {
            Ball ball = new Ball();
            ball.num = i + 1;
            lottoBalls[i] = ball;
        }
    }

    public void mix() {
        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * lottoBalls.length);
            int y = (int) (Math.random() * lottoBalls.length);

            Ball temp = lottoBalls[x];
            lottoBalls[x] = lottoBalls[y];
            lottoBalls[y] = temp;
        }
    }

    public Ball getBall() {
        Ball ball = null;
        int index;
        do {
            index = (int) (Math.random() * lottoBalls.length);
            ball = lottoBalls[index];
        } while (ball == null);

        lottoBalls[index] = null;
        return ball;
    }

    public int[] Result() {
        int[] numbers = new int[6];

        for (int i = 0; i < 6; i++) {
            numbers[i] = getBall().num;
        }

        Arrays.sort(numbers);
        return numbers;
    }

    public static void main(String[] args) {
        Exam10 machine = new Exam10();
        machine.putBall();
        machine.mix();

        int[] lottoNum = machine.Result();

        for (int num : lottoNum) {
            System.out.print(num + " ");
        }
    }
}