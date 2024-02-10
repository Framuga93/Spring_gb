package ru.framuga.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Entity
@Table(name = "issues")
@Schema(name = "Выдача книги")
@Data
public class Issue {

  @Id
  @Schema(name = "Идентификатор")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(name = "Идентификатор книги")
  @Column(name = "bookId")
  private Long bookId;

  @Schema(name = "Идентификатор читателя")
  @Column(name = "readerId")
  private Long readerId;

  @Schema(name = "Дата выдачи")
  @Column(name = "issueAt")
  private LocalDateTime issueAt;

  @Schema(name = "Дата возврата книги")
  @Column(name = "returnedAt")
  private LocalDateTime returnedAt;

}

