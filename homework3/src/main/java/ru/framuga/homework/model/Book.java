package ru.framuga.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Entity
@Table(name = "books")
@Schema(name = "Книга")
@Data
public class Book {

    @Id
    @Schema(name = "Идентификатор")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "Имя пользователя")
    @Column(name = "name")
    private String name;
}
