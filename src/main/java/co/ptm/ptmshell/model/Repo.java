package co.ptm.ptmshell.model;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.util.ObjectUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Repo {
  private Integer sn;
  private String repo;
  private @Nullable String directory;

  public String getDirectory() {
    if (ObjectUtils.isEmpty(this.directory)) {
      String directory;
      directory = Arrays.stream(this.repo.split("/")).toList().getLast();
      directory = Arrays.stream(directory.split("\\.")).toList().getFirst();
      this.directory = directory;
      return directory;
    } else {
      return this.directory;
    }
  }
}
