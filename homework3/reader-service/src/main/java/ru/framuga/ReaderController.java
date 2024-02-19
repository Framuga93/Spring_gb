package ru.framuga;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reader")
public class ReaderController {


    private final ReaderService readerService;

    @GetMapping("/{id}")
    @Operation(summary = "get reader by Id", description = "Находит и возвращает " +
            "читателя по ID")
    public Reader getBookById(@PathVariable("id") UUID id) {
        return readerService.findReaderById(id);
    }

    @GetMapping("/{id}/issue")
    @Operation(summary = "get reader issue`s", description = "Находит и возвращает " +
            "выдачи книг читателю по ID")
    public List<Issue> getReaderIssue(@PathVariable UUID id) {
        return readerService.readerIssue(readerService.findReaderById(id));
    }

    @GetMapping("/all")
    public List<Reader> getAllReaders(){
        return readerService.getAllReaders();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete reader by Id", description = "Удалить " +
            "читателя по ID")
    public void deleteReader(@PathVariable("id") UUID id) {
        readerService.removeReaderFromRep(id);
        log.info("Книга " + readerService.findReaderById(id).getName() + " удалена из репозитория");
    }

    @PostMapping
    @Operation(summary = "add reader to repository", description = "Добавить читателя в репозиторий")
    public void createReader(@RequestBody Reader requestReader) {
        readerService.addReaderToRep(requestReader);
        log.info("Создан читатель: readerName = {}", requestReader.getName());
    }


}
