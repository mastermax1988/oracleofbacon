package oracleofbacon;

import java.util.List;

public class Film {
  public String title;
  public List<String> castNames;

  public Film(String title, List<String> castNames) {
    this.title = title;
    this.castNames = castNames;
  }
}
