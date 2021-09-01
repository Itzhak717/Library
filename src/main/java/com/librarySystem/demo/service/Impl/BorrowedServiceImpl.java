package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.Exception.NotFoundException;
import com.librarySystem.demo.model.Borrowed;
import com.librarySystem.demo.repository.BorrowedRepository;
import com.librarySystem.demo.security.UserIdentity;
import com.librarySystem.demo.service.BorrowedService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class BorrowedServiceImpl implements BorrowedService {

    @Autowired
    BorrowedRepository borrowedRepository;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    private UserIdentity userIdentity;

    @Override
    public List<Borrowed> listBorrowedBooks(long userId) {
        return borrowedRepository.findByReaderId(userId);
    }

    @Override
    public Borrowed getBorrowedBook(long borrowedId) {
        return borrowedRepository.findById(borrowedId).orElseThrow(() -> new NotFoundException("not found!"));
    }

    @Override
    public Date showBorrowedDate(long borrowedId) {
        return borrowedRepository.findById(borrowedId).get().getBorrowedDate();
    }

    @Override
    public Borrowed setBorrowed(String bookId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));

        Borrowed borrowed = new Borrowed();
        borrowed.setReaderId(userIdentity.getId());
        borrowed.setBookId(bookId);
        borrowed.setBorrowedDate(calendar.getTime());
        bookService.borrowedBook(bookId);

        return borrowedRepository.save(borrowed);
    }

    @Override
    public void returnBook(long borrowedId) {
        bookService.returnBook(getBorrowedBook(borrowedId).getBookId());
        borrowedRepository.deleteById(borrowedId);
    }

    @Override
    public Borrowed extendTime(Borrowed borrowed) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowed.getBorrowedDate());
        calendar.add(Calendar.DATE,7);
        borrowed.setBorrowedDate(calendar.getTime());

        return borrowedRepository.save(borrowed);
    }
}
