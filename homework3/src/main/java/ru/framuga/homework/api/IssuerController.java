package ru.framuga.homework.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.repository.IssueRepository;
import ru.framuga.homework.service.IssuerService;


import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/issue")
public class IssuerController {


  private final IssuerService service;


  @PostMapping
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(issue);
  }

  @PutMapping("/{id}")
  public void returnBook(@PathVariable long id){
    service.findIssueById(id).setReturnedAt(LocalDateTime.now());
  }

  @GetMapping("/{id}")
  public Issue getIssue(@PathVariable long id){
    return service.findIssueById(id);
  }


}
