package ru.framuga;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
@Tag(name = "Controllers")
public class BookController {


    private final BookService bookService;

    @GetMapping("/{id}")
    @Operation(summary = "get book by Id", description = "Находит и возвращает " +
            "книгу по ID")
    public Book getBookById(@PathVariable("id") long id){
        return bookService.findBookByID(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete book from repository", description = "Удалить книгу из репозитория по ID")
    public void deleteBook(@PathVariable("id") long id){
        bookService.removeBookFromRep(id);
        log.info("Книга " + bookService.findBookByID(id).getName()+" удалена из репозитория");
    }

    @PostMapping
    @Operation(summary = "add book in repository", description = "Добавить книгу в репозиторий")
    public void createBook(@RequestBody Book requestBook){
        bookService.addBookToRep(requestBook);
        log.info("Создана книга: bookName = {}", requestBook.getName());
    }

}
