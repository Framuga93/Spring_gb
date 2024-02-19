package ru.framuga;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

    // спринг это все заинжектит
    private final BookProvider bookProvider;
    private final ReaderProvider readerProvider;
    private final IssueRepositoryJPA issueRepository;

    @Value("${application.max-allowed-book}")
    private int maxBookCapacity;

    Issue issue;
    public Issue issue(IssueRequest request) {
        if (bookProvider.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerProvider.getReaderById(request.getReaderId()) == null) {
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

    public void removeIssueById(long id){
        issueRepository.deleteIssueById(id);
    }

    public void saveIssue(Issue issue){
        issueRepository.save(issue);
    }
}
