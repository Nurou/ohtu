package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset {

  private static final Scanner scanner = new Scanner(System.in);

  private Tekoaly tekoaly;

  public KPSTekoaly(Tekoaly tekoaly) {
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
