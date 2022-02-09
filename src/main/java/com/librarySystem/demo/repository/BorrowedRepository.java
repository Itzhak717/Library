package com.librarySystem.demo.repository;

import com.librarySystem.demo.model.Borrowed;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BorrowedRepository extends CrudRepository<Borrowed, Long> {

    List<Borrowed> findByReaderId(long readerId);
}
