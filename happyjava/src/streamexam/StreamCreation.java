package streamexam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class StreamCreation {
    public static void main(String[] args) {
        // List에서 스트림 생성
        List<String> list = Arrays.asList("Java", "Python", "JavaScript");
        Stream<String> streamFromList = list.stream();
        streamFromList.forEach(System.out::println);

        // Set에서 스트림 생성
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Stream<Integer> streamFromSet = set.stream();
        System.out.println();
        streamFromSet.forEach(System.out::println);

        // 배열에서 스트림 생성
        String[] array = {"Spring", "Summer", "Fall", "Winter"};
        Stream<String> streamFromArray = Arrays.stream(array);
        System.out.println();
        streamFromArray.forEach(System.out::println);

        // 부분 배열에서 스트림 생성
        Stream<String> partialStream = Arrays.stream(array, 1, 3); // Summer, Fall
        System.out.println();
        partialStream.forEach(System.out::println);

        // Stream.of() 사용
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);
        System.out.println();
        numberStream.forEach(System.out::println);

        // Stream.iterate() - 무한 스트림 생성
        Stream<Integer> iterateStream = Stream.iterate(0, n -> n + 2)
                .limit(10); // 0, 2, 4, 6, 8...
        System.out.println();
        iterateStream.forEach(System.out::println);

        // Stream.generate() - 무한 스트림 생성
        Stream<Double> randomStream = Stream.generate(Math::random)
                .limit(5);
        System.out.println();
        randomStream.forEach(System.out::println);

        try {
            Stream<String> lines = Files.lines(Paths.get("data.txt"));
            lines.forEach(System.out::println);
            lines.close(); // 파일 스트림은 반드시 닫아야 함
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}