/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author joelhassan
 */
public class StatisticsTest {
  Reader readerStub = new Reader() {

    public List<Player> getPlayers() {
      ArrayList<Player> players = new ArrayList<>();

      players.add(new Player("Semenko", "EDM", 4, 12));
      players.add(new Player("Lemieux", "PIT", 45, 54));
      players.add(new Player("Kurri", "EDM", 37, 53));
      players.add(new Player("Yzerman", "DET", 42, 56));
      players.add(new Player("Gretzky", "EDM", 35, 89));

      return players;
    }
  };

  Statistics stats;

  public StatisticsTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {
    // luodaan Statistics-olio joka käyttää "stubia"
    stats = new Statistics(readerStub);
  }

  @After
  public void tearDown() {}

  @Test
  public void validPlayerSearchReturnsCorrespondingPlayer() {
    var player = stats.search("Semenko");
    assertEquals("Semenko", player.getName());
  }

  @Test
  public void invalidPlayerSearchReturnsNull() {
    var player = stats.search("bleeergh.");
    assertNull(player);
  }

  @Test
  public void correctNumberOfPlayerReturnedForTeam() {
    assertEquals(1, stats.team("PIT").size());
  }

  @Test
  public void correctNumberOfTopScorersFetched() {
    assertEquals(3, stats.topScorers(2).size());
  }

  @Test
  public void correctTopScorersFetched() {
    var topScorers = stats.topScorers(1);
    assertEquals("Gretzky", topScorers.get(0).getName());
    assertEquals("Lemieux", topScorers.get(1).getName());
  }
}
