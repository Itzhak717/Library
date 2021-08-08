package com.librarySystem.demo.service;

import com.librarySystem.demo.model.Borrowed;

import java.util.Date;
import java.util.List;

public interface BorrowedService {

    List<Borrowed> listBorrowedBooks(long userId);

    Borrowed getBorrowedBook(long borrowedId);

    Date showBorrowedDate(long borrowedId);

    Borrowed setBorrowed(String bookId);

    void returnBook(long borrowedId);

    Borrowed extendTime(Borrowed borrowed);
}
