package hw2.problem3;

// LibraryTest.java - 테스트 클래스
class LibraryTest {
    public static void main(String[] args) {
        System.out.println("===== 도서관 관리 시스템 테스트 =====\n");

        // 도서관 생성
        Library library = new Library();

        // 도서 생성 및 추가
        Book book1 = new Book("자바의 정석", "남궁성", "978-89-7914-726-8");
        Book book2 = new Book("이펙티브 자바", "조슈아 블로크", "978-89-6626-284-4");
        Book book3 = new Book("클린 코드", "로버트 마틴", "978-89-7914-725-1");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        System.out.println();

        // 대출 가능한 도서 출력
        library.displayAvailableBooks();

        // 도서 대출 테스트
        library.borrowBook("자바의 정석");
        library.borrowBook("자바의 정석"); // 이미 대출된 도서
        library.borrowBook("존재하지 않는 책"); // 없는 도서
        System.out.println();

        // 대출 후 도서 목록
        library.displayAvailableBooks();

        // 도서 반납 테스트
        library.returnBook("자바의 정석");
        library.returnBook("이미 있는 책"); // 없는 도서
        System.out.println();

        // 반납 후 도서 목록
        library.displayAvailableBooks();
    }
}