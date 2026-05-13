package day07.Exam;

public class Exam3 {

    int sum = 0;

    public Exam3() {
    }

    public void numSum() {
        int i = 0;
        while (i++ < 100) {
            sum += i;
        }
    }

    public void Result() {
        System.out.println(sum);
    }

    public static void main(String[] args) {
        Exam3 ex = new Exam3();
        ex.numSum();
        ex.Result();
    }
}