/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.*;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author mluukkai
 */
public class Main {

  public static void main(String[] args) throws IOException {
    String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

    String bodyText = Request.Get(url).execute().returnContent().asString();

    System.out.println("json-muotoinen data:");
    // System.out.println(bodyText);

    Gson mapper = new Gson();
    Player[] players = mapper.fromJson(bodyText, Player[].class);

    players =
      Arrays
        .stream(players)
        .filter(player -> player.getNationality().equals("FIN"))
        .toArray(Player[]::new);

    System.out.println("Oliot:");
    for (Player player : players) {
      System.out.println(player);
    }
  }
}
