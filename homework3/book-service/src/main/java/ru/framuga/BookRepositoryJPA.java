package ru.framuga;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryJPA extends JpaRepository<Book, Long> {

        Book findBookById(long id);

        void deleteBookById(long id);

}
