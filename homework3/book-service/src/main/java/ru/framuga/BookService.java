package ru.framuga;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepositoryJPA bookRepository;

    public Book findBookByID(UUID id) {
        Book responseBook = bookRepository.findBookById(id);
        if (responseBook == null)
            throw new NoSuchElementException("Такая книга не найдена");
        return responseBook;
    }

    @Transactional
    public Book removeBookFromRep(UUID id) {
        Book book = findBookByID(id);
        bookRepository.deleteBookById(id);
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.size() < 1)
            throw new NoSuchElementException("Список книг пуст");
        return books;
    }

    public Book addBookToRep(Book requestBook) {
        if(requestBook.getId() == null && requestBook.getName() == null)
            throw new NullPointerException("При создании книги не может быть значения null");
        return bookRepository.save(requestBook);
    }
}
