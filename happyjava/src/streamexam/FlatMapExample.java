package streamexam;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {
    public static void main(String[] args) {
        // 중첩된 리스트를 평탄화
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("Apple", "Banana"),
                Arrays.asList("Cherry", "Date"),
                Arrays.asList("Elderberry", "Fig")
        );

        List<String> flatList = nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        System.out.println(flatList);
        // [Apple, Banana, Cherry, Date, Elderberry, Fig]

        // 문자열을 문자 스트림으로 변환
        List<String> words = Arrays.asList("Hello", "World");
        List<String> letters = words.stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(Collectors.toList());

        System.out.println(letters);
        // [H, e, l, o, W, r, d]
    }
}