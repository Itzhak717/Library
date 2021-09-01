package com.librarySystem.demo.controller;

import com.librarySystem.demo.model.Borrowed;
import com.librarySystem.demo.security.UserIdentity;
import com.librarySystem.demo.service.Impl.BorrowedServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "borrowed",produces = MediaType.APPLICATION_JSON_VALUE)
public class BorrowedController {

    @Autowired
    BorrowedServiceImpl borrowedService;

    @Autowired
    private UserIdentity userIdentity;

    @Operation(
            summary = "列出借閱的書"
    )
    @GetMapping("/{id}")
    public ResponseEntity<List<Borrowed>> listBorrowed(@PathVariable long id){
        List<Borrowed> borrowed = borrowedService.listBorrowedBooks(id);

        return ResponseEntity.ok(borrowed);
    }

    @Operation(
            summary = "借閱書籍"
    )
    @PostMapping("")
    public ResponseEntity<Borrowed> borrowedBook(@RequestBody List<String> books){
        for (String bookId : books) {
            borrowedService.setBorrowed(bookId);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userIdentity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(
            summary = "續借書籍"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Borrowed> extendDate(@RequestBody Borrowed borrowed){
        borrowedService.extendTime(borrowed);

        return ResponseEntity.ok(borrowed);
    }

    @Operation(
            summary = "還書"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> returnBook(@PathVariable long id){
        borrowedService.returnBook(id);

        return ResponseEntity.noContent().build();
    }
}
