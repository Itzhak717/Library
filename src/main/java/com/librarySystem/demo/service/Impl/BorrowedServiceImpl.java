package com.librarySystem.demo.service.Impl;

import com.librarySystem.demo.Exception.NotFoundException;
import com.librarySystem.demo.model.Borrowed;
import com.librarySystem.demo.repository.BorrowedRepository;
import com.librarySystem.demo.service.BorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowedServiceImpl implements BorrowedService {

    @Autowired
    BorrowedRepository borrowedRepository;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    UserServiceImpl userService;

    @Override
    public List<Borrowed> listBorrowedBooks(Long userId) {

        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_WEEK,-1);
        Date date = now.getTime();

        List<Borrowed> borrowedList = borrowedRepository.findByReaderId(userId);
        if (borrowedList.isEmpty()){
            throw new NotFoundException("No borrowed book found.");
        }
        for (Borrowed borrowed : borrowedList) {
            if(borrowed.getBorrowedDate().before(date)){
                borrowed.setExpired(true);
            }
        }
        return borrowedList;
    }

    @Override
    public Borrowed getBorrowedBook(Long borrowedId) {
        return borrowedRepository.findById(borrowedId).orElseThrow(() -> new NotFoundException("Borrowed book not found!"));
    }

    @Override
    public Date showBorrowedDate(Long borrowedId) {
        return borrowedRepository.findById(borrowedId).get().getBorrowedDate();
    }

    @Override
    public Borrowed saveBorrowed(String bookId, Long userId) {
        bookService.getBookById(bookId);
        userService.getUser(userId);

        Borrowed borrowed = new Borrowed();
        borrowed.setReaderId(userId);
        borrowed.setBookId(bookId);
        bookService.borrowBook(bookId);

        return borrowedRepository.save(borrowed);
    }

    @Override
    public void returnBook(Long borrowedId) {
        bookService.returnBook(getBorrowedBook(borrowedId).getBookId());
        borrowedRepository.deleteById(borrowedId);
    }

    @Override
    public Borrowed extendTime(Long borrowedId,Long userId) {
        Borrowed borrowed = getBorrowedBook(borrowedId);
        if (!borrowed.getReaderId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User id incorrect.");
        }
        Calendar now = Calendar.getInstance();
        borrowed.setBorrowedDate(now.getTime());

        return borrowedRepository.save(borrowed);
    }
}
