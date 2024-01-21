package ru.framuga.homework.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Reader {

  public static long sequence = 1L;

  private final long id;
  private final String name;
  private List<Book> books;

  public Reader(String name) {
    this(sequence++, name);
  }


}
