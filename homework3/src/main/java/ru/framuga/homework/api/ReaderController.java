package ru.framuga.homework.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.model.Reader;
import ru.framuga.homework.service.ReaderService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reader")
public class ReaderController {


    private final ReaderService readerService;

    @GetMapping("/{id}")
    public Reader getBookById(@PathVariable("id") long id){
        return readerService.findReaderById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") long id){
        readerService.removeReaderFromRep(id);
        log.info("Книга " + readerService.findReaderById(id).getName()+" удалена из репозитория");
    }

    @PostMapping
    public void createReader(@RequestBody Reader requestReader){
        readerService.addReaderToRep(requestReader);
        log.info("Создан читатель: readerName = {}", requestReader.getName());
    }

    @GetMapping("/{id}/issue")
    public List<Issue> getReaderIssue(@PathVariable long id){
        return readerService.readerIssue(readerService.findReaderById(id));
    }

}
