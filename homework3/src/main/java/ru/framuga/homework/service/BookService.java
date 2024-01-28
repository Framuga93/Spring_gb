package ru.framuga.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.repository.BookRepositoryJPA;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepositoryJPA bookRepository;

    public List<Book> bookList(){
        return bookRepository.findAll();
    }

    public Book findBookByID(long id){
        return bookRepository.findBookById(id);
    }

    public void removeBookFromRep(long id){
        bookRepository.deleteBookById(id);
    }

    public void addBookToRep(Book requestBook){
        bookRepository.save(requestBook);
    }
}
