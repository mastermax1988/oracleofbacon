package oracleofbacon;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Parser {
  public void readFile(HashSet<Film> films, HashMap<String, Actor> actors) {
    try {
      // Get the resource URL
      ClassLoader classLoader = getClass().getClassLoader();
      InputStream inputStream = classLoader.getResourceAsStream("data.txt");

      if (inputStream != null) {
        // Read the file using BufferedReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        JsonParser parser = new JsonParser();
        while ((line = reader.readLine()) != null) {
          JsonObject film = parser.parse(line).getAsJsonObject();
          List<String> actorNames = new ArrayList<>();
          film.get("cast").getAsJsonArray().forEach((e) ->{
             String actor = e.toString();
             actor = actor.replace("[","").replaceAll("]","").replaceAll("\"", "");
             String[] split = actor.split("\\|");
             actor = split.length==1?actor:split[1].trim();
             actorNames.add(actor);
          });
          films.add(new Film(film.get("title").getAsString(), actorNames));
        }
        reader.close();
      } else {
        System.out.println("File not found!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    //lege alle Schauspieler an, aber noch ohne die Filme
    for(Film f : films){
      for (String castName : f.castNames) {
          if(!actors.containsKey(castName)){
            actors.put(castName, new Actor(castName, new ArrayList<>()));
          }
      }
    }

    //füge die Filme den Schauspielern hinzu
    for(Film f : films){
      for(String castName : f.castNames){
        actors.get(castName).films.add(f);
      }
    }
    System.out.println("Import done");
  }

}