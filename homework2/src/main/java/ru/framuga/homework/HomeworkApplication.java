package ru.framuga.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 1. Создать spring-boot приложение с помощью https://start.spring.io/
 * 2. Создать Класс Student c полями: идентификатор, имя, имя группы
 * 3. Создать контроллер, обрабатывающий входящие запросы:
 * 3.1 GET /student/{id} - получить студента по ID  //done
 * 3.2 GET /student - получить всех студентов // done
 * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName //done
 * 3.4 GET /group/{groupName}/student - получить всех студентов группы //done
 * 3.5 POST /student - создать студента (принимает JSON) (отладиться можно с помощью Postman) // done
 * 3.6 DELETE /student/{id} - удалить студента  // done
 * 4. При старте приложения, в программе должно быть создано 5-10 студентов.
 */

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

}
