package ru.framuga.homework.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.framuga.homework.model.Issue;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class IssueRepository {

  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    // insert into ....
    issues.add(issue);
  }

  public Issue get(long id){
    return issues.stream()
            .filter(it -> Objects.equals(it.getId(),id))
            .findFirst()
            .orElse(null);
  }

  public List<Issue> getAllIssue(){
    return issues;
  }

}
