package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

  private static final Scanner scanner = new Scanner(System.in);
  private static KiviPaperiSakset currentGame;

  public static void main(String[] args) {
    while (true) {
      System.out.println(
        "\nValitse pelataanko" +
        "\n (a) ihmistä vastaan " +
        "\n (b) tekoälyä vastaan" +
        "\n (c) parannettua tekoälyä vastaan" +
        "\nmuilla valinnoilla lopetataan"
      );

      String vastaus = scanner.nextLine();
      if (vastaus.endsWith("a")) {
        currentGame = new KPSPelaajaVsPelaaja();
      } else if (vastaus.endsWith("b")) {
        currentGame = new KPSTekoaly(new Tekoaly());
      } else if (vastaus.endsWith("c")) {
        currentGame = new KPSParempiTekoaly(new TekoalyParannettu(20));
      } else {
        break;
      }
      currentGame.pelaa();
    }
  }
}
