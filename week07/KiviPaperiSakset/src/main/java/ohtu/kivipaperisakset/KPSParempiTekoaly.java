package ohtu.kivipaperisakset;

import java.util.Scanner;
import java.util.Scanner;

public class KPSParempiTekoaly extends KiviPaperiSakset {

  private static final Scanner scanner = new Scanner(System.in);

  private TekoalyParannettu tekoaly;

  public KPSParempiTekoaly(TekoalyParannettu tekoaly) {
    this.tekoaly = tekoaly;
  }

  @Override
  protected String toisenSiirto() {
    String tokanSiirto = tekoaly.annaSiirto();
    System.out.println("Tietokone valitsi: " + tokanSiirto);
    tekoaly.asetaSiirto(this.viimeinenEnsimmaisenSiirto);
    return tokanSiirto;
  }
}
