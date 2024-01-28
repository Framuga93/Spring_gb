package ru.framuga.homework.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.model.Reader;

@RequiredArgsConstructor
public class GenerateTestData {


    @PostConstruct
    public void generateData(BookRepositoryJPA bookRepository, ReaderRepositoryJPA readerRepository){




    }
}
