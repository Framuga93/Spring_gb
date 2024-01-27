package ru.framuga.homework.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.service.BookService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {


    private final BookService bookService;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") long id){
        return bookService.findBookByID(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") long id){
        bookService.removeBookFromRep(id);
        log.info("Книга " + bookService.findBookByID(id).getName()+" удалена из репозитория");
    }

    @PostMapping
    public void createBook(@RequestBody Book requestBook){
        bookService.addBookToRep(requestBook);
        log.info("Создана книга: bookName = {}", requestBook.getName());
    }

}
