package ru.framuga.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.framuga.homework.model.Reader;

public interface ReaderRepositoryJPA extends JpaRepository<Reader, Long> {

        Reader findReaderById(long id);

        void deleteReaderById(long id);

}
