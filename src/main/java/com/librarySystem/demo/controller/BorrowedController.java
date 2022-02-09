package com.librarySystem.demo.controller;

import com.librarySystem.demo.model.Borrowed;
import com.librarySystem.demo.service.Impl.BorrowedServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "borrowed", produces = MediaType.APPLICATION_JSON_VALUE)
public class BorrowedController {

    @Autowired
    BorrowedServiceImpl borrowedService;

    @Operation(
            summary = "列出借閱的書",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "list borrowed book successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Borrowed book not found.",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<List<Borrowed>> listBorrowed(@Parameter(description = "Id of book.") @PathVariable Long id) {
        List<Borrowed> borrowed = borrowedService.listBorrowedBooks(id);

        return ResponseEntity.ok(borrowed);
    }

    @Operation(
            summary = "借閱書籍",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Borrowed book successfully",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Borrowed book not found.",
                            content = @Content
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Borrowed> borrowedBook(@Parameter(description = "Id of book") @RequestBody Borrowed borrowed) {

        borrowedService.saveBorrowed(borrowed.getBookId(), borrowed.getReaderId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "續借書籍",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Extend borrowed Date successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Borrowed not found.",
                            content = @Content
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Borrowed> extendDate( @RequestBody Borrowed borrowed) {
        borrowedService.extendTime(borrowed.getId(), borrowed.getReaderId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "還書",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "list borrowed book successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Borrowed book not found.",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> returnBook(@Parameter(description = "Id of book") @PathVariable Long id) {
        borrowedService.returnBook(id);

        return ResponseEntity.noContent().build();
    }
}
