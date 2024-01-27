package ru.framuga.homework.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.framuga.homework.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    readers.add(
      new Reader("Igor")
    );
  }

  public Reader getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public void removeReader(long id){
    readers.remove(getReaderById(id));
  }

  public void addReader(Reader requestReader){
    Reader reader = new Reader(requestReader.getName());
    readers.add(reader);
  }

  public List<Reader> getAllReaders(){
    return readers;
  }

}
