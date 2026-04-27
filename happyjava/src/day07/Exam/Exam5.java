package day07.Exam;

public class Exam5 {

    public Exam5() {
    }

    public void Ratio() {
        int i = 1;
        while (i <= 50) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println(i);
            }
            i++;
        }
    }

    public static void main(String[] args) {
        Exam5 ratio = new Exam5();
        ratio.Ratio();
    }
}