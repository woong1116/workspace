package day09;

class Good {
    private int i;

    public Good(int i){
        this.i = i;
    }
}

public class Exam04 {
    public static void main(String[] args) {
        Good g1 = new Good(10);
        Good g2 = new Good(10);

        if(g1 == g2)
            System.out.println("O");
        else
            System.out.println("X");


    }
}
