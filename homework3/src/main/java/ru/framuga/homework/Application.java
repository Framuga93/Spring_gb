package ru.framuga.homework;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.model.Reader;
import ru.framuga.homework.repository.BookRepositoryJPA;
import ru.framuga.homework.repository.ReaderRepositoryJPA;

@SpringBootApplication
public class Application {

	/**
	 * 1. В предыдущий проект (где была библиотека с книгами, выдачами и читателями) добавить следующие рерурсы,
	 * которые возвращают готовые HTML-страницы (т.е. это просто Controller'ы):
	 * 1.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе // done
	 * 1.2 /ui/reader - аналогично 1.1 //done
	 * 1.3 /ui/issues - на странице отображается таблица, в которой есть столбцы (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
	 * 1.4* /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги, которые на руках у этого читателя
	 *
	 * Чтобы шаблонизатор thymeleaf заработал, то нужно добавить зависимость в pom.xml:
	 * 		<dependency>
	 * 			<groupId>org.springframework.boot</groupId>
	 * 			<artifactId>spring-boot-starter-thymeleaf</artifactId>
	 * 		</dependency>
	 */

	// TODO: 21.01.2024 EXEPTIONS
	// TODO: 27.01.2024 add log



	@SneakyThrows
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		BookRepositoryJPA bookRepository = context.getBean(BookRepositoryJPA.class);
		ReaderRepositoryJPA readerRepository = context.getBean(ReaderRepositoryJPA.class);


		for (int i = 1; i < 10; i++) {
			Reader reader = new Reader();
			reader.setName("Reader #"+ i);
			readerRepository.save(reader);
		}

		for (int i = 1; i < 10; i++) {
			Book book = new Book();
			book.setName("Book #"+ i);
			bookRepository.save(book);
		}


		/**
		 *
		 * JDBC
		 //		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		 //		DataSource dataSource = context.getBean(DataSource.class);
		 //
		 //		try(Connection connection = dataSource.getConnection()){
		 //			try(Statement statement = connection.createStatement()){
		 //				statement.execute("create table if not exists test1(id bigint, name varchar(512))");
		 //			}
		 //
		 //			try(Statement statement = connection.createStatement()){
		 //				statement.execute("insert into test1(id, name) values(1,'Alex')");
		 //			}
		 //
		 //			try(Statement statement = connection.createStatement()){
		 //				ResultSet resultSet = statement.executeQuery("select id, name from test1");
		 //				while (resultSet.next()){
		 //					System.out.println(resultSet.getInt("id"));
		 //					System.out.println(resultSet.getString("name"));
		 //				}
		 //			}
		 //		}
		 //
		 //
		 */
	}
}
