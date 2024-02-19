package ru.framuga;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepositoryJPA bookRepository;

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
