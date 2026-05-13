package hw2.problem3;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    // 빈 도서관 생성
    public Library() {
        books = new ArrayList<>();
    }

    // 도서 추가
    public void addBook(Book book) {
        books.add(book);
    }

    // 제목으로 도서 검색
    public Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;
    }

    // 도서 대출
    public boolean borrowBook(String title) {
        Book book = findBookByTitle(title);

        if (book == null) {
            System.out.println("도서를 찾을 수 없음");
            return false;
        }
        return book.borrowBook();
    }

    // 도서 반납
    public boolean returnBook(String title) {
        Book book = findBookByTitle(title);

        if (book == null) {
            System.out.println("도서를 찾을 수 없음");
            return false;
        }
        return book.returnBook();
    }

    // 대출 가능한 도서 목록 출력
    public void displayAvailableBooks() {
        System.out.println("- 대출 가능한 도서 목록 -");

        for (Book b : books) {
            if (b.isAvailable()) {
                b.showBookInfo();
            }
        }
        System.out.println();
    }
}