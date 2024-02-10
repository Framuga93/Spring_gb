package ru.framuga.homework.api;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "get reader by Id", description = "Находит и возвращает " +
            "читателя по ID")
    public Reader getBookById(@PathVariable("id") long id){
        return readerService.findReaderById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete reader by Id", description = "Удалить " +
            "читателя по ID")
    public void deleteReader(@PathVariable("id") long id){
        readerService.removeReaderFromRep(id);
        log.info("Книга " + readerService.findReaderById(id).getName()+" удалена из репозитория");
    }

    @PostMapping
    @Operation(summary = "add reader to repository", description = "Добавить читателя в репозиторий")
    public void createReader(@RequestBody Reader requestReader){
        readerService.addReaderToRep(requestReader);
        log.info("Создан читатель: readerName = {}", requestReader.getName());
    }

    @GetMapping("/{id}/issue")
    @Operation(summary = "get reader issue`s", description = "Находит и возвращает " +
            "выдачи книг читателю по ID")
    public List<Issue> getReaderIssue(@PathVariable long id){
        return readerService.readerIssue(readerService.findReaderById(id));
    }

}
