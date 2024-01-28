package ru.framuga.homework.service;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.framuga.homework.model.IssueRequest;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.model.Reader;
import ru.framuga.homework.repository.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

    // спринг это все заинжектит
    private final BookRepositoryJPA bookRepository;
    private final ReaderRepositoryJPA readerRepository;
    private final IssueRepositoryJPA issueRepository;

    @Value("${application.max-allowed-book}")
    private int maxBookCapacity;

    Issue issue;
    public Issue issue(IssueRequest request) {
        if (bookRepository.findBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.findReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        if (issueRepository.findAll().stream()
                .filter(issue -> issue.getReturnedAt() == null)
                .map(Issue::getReaderId)
                .filter(it -> it.equals(request.getReaderId()))
                .toList()
                .size() >= maxBookCapacity) {
            throw new RuntimeException("У читателя превышен лимит книг");
        }
        issue = new Issue();
        issue.setBookId(request.getBookId());
        issue.setReaderId(request.getReaderId());
        issue.setIssueAt(LocalDateTime.now());
        issueRepository.save(issue);
        return issue;
    }

    public Issue findIssueById(long id){
        return issueRepository.findIssueById(id);
    }

    public List<Issue> issueList(){
        return issueRepository.findAll();
    }

    public Book getIssueBook(){
        return bookRepository.findBookById(issue.getBookId());
    }

    public Reader getIssueReader(){
        return readerRepository.findReaderById(issue.getReaderId());
    }

    public void removeIssueById(long id){
        issueRepository.deleteIssueById(id);
    }

    public void saveIssue(Issue issue){
        issueRepository.save(issue);
    }
}
