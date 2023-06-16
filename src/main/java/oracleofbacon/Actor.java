package oracleofbacon;

import java.util.List;

public class Actor {
  public String name;
  public List<Film> films;

  public Actor(String name, List<Film> films) {
    this.name = name;
    this.films = films;
  }
}
