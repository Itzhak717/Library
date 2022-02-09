package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Borrowed;

import java.util.Date;
import java.util.List;

public interface BorrowedService {

    List<Borrowed> listBorrowedBooks(Long userId);

    Borrowed getBorrowedBook(Long borrowedId);

    Date showBorrowedDate(Long borrowedId);

    Borrowed saveBorrowed(String bookId, Long userId);

    void returnBook(Long borrowedId);

    Borrowed extendTime(Long borrowedId, Long userId);
}
