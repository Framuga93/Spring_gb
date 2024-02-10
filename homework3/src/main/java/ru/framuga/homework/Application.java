package ru.framuga.homework;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.framuga.homework.model.Book;
import ru.framuga.homework.model.Reader;
import ru.framuga.homework.model.Roles;
import ru.framuga.homework.model.User;
import ru.framuga.homework.repository.BookRepositoryJPA;
import ru.framuga.homework.repository.ReaderRepositoryJPA;
import ru.framuga.homework.repository.RoleRepository;
import ru.framuga.homework.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Application {

    /**
     * 1. В предыдущий проект (где была библиотека с книгами, выдачами и читателями) добавить следующие рерурсы,
     * которые возвращают готовые HTML-страницы (т.е. это просто Controller'ы):
     * 1.1 /ui/books - на странице должен отобразиться список всех доступных книг в системе // done
     * 1.2 /ui/reader - аналогично 1.1 //done
     * 1.3 /ui/issues - на странице отображается таблица, в которой есть столбцы (книга, читатель, когда взял, когда вернул (если не вернул - пустая ячейка)).
     * 1.4* /ui/reader/{id} - страница, где написано имя читателя с идентификатором id и перечислены книги, которые на руках у этого читателя
     * <p>
     * Чтобы шаблонизатор thymeleaf заработал, то нужно добавить зависимость в pom.xml:
     * <dependency>
     * <groupId>org.springframework.boot</groupId>
     * <artifactId>spring-boot-starter-thymeleaf</artifactId>
     * </dependency>
     * <p>
     * <p>
     * <p>
     * * 1. Для ресурсов, возвращающих HTML-страницы, реализовать авторизацию через login-форму.
     * * Остальные /api ресурсы, возвращающие JSON, закрывать не нужно!
     * * 2.1* Реализовать таблицы User(id, name, password) и Role(id, name), связанные многие ко многим
     * * 2.2* Реализовать UserDetailsService, который предоставляет данные из БД (таблицы User и Role)
     * * 3.3* Ресурсы выдачей (issue) доступны обладателям роли admin
     * * 3.4* Ресурсы читателей (reader) доступны всем обладателям роли reader
     * * 3.5* Ресурсы книг (books) доступны всем авторизованным пользователям
     */

    // TODO: 21.01.2024 EXEPTIONS
    // TODO: 27.01.2024 add log


    static long id = 1L;

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        BookRepositoryJPA bookRepository = context.getBean(BookRepositoryJPA.class);
        ReaderRepositoryJPA readerRepository = context.getBean(ReaderRepositoryJPA.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        RoleRepository roleRepository = context.getBean(RoleRepository.class);

        Roles roleAdmin = new Roles();
        roleAdmin.setName("admin");
        roleRepository.save(roleAdmin);

        Roles roleReader = new Roles();
        roleReader.setName("reader");
        roleRepository.save(roleReader);


        for (int i = 1; i < 10; i++) {
            Reader reader = new Reader();
            reader.setName("Reader #" + i);
            readerRepository.save(reader);
            saveUser(userRepository, roleRepository, "reader"+i, List.of("reader"));
        }


        for (int i = 1; i < 10; i++) {
            Book book = new Book();
            book.setName("Book #" + i);
            bookRepository.save(book);
        }


        saveUser(userRepository, roleRepository, "admin", List.of("admin", "reader"));


    }


    private static void saveUser(UserRepository repository, RoleRepository roleRepository, String
            login, List<String> rolesList) {
        User user = new User();
        Set<Roles> roles = new java.util.HashSet<>(Collections.emptySet());
        rolesList.forEach(it -> roles.add(roleRepository
                .findByName(it)
                .orElseThrow(RuntimeException::new)));
        user.setId(id++);
        user.setLogin(login);
        user.setPassword(login);
        user.setRoles(roles);
        repository.save(user);
    }
}

/**
 * JDBC
 * //		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
 * <p>
 * //		DataSource dataSource = context.getBean(DataSource.class);
 * //
 * //		try(Connection connection = dataSource.getConnection()){
 * //			try(Statement statement = connection.createStatement()){
 * //				statement.execute("create table if not exists test1(id bigint, name varchar(512))");
 * //			}
 * //
 * //			try(Statement statement = connection.createStatement()){
 * //				statement.execute("insert into test1(id, name) values(1,'Alex')");
 * //			}
 * //
 * //			try(Statement statement = connection.createStatement()){
 * //				ResultSet resultSet = statement.executeQuery("select id, name from test1");
 * //				while (resultSet.next()){
 * //					System.out.println(resultSet.getInt("id"));
 * //					System.out.println(resultSet.getString("name"));
 * //				}
 * //			}
 * //		}
 * //
 * //
 */
