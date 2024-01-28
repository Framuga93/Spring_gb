package ru.framuga.homework.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Entity
@Table(name = "issues")
@Data
public class Issue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "bookId")
  private Long bookId;

  @Column(name = "readerId")
  private Long readerId;

  @Column(name = "issueAt")
  private LocalDateTime issueAt;

  @Column(name = "returnedAt")
  private LocalDateTime returnedAt;

}

