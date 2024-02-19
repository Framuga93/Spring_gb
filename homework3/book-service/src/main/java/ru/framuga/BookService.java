package ru.framuga;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepositoryJPA bookRepository;

    public Book findBookByID(UUID id){
        return bookRepository.findBookById(id);
    }

    public void removeBookFromRep(UUID id){
        bookRepository.deleteBookById(id);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public void addBookToRep(Book requestBook){
        bookRepository.save(requestBook);
    }
}
