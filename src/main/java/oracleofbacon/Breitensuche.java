package oracleofbacon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Breitensuche {
  private HashSet<Film> films;
  private HashMap<String, Actor> actors;

  public Breitensuche(HashSet<Film> films, HashMap<String, Actor> actors) {
    this.films = films;
    this.actors = actors;
  }

  public void suche(String name1, String name2){
    HashSet<Film> besucht = new HashSet<>();
    List<Film> warteschlange = new ArrayList<>();
    Actor start = actors.get(name1);
    fügeUnbesuchteFilmeZurWarteschlangeHinzu(besucht, warteschlange, start);
    while (!warteschlange.isEmpty()){
      Film f = warteschlange.get(0);
      warteschlange.remove(0);
      System.out.println(f.title);
      if(f.castNames.contains(name2)){
        System.out.println("Breitensuche ist am Ziel");
        return;
      }
      for(String actor : f.castNames){
        fügeUnbesuchteFilmeZurWarteschlangeHinzu(besucht, warteschlange, actors.get(actor));
      }
    }
  }

  private static void fügeUnbesuchteFilmeZurWarteschlangeHinzu(HashSet<Film> besucht, List<Film> warteschlange, Actor start) {
    for(Film f : start.films){
      if(!besucht.contains(f)){
        warteschlange.add(f);
      }
    }
  }
}
