package ohtu.kivipaperisakset;

import java.util.Scanner;

/* 
    shared functionality for all three game types
*/

public abstract class KiviPaperiSakset {

  private static final Scanner scanner = new Scanner(System.in);
  protected String viimeinenEnsimmaisenSiirto;

  // template method
  public void pelaa() {
    Tuomari tuomari = new Tuomari();
    System.out.println(
      "peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s"
    );
    String ekanSiirto = ensimmaisenSiirto();
    String tokanSiirto = toisenSiirto();

    while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
      tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
      System.out.println(tuomari);
      System.out.println();

      ekanSiirto = ensimmaisenSiirto();
      tokanSiirto = toisenSiirto();
    }

    System.out.println();
    System.out.println("Kiitos!");
    System.out.println(tuomari);
  }

  protected String ensimmaisenSiirto() {
    System.out.print("Ensimmäisen pelaajan siirto: ");
    String siirto = scanner.nextLine();
    this.viimeinenEnsimmaisenSiirto = siirto;
    return siirto;
  }

  protected abstract String toisenSiirto();

  protected static boolean onkoOkSiirto(String siirto) {
    return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
  }
}
