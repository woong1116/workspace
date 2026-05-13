package day07.Exam;

public class Exam4 {

    public Exam4() {
    }

    public void Gugudan() {
        for (int k = 1; k <= 9; k++) {
            for (int i = 2; i <= 9; i++) {
                System.out.print(i + " * " + k + " = " + (k * i) + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Exam4 gugudan = new Exam4();
        gugudan.Gugudan();
    }
}