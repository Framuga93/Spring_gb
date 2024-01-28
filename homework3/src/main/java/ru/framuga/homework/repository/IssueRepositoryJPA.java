package ru.framuga.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.framuga.homework.model.Issue;
import ru.framuga.homework.model.IssueRequest;
import ru.framuga.homework.model.Reader;

public interface IssueRepositoryJPA extends JpaRepository<Issue, Long> {

        Issue findIssueById(long id);

        void deleteIssueById(long id);

}
