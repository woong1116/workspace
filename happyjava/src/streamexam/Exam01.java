package streamexam;

public class Exam01 {
    static void main(String[] args) {
        int[] iarr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i : iarr) {
            if (i % 2 == 0)
                System.out.println(i);

        }
    }
}
