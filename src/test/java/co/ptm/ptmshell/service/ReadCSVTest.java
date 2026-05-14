package co.ptm.ptmshell.service;

import co.ptm.ptmshell.model.Repo;
import java.io.IOException;
import java.util.List;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReadCSVTest {

  @InjectMocks private @NonNull ReadCSV readCSV;

  @Test
  void bashCommandTest() throws IOException, InterruptedException {
    String createDirectory =
        readCSV.bashCommand("/home/b/java-projects/ptmshell", "mkdir test test2");
    //    String deleteDirectory = readCSV.bashCommand("/home/b/java-projects/ptmshell","rm -rf test
    // test2");
    IO.println(createDirectory);
    //    IO.println(deleteDirectory);
  }

  @Test
  void readCsvTest() throws IOException {
    List<Repo> repos =
        readCSV.readCsv(
            "/Users/surajgurung/IdeaProjects/ptmshell/src/main/resources/repos_spring.csv");
    repos.forEach(IO::println);
  }

  @Test
  void cloneReposTest() throws IOException, InterruptedException {
    List<Repo> repos =
        readCSV.readCsv(
            "/home/b/java-projects/ptmshell/src/main/resources/repos_spring.csv");
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/spring-repos", "git clone " + repo.getRepo());
            IO.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void pullAllTest() throws IOException {
    List<Repo> repos =
        readCSV.readCsv(
            "/home/b/java-projects/ptmshell/src/main/resources/repos_spring.csv");
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/spring-repos" + "/" + repo.getDirectory(),
                    "git pull --all");
            IO.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void cloneReposTestCloud() throws IOException, InterruptedException {
    List<Repo> repos =
        readCSV.readCsv(
            "/home/b/java-projects/ptmshell/src/main/resources/repos_cloud_spring.csv");
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/spring-cloud-repos", "git clone " + repo.getRepo());
            IO.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void pullAllTestCloud() throws IOException {
    List<Repo> repos =
        readCSV.readCsv(
            "/home/b/java-projects/ptmshell/src/main/resources/repos_cloud_spring.csv");
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/spring-cloud-repos" + "/" + repo.getDirectory(),
                    "git pull --all");
            IO.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
