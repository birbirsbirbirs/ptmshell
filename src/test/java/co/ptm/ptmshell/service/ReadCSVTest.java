package co.ptm.ptmshell.service;

import co.ptm.ptmshell.model.Repo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReadCSVTest {

  private static String homeDirectory() {
    return System.getProperty("user.home");
  }

  private static String projectDirectory() {
    return System.getProperty("user.dir");
  }

  private static String resourcePath(String fileName) {
    return Path.of(projectDirectory(), "src", "main", "resources", fileName).toString();
  }

  private static String cloneDirectory(String directoryName) {
    return Path.of(homeDirectory(), "repo-clones", directoryName).toString();
  }

  private static void createCloneDirectory(String directoryName) throws IOException {
    Files.createDirectories(Path.of(cloneDirectory(directoryName)));
  }

  @InjectMocks private @NonNull ReadCSV readCSV;

  @Test
  void executeCommandTest() throws IOException, InterruptedException {
    String createDirectory = readCSV.executeCommand(projectDirectory(), "mkdir test test2");
    System.out.println(createDirectory);
  }

  @Test
  void readCsvTest() throws IOException {
    List<Repo> repos = readCSV.readCsv(resourcePath("repos_spring.csv"));
    repos.forEach(System.out::println);
  }

  @Test
  void cloneReposTest() throws IOException, InterruptedException {
    createCloneDirectory("spring-repos");
    List<Repo> repos = readCSV.readCsv(resourcePath("repos_spring.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.executeCommand(
                    cloneDirectory("spring-repos"), "git clone " + repo.getRepo());
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
                readCSV.executeCommand(
                    Path.of(cloneDirectory("spring-repos"), repo.getDirectory()).toString(),
                    "git pull --all");
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
    createCloneDirectory("spring-cloud-repos");
    List<Repo> repos = readCSV.readCsv(resourcePath("repos_cloud_spring.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.executeCommand(
                    cloneDirectory("spring-cloud-repos"), "git clone " + repo.getRepo());
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
                readCSV.executeCommand(
                    Path.of(cloneDirectory("spring-cloud-repos"), repo.getDirectory()).toString(),
                    "git pull --all");
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
    createCloneDirectory("additional-repos");
    List<Repo> repos = readCSV.readCsv(resourcePath("additional_repos.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.executeCommand(
                    cloneDirectory("additional-repos"), "git clone " + repo.getRepo());
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
                readCSV.executeCommand(
                    Path.of(cloneDirectory("additional-repos"), repo.getDirectory()).toString(),
                    "git pull --all");
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
    createCloneDirectory("craft-repos");
    List<Repo> repos = readCSV.readCsv(resourcePath("craft.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.executeCommand(
                    cloneDirectory("craft-repos"), "git clone " + repo.getRepo());
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
                readCSV.executeCommand(
                    Path.of(cloneDirectory("craft-repos"), repo.getDirectory()).toString(),
                    "git pull --all");
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
    createCloneDirectory("floci-repos");
    List<Repo> repos = readCSV.readCsv(resourcePath("floci.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.executeCommand(
                    cloneDirectory("floci-repos"), "git clone " + repo.getRepo());
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }

  /**
   * 7,https://github.com/floci-io/floci-io.github.io.git is removed, because of the no directory
   * exception
   */
  @Test
  void pullAllFloci() throws IOException {
    List<Repo> repos = readCSV.readCsv(resourcePath("floci.csv"));
    repos.forEach(
        repo -> {
          try {
            String s =
                readCSV.executeCommand(
                    Path.of(cloneDirectory("floci-repos"), repo.getDirectory()).toString(),
                    "git pull --all");
            System.out.println(s);
          } catch (IOException e) {
            throw new RuntimeException(e);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
