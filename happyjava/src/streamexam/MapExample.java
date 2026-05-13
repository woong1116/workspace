package streamexam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("alice", "bob", "charlie");

        // 대문자로 변환
        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(upperNames); // [ALICE, BOB, CHARLIE]

        // 길이로 변환
        List<Integer> lengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(lengths); // [5, 3, 7]
    }
}