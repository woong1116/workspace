package lambdaexam;

import java.util.function.*;

public class StringTransformer {

    public static void main(String[] args) {
        // 문자열 변환 람다식 구현
        Function<String, String> toUpperCase = str -> str.toUpperCase();
        Function<String, String> toLowerCase = str -> str.toLowerCase();
        Function<String, String> reverse = str -> new StringBuilder(str).reverse().toString();

        // 테스트
        String input = "Hello World";
        System.out.println("원본: " + input);
        System.out.println("대문자: " + transform(input, toUpperCase));
        System.out.println("소문자: " + transform(input, toLowerCase));
        System.out.println("역순: " + transform(input, reverse));

        // 변환 체이닝
        Function<String, String> upperThenReverse = toUpperCase.andThen(reverse);
        System.out.println("대문자 후 역순: " + transform(input, upperThenReverse));

        // 커스텀 변환
        Function<String, String> addBrackets = str -> "[" + str + "]";
        Function<String, String> removeSpaces = str -> str.replace(" ", "");

        Function<String, String> complex = toUpperCase
                .andThen(removeSpaces)
                .andThen(addBrackets);

        System.out.println("복합 변환: " + transform(input, complex));
    }

    public static String transform(String str, Function<String, String> transformer) {
        return transformer.apply(str);
    }
}