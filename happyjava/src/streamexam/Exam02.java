package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exam02 {
    static void main(String[] args) {
        int[] iarr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

        List<String> result = names.stream()
                .filter(name -> name.length() > 3)    // 중간 연산: 필터링
                .map(String::toUpperCase)              // 중간 연산: 변환
                .sorted()                              // 중간 연산: 정렬
                .collect(Collectors.toList());         // 최종 연산: 수집

        System.out.println(result); // [ALICE, CHARLIE, DAVID]
    }
}
