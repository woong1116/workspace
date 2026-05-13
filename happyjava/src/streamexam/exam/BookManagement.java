package streamexam.exam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private int year;
    private double price;

    @Override
    public String toString() {
        return title;
    }

    public Book(String title, String author, int year, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }
    public double getPrice() {
        return price;
    }
}

public class BookManagement {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("자바의 정석", "케빈", 2016, 30000),
                new Book("모던 자바", "케빈", 2021, 35000),
                new Book("클린 코드", "로버트", 2013, 25000),
                new Book("이펙티브 자바", "로버트", 2018, 28000),
                new Book("자바 병렬 프로그래밍", "브라이언", 2022, 32000)
        );

        System.out.println("\n2020년 이후 출판된 도서만 필터링");
        List<Book> yearfilter = books.stream()
                .filter(book -> book.getYear() > 2020)
                .collect(Collectors.toList());
        yearfilter.forEach(System.out::println);

        System.out.println("\n가격이 20000원 이상인 도서 찾기");
        List<Book> pricefilter = books.stream()
                .filter(book -> book.getPrice() > 20000)
                .collect(Collectors.toList());
        pricefilter.forEach(System.out::println);

        System.out.println("\n저자별로 도서를 그룹화");
        Map<String, List<Book>> author = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
        author.forEach((book, books1) -> {
            System.out.println(book + ": " +
                    books1.stream()
                            .map(Book::getTitle).collect(Collectors.toList()));
        });

        System.out.println("\n평균 가격 계산");
        double avgPrice = books.stream()
                .collect(Collectors.averagingDouble(Book::getPrice));
        System.out.println(avgPrice);
    }
}