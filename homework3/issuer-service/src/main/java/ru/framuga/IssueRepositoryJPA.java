package ru.framuga;


import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepositoryJPA extends JpaRepository<Issue, Long> {

        Issue findIssueById(long id);

        void deleteIssueById(long id);

}
