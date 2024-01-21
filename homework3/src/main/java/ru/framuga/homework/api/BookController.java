package ru.framuga.homework.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.repository.BookRepository;

import java.lang.annotation.Repeatable;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") long id){
        return bookRepository.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") long id){
        bookRepository.removeBook(id);
        log.info("Книга " + bookRepository.getBookById(id).getName()+" удалена из репозитория");
    }

    @PostMapping
    public void createBook(@RequestBody Book requestBook){
        bookRepository.addBook(requestBook);
        log.info("Создана книга: bookName = {}", requestBook.getName());
    }

}
