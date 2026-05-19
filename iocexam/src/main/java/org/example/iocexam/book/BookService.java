package org.example.iocexam.book;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void test(){
        bookDao.test();
    }
}
