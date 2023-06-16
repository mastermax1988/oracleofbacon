package oracleofbacon;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) {
      Parser reader = new Parser();
      HashSet<Film> films = new HashSet<>();
      HashMap<String, Actor> actors = new HashMap<>();
      reader.readFile(films, actors);
      Breitensuche b = new Breitensuche(films, actors);
      b.suche("Sean Connery", "Roger Moore");
      }
}