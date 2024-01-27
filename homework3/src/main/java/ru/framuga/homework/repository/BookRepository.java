package ru.framuga.homework.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.framuga.homework.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

  private final List<Book> books;

  public BookRepository() {
    this.books = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    books.addAll(List.of(
      new Book("War and Peace"),
      new Book("Dead Souls"),
      new Book("Clean Code")
    ));
  }

  public Book getBookById(long id) {
    return books.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public List<Book> getAllBooks(){
    return books;
  }

  public void removeBook(long id){
    books.remove(getBookById(id));
  }

  public void addBook(Book requestBook){
      Book book = new Book(requestBook.getName());
      books.add(book);
  }

}
