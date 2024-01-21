package ru.framuga.homework.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.model.Reader;
import ru.framuga.homework.repository.BookRepository;
import ru.framuga.homework.repository.ReaderRepository;
import ru.framuga.homework.service.ReaderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reader")
public class ReaderController {


    private final ReaderRepository readerRepository;
    private final ReaderService readerService;

    @GetMapping("/{id}")
    public Reader getBookById(@PathVariable("id") long id){
        return readerRepository.getReaderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") long id){
        readerRepository.removeReader(id);
        log.info("Книга " + readerRepository.getReaderById(id).getName()+" удалена из репозитория");
    }

    @PostMapping
    public void createBook(@RequestBody Reader requestReader){
        readerRepository.addReader(requestReader);
        log.info("Создан читатель: readerName = {}", requestReader.getName());
    }

    @GetMapping("/{id}/issue")
    public List<Issue> getReaderIssue(@PathVariable long id){
        return readerService.readerIssue(readerRepository.getReaderById(id));
    }

}
