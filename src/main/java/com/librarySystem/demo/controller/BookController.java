package com.librarySystem.demo.controller;

import com.librarySystem.demo.model.Book;
import com.librarySystem.demo.service.Impl.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "book", produces = {"application/json;charset=UTF-8"})
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @Operation(
            summary = "根據id取得書籍資料",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get book successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found.",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @Operation(
            summary = "根據搜尋條件列出所有書籍",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Get books successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book not found.",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<Book>> findBookBy(@RequestParam(defaultValue = "") String name,
                                                 @RequestParam(defaultValue = "bookName") String search,
                                                 @RequestParam(defaultValue = "bookName") String type,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "asc") String order) {

        List<Book> books = bookService.getBooks(name, search, type, page, order);

        return ResponseEntity.ok(books);
    }

    @Operation(
            summary = "新增書籍",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Add book successfully",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Input publication year not valid.",
                            content = @Content
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        bookService.createBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "更新書籍資料",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Update book successfully",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book id not found.",
                            content = @Content
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        bookService.updateBook(id, book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "刪除書籍",
            responses = {
                    @ApiResponse(
                            responseCode = "203",
                            description = "Delete book successfully",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book id not found.",
                            content = @Content
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


}
