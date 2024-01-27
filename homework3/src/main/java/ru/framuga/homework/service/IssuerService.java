package ru.framuga.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.framuga.homework.api.IssueRequest;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.model.Reader;
import ru.framuga.homework.repository.BookRepository;
import ru.framuga.homework.repository.IssueRepository;
import ru.framuga.homework.repository.ReaderRepository;


import javax.naming.LimitExceededException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

    // спринг это все заинжектит
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    @Value("${application.max-allowed-book}")
    private int maxBookCapacity;

    Issue issue;
    public Issue issue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        System.out.println(issueRepository.getAllIssue());
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
        if (issueRepository.getAllIssue().stream()
                .filter(issue -> issue.getReturnedAt() == null)
                .map(Issue::getReaderId)
                .filter(it -> it.equals(request.getReaderId()))
                .toList()
                .size() >= maxBookCapacity) {
            throw new RuntimeException("У читателя превышен лимит книг");
        }
        issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    public List<Issue> issueList(){
        return issueRepository.getAllIssue();
    }

    public Book getIssueBook(){
        return bookRepository.getBookById(issue.getBookId());
    }

    public Reader getIssueReader(){
        return readerRepository.getReaderById(issue.getReaderId());
    }
}
