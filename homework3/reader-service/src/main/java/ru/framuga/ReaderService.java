package ru.framuga;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ReaderService {

    private final IssueProvider issueProvider;
    private final ReaderRepositoryJPA readerRepository;


    public List<Issue> readerIssue(Reader reader) {
        return issueProvider.getAllIssue().stream()
                .filter(it -> Objects.equals(it.getReaderId(), reader.getId()))
                .toList();
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
