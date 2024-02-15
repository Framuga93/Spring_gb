package ru.framuga.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.framuga.homework.aspect.annotation.Timer;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.model.Reader;
import ru.framuga.homework.repository.IssueRepositoryJPA;
import ru.framuga.homework.repository.ReaderRepositoryJPA;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ReaderService {

    private final IssueRepositoryJPA issueRepository;
    private final ReaderRepositoryJPA readerRepository;


    public List<Issue> readerIssue(Reader reader) {
        return issueRepository.findAll().stream()
                .filter(it -> Objects.equals(it.getReaderId(), reader.getId()))
                .toList();
    }

    @Timer
    public List<Reader> readerList() {
        return readerRepository.findAll();
    }

    public Reader findReaderById(long id) {
        return readerRepository.findReaderById(id);
    }

    public void removeReaderFromRep(long id) {
        readerRepository.deleteReaderById(id);
    }

    public void addReaderToRep(Reader requestReader) {
        readerRepository.save(requestReader);
    }

}
