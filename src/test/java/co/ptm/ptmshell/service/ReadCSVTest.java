package co.ptm.ptmshell.service;

import co.ptm.ptmshell.model.Repo;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReadCSVTest {

  private static String resourcePath(String fileName) {
    // Use project-relative path so tests work on any machine.
    return Path.of("src/main/resources", fileName).toString();
  }

  @InjectMocks private @NonNull ReadCSV readCSV;

  @Test
  void bashCommandTest() throws IOException, InterruptedException {
    String createDirectory =
        readCSV.bashCommand("/home/b/java-projects/ptmshell", "mkdir test test2");
    //    String deleteDirectory = readCSV.bashCommand("/home/b/java-projects/ptmshell","rm -rf test
    // test2");
    System.out.println(createDirectory);
    //    System.out.println(deleteDirectory);
  }

  @Test
  void readCsvTest() throws IOException {
    List<Repo> repos = readCSV.readCsv(resourcePath("repos_spring.csv"));
    repos.forEach(System.out::println);
  }

  @Test
  void cloneReposTest() throws IOException, InterruptedException {
    List<Repo> repos = readCSV.readCsv(resourcePath("repos_spring.csv"));
    repos.forEach(
        repo -> {
          try {
            String s = readCSV.bashCommand("/home/b/spring-repos", "git clone " + repo.getRepo());
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void pullAllTest() throws IOException {
    List<Repo> repos = readCSV.readCsv(resourcePath("repos_spring.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/spring-repos" + "/" + repo.getDirectory(), "git pull --all");
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void cloneReposTestCloud() throws IOException, InterruptedException {
    List<Repo> repos = readCSV.readCsv(resourcePath("repos_cloud_spring.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand("/home/b/spring-cloud-repos", "git clone " + repo.getRepo());
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void pullAllTestCloud() throws IOException {
    List<Repo> repos = readCSV.readCsv(resourcePath("repos_cloud_spring.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/spring-cloud-repos" + "/" + repo.getDirectory(), "git pull --all");
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void cloneReposAdditional() throws IOException, InterruptedException {
    List<Repo> repos = readCSV.readCsv(resourcePath("additional_repos.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand("/home/b/additional-repos", "git clone " + repo.getRepo());
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void pullAllAdditional() throws IOException {
    List<Repo> repos = readCSV.readCsv(resourcePath("additional_repos.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/additional-repos" + "/" + repo.getDirectory(), "git pull --all");
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }
  @Test
  void cloneReposCraft() throws IOException, InterruptedException {
    List<Repo> repos = readCSV.readCsv(resourcePath("craft.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand("/home/b/craft-repos", "git clone " + repo.getRepo());
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Test
  void pullAllCraft() throws IOException {
    List<Repo> repos = readCSV.readCsv(resourcePath("craft.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/craft-repos" + "/" + repo.getDirectory(), "git pull --all");
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }
  @Test
  void cloneReposFloci() throws IOException, InterruptedException {
    List<Repo> repos = readCSV.readCsv(resourcePath("floci.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand("/home/b/floci-repos", "git clone " + repo.getRepo());
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

    /**
     * 7,https://github.com/floci-io/floci-io.github.io.git
     * is removed, because of the no directory exception
     */
  @Test
  void pullAllFloci() throws IOException {
    List<Repo> repos = readCSV.readCsv(resourcePath("floci.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.bashCommand(
                    "/home/b/floci-repos" + "/" + repo.getDirectory(), "git pull --all");
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
