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

    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;


    public List<Issue> readerIssue(Reader reader){
        return issueRepository.getAllIssue().stream()
                .filter(it -> Objects.equals(it.getReaderId(), reader.getId()))
                .toList();
    }

    public List<Reader> readerList(){
        return readerRepository.getAllReaders();
    }

    public Reader findReaderById(long id){
        return readerRepository.getReaderById(id);
    }

    public List<Book> readerBooks(long id){
        return issueRepository.getAllIssue().stream()
                .filter(it -> Objects.equals(it.getReaderId(), id))
                .map(issue -> bookRepository.getBookById(issue.getBookId()))
                .toList();
    }

    public void removeReaderFromRep(long id){
        readerRepository.removeReader(id);
    }

    public void addReaderToRep(Reader requestReader){
        readerRepository.addReader(requestReader);
    }

}
