package day06;

public class DiceGame {
    public static void countEye(Dice dice, int rollTimes, int eye){
        int count = 0;
        for(int i = 0; i < rollTimes; i ++){
            dice.roll();
            if(dice.eye == eye)
                count++;
        }
        System.out.printf("주사위를 %d 번 굴려서 %d 이 나온 횟수 : %d \n",rollTimes,eye,count);
    }

    public static void main(String[] args) {
        Dice dice = new Dice();
        countEye(dice,100,3);
        countEye(dice,30,5);
        countEye(dice,200,6);
    }
}
