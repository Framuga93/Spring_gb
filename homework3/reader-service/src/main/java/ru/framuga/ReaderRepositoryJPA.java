package ru.framuga;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReaderRepositoryJPA extends JpaRepository<Reader, Long> {

        Reader findReaderById(long id);

        void deleteReaderById(long id);

}
