package ohtu.intjoukkosovellus;

public class IntJoukko {

  public static final int KAPASITEETTI = 5, OLETUSKASVATUS = 5; // aloitustalukon koko // luotava uusi taulukko on
  // näin paljon isompi kuin vanha
  private int kasvatuskoko; // Uusi taulukko on tämän verran vanhaa suurempi.
  private int[] lukujono; // Joukon luvut säilytetään taulukon alkupäässä.
  private int alkioidenLkm; // Tyhjässä joukossa alkioiden_määrä on nolla.

  public IntJoukko() {
    initialiseSet(KAPASITEETTI, OLETUSKASVATUS);
  }

  public IntJoukko(int kapasiteetti) {
    validateCapacity(kapasiteetti);
    initialiseSet(kapasiteetti, OLETUSKASVATUS);
  }

  public IntJoukko(int kapasiteetti, int kasvatuskoko) {
    validateCapacity(kapasiteetti);
    validateGrowthNumber(kasvatuskoko);
    initialiseSet(kapasiteetti, kasvatuskoko);
  }

  public boolean lisaa(int luku) {
    if (alkioidenLkm == 0) {
      lukujono[0] = luku;
      alkioidenLkm++;
      return true;
    }

    if (!kuuluu(luku)) {
      lukujono[alkioidenLkm] = luku;
      alkioidenLkm++;
      if (alkioidenLkm % lukujono.length == 0) {
        int[] taulukkoOld = new int[lukujono.length];
        taulukkoOld = lukujono;
        kopioiTaulukko(lukujono, taulukkoOld);
        lukujono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, lukujono);
      }
      return true;
    }

    return false;
  }

  public boolean kuuluu(int luku) {
    for (int i = 0; i < alkioidenLkm; i++) {
      if (luku == lukujono[i]) {
        return true;
      }
    }

    return false;
  }

  public boolean poista(int luku) {
    int luvunIndeksi = -1;
    int apuLukujono;

    for (int i = 0; i < alkioidenLkm; i++) {
      if (luku == lukujono[i]) {
        luvunIndeksi = i;
        lukujono[luvunIndeksi] = 0;
        break;
      }
    }
    if (luvunIndeksi != -1) {
      for (int j = luvunIndeksi; j < alkioidenLkm - 1; j++) {
        apuLukujono = lukujono[j];
        lukujono[j] = lukujono[j + 1];
        lukujono[j + 1] = apuLukujono;
      }
      alkioidenLkm--;
      return true;
    }

    return false;
  }

  private void kopioiTaulukko(int[] vanha, int[] uusi) {
    for (int i = 0; i < vanha.length; i++) {
      uusi[i] = vanha[i];
    }
  }

  public int mahtavuus() {
    return alkioidenLkm;
  }

  @Override
  public String toString() {
    if (alkioidenLkm == 0) {
      return "{}";
    } else if (alkioidenLkm == 1) {
      return "{" + lukujono[0] + "}";
    } else {
      String tuotos = "{";
      for (int i = 0; i < alkioidenLkm - 1; i++) {
        tuotos += lukujono[i];
        tuotos += ", ";
      }
      tuotos += lukujono[alkioidenLkm - 1];
      tuotos += "}";
      return tuotos;
    }
  }

  public int[] toIntArray() {
    int[] taulu = new int[alkioidenLkm];
    for (int i = 0; i < taulu.length; i++) {
      taulu[i] = lukujono[i];
    }
    return taulu;
  }

  public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
    IntJoukko x = new IntJoukko();
    int[] aTaulu = a.toIntArray();
    int[] bTaulu = b.toIntArray();
    for (int i = 0; i < aTaulu.length; i++) {
      x.lisaa(aTaulu[i]);
    }
    for (int i = 0; i < bTaulu.length; i++) {
      x.lisaa(bTaulu[i]);
    }
    return x;
  }

  public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
    IntJoukko y = new IntJoukko();
    int[] aTaulu = a.toIntArray();
    int[] bTaulu = b.toIntArray();
    for (int i = 0; i < aTaulu.length; i++) {
      for (int j = 0; j < bTaulu.length; j++) {
        if (aTaulu[i] == bTaulu[j]) {
          y.lisaa(bTaulu[j]);
        }
      }
    }
    return y;
  }

  public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
    IntJoukko z = new IntJoukko();
    int[] aTaulu = a.toIntArray();
    int[] bTaulu = b.toIntArray();
    for (int i = 0; i < aTaulu.length; i++) {
      z.lisaa(aTaulu[i]);
    }
    for (int i = 0; i < bTaulu.length; i++) {
      z.poista(bTaulu[i]);
    }

    return z;
  }

  private Boolean validateCapacity(int kapasiteetti) {
    if (kapasiteetti < 0) {
      throw new IndexOutOfBoundsException("Kapasitteetti on negatiivinen."); //heitin vaan jotain :D
    }
    return true;
  }

  private Boolean validateGrowthNumber(int kasvatuskoko) {
    if (kasvatuskoko < 0) {
      throw new IndexOutOfBoundsException("Kasvatuskoko on negatiivinen."); //heitin vaan jotain :D
    }
    return true;
  }

  private void initialiseSet(int kapasiteetti, int kasvatuskoko) {
    lukujono = new int[kapasiteetti];
    for (int i = 0; i < lukujono.length; i++) {
      lukujono[i] = 0;
    }
    alkioidenLkm = 0;
    this.kasvatuskoko = kasvatuskoko;
  }
}
