package co.ptm.ptmshell.model;

import org.junit.jupiter.api.Test;

class RepoTest {

  @Test
  void testSet() {
    Repo repo =
        Repo.builder()
            .sn(1)
            .repo("https://github.com/spring-projects/spring-framework.git")
            .build();
    String directory = repo.getDirectory();
    IO.println(directory);
  }
}
