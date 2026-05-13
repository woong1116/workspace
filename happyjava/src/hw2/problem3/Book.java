package hw2.problem3;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    // 도서 대출
    public boolean borrowBook() {
        if (available) {
            available = false;
            System.out.println(title + " 대출 성공");
            return true;
        } else {
            System.out.println(title + " 이미 대출 중");
            return false;
        }
    }

    // 도서 반납
    public boolean returnBook() {
        if (!available) {
            available = true;
            System.out.println(title + " 반납 완료");
            return true;
        } else {
            System.out.println(title + " 이미 도서관에 있음");
            return false;
        }
    }

    // 도서 정보 출력
    public void showBookInfo() {
        System.out.println("제목: " + title + ", 저자: " + author + " ISBN: " + isbn);
    }

    // getter 사용
    public String getTitle() {
        return title;
    }

    // 대출 가능 확인
    public boolean isAvailable() {
        return available;
    }

}
