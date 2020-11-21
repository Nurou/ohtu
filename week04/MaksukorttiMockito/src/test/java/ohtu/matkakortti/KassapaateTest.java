package ohtu.matkakortti;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ohtu.matkakortti.Kassapaate;
import ohtu.matkakortti.Maksukortti;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

  Kassapaate kassa;
  Maksukortti kortti;

  @Before
  public void setUp() {
    kassa = new Kassapaate();
    kortti = mock(Maksukortti.class);
  }

  @Test
  public void kortiltaVelotetaanHintaJosRahaaOn() {
    when(kortti.getSaldo()).thenReturn(10);
    kassa.ostaLounas(kortti);

    // check getSaldo() called once
    verify(kortti, times(1)).getSaldo();
    // verify that the purchase happened with the given param
    verify(kortti).osta(eq(Kassapaate.HINTA));
  }

  @Test
  public void kortiltaEiVelotetaJosRahaEiRiita() {
    when(kortti.getSaldo()).thenReturn(4);
    kassa.ostaLounas(kortti);

    verify(kortti, times(1)).getSaldo();
    verify(kortti, times(0)).osta(anyInt());
  }

  // kassapäätteen metodin lataa kutsu lisää maksukortille ladattavan rahamäärän käyttäen kortin metodia lataa jos ladattava summa on positiivinen

  @Test
  public void korttiLadataanJosLadattavaSummaOnPositiivinen() {
    kassa.lataa(kortti, 10);
    verify(kortti, times(1)).lataa(anyInt());
  }

  // kassapäätteen metodin lataa kutsu ei tee maksukortille mitään jos ladattava summa on negatiivinen

  @Test
  public void korttiaEiLadaJosLadattavaSummaOnNegatiivinen() {
    kassa.lataa(kortti, -1);
    verify(kortti, times(0)).lataa(anyInt());
  }
}
