package ru.framuga.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.model.Reader;
import ru.framuga.homework.repository.BookRepository;
import ru.framuga.homework.repository.IssueRepository;
import ru.framuga.homework.repository.ReaderRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public List<Issue> readerIssue(Reader reader){
        return issueRepository.getAllIssue().stream()
                .filter(it -> Objects.equals(it.getReaderId(), reader.getId()))
                .toList();
    }



}
