package ru.framuga.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "reader")
@Schema(name = "Читатель")
@Data
public class Reader {

  @Id
  @Schema(name = "Идентификатор")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Schema(name = "Имя читателя")
  @Column
  private String name;

  // List<Book>
}
