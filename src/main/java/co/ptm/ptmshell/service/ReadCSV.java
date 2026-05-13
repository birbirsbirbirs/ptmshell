package co.ptm.ptmshell.service;

import co.ptm.ptmshell.model.Repo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReadCSV {
  List<Repo> repoList = new ArrayList<>();

  public String bashCommand(String dir, String command) throws IOException, InterruptedException {
    Process process =
        new ProcessBuilder("bash", "-c", command)
            .directory(new File(dir))
            .redirectErrorStream(true)
            .start();
    StringBuilder output = new StringBuilder();

    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(process.getInputStream()))) {

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line).append("\n");
      }
    }

    int exitCode = process.waitFor();

    return "Exit Code: " + exitCode + "\n" + output;
  }

  public List<Repo> readCsv() throws IOException {
    String[] HEADERS = {"SN", "repo"};
    Reader in =
        new FileReader("/Users/surajgurung/IdeaProjects/ptmshell/src/main/resources/repos_spring.csv");
    CSVFormat csvFormat =
        CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();
    CSVParser records = csvFormat.parse(in);
    for (CSVRecord record : records) {
      Repo repo =
          Repo.builder().sn(Integer.valueOf(record.get("SN"))).repo(record.get("repo")).build();
      this.repoList.add(repo);
    }
    return this.repoList;
  }
}
