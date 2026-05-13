package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 짝수만 필터링
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("짝수: " + evenNumbers); // [2, 4, 6, 8, 10]

        // 복합 조건 필터링
        List<Integer> filtered = numbers.stream()
                .filter(n -> n > 3)
                .filter(n -> n < 8)
                .collect(Collectors.toList());

        System.out.println("3 < n < 8: " + filtered); // [4, 5, 6, 7]
    }
}