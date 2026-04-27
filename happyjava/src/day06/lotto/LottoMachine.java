package day06.lotto;

public class LottoMachine {
    Ball balls;
    Ball[] lottoBalls = new Ball[45];

//  볼을 채우는 기능
    public void putBall(){
        for(int i = 0; i < lottoBalls.length; i++){
            Ball ball = new Ball();
            ball.number = i + 1;
            lottoBalls[i] = ball;
        }
    }
//  공들을 섞는 기능
    public void mix(){
        for(int i = 0; i < 100; i++){
            int x = (int)(Math.random() * lottoBalls.length);
            int y = (int)(Math.random() * lottoBalls.length);

            Ball temp = lottoBalls[x];
            lottoBalls[x] = lottoBalls[y];
            lottoBalls[y] = temp;

        }
    }
//  공을 뽑는 기능
    public Ball getBalls() {
        Ball ball = null;
        int index;
        do{
            index = (int)(Math.random() * lottoBalls.length);
            ball = lottoBalls[index];
        }while (ball == null);
        lottoBalls[index] = null;
        return ball;
    }
}
