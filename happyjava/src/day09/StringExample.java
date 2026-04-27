package day09;

import java.util.Arrays;

public class StringExample {
    public static void main(String[] args) {
        String str = "  Hello Java World  ";

        // 길이와 문자 접근
        System.out.println("길이: " + str.length());           // 20
        System.out.println("5번째 문자: " + str.charAt(3));    // 'l'

        // 부분 문자열
        System.out.println(str.substring(8, 12));              // "Java"

        // 검색
        System.out.println(str.indexOf("Java"));               // 8
        System.out.println(str.contains("Java"));              // true
        System.out.println(str.startsWith("  Hello"));         // true
        System.out.println(str.endsWith("World  "));           // true

        // 변환
        System.out.println(str.toLowerCase());                 // 소문자로
        System.out.println(str.toUpperCase());                 // 대문자로
        System.out.println(str.trim());                        // 앞뒤 공백 제거
        System.out.println(str.replace("Java", "Python"));     // 치환

        // 분할과 결합
        String[] words = str.trim().split(" ");
        System.out.println(Arrays.toString(words));            // [Hello, Java, World]

        String joined = String.join("-", words);
        System.out.println(joined);                            // Hello-Java-World

        // 형식화
        String formatted = String.format("이름: %s, 나이: %d", "홍길동", 25);
        System.out.println(formatted);
    }
}