/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author mluukkai
 */
public class Main {

  public static void main(String[] args) throws IOException {
    String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

    String bodyText = Request.Get(url).execute().returnContent().asString();

    System.out.println("Players from FIN: " + LocalDate.now());

    Gson mapper = new Gson();
    Player[] players = mapper.fromJson(bodyText, Player[].class);

    players =
      Arrays
        .stream(players)
        .filter(player -> player.getNationality().equals("FIN"))
        .sorted(Comparator.comparing(Player::getGoalsAndAssists).reversed())
        .toArray(Player[]::new);

    System.out.println("Oliot:");
    for (Player player : players) {
      System.out.println(player);
    }
  }
}
