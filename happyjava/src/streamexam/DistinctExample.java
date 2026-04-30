package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList(
                "Apple", "Banana", "Cherry", "Apple", "Cherry", "Date"
        );

        // 중복 제거
        List<String> uniqueWords = words.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("고유한 단어: " + uniqueWords);
        // [Apple, Banana, Cherry, Date]

        // 필터링과 중복 제거 조합
        List<String> result = words.stream()
                .filter(word -> word.length() > 5)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("5자 초과 고유 단어: " + result);
        // [Banana, Cherry]
    }
}