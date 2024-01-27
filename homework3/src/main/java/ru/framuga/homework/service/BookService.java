package ru.framuga.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> bookList(){
        return bookRepository.getAllBooks();
    }

    public Book findBookByID(long id){
        return bookRepository.getBookById(id);
    }

    public void removeBookFromRep(long id){
        bookRepository.removeBook(id);
    }

    public void addBookToRep(Book requestBook){
        bookRepository.addBook(requestBook);
    }
}
