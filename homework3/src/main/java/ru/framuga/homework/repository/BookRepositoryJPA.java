package ru.framuga.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.framuga.homework.model.Book;

import java.util.List;

public interface BookRepositoryJPA extends JpaRepository<Book, Long> {

        Book findBookById(long id);

        void deleteBookById(long id);

}
