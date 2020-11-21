/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import static org.mockito.Mockito.*;

import org.junit.*;

/**
 *
 * @author joelhassan
 */
public class KauppaTest {

  Pankki pankki;
  Viitegeneraattori viite;
  Varasto varasto;
  Kauppa k;

  Tuote maito;

  @After
  public void tearDown() {
    pankki = null;
    viite = null;
    varasto = null;
    k = null;
    maito = null;
  }

  @Before
  public void setUp() {
    // luodaan ensin mock-oliot
    pankki = mock(Pankki.class);

    viite = mock(Viitegeneraattori.class);
    // määritellään että viitegeneraattori palauttaa viitten 42
    when(viite.uusi()).thenReturn(42);

    varasto = mock(Varasto.class);
    // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
    when(varasto.saldo(1)).thenReturn(10);
    when(varasto.saldo(2)).thenReturn(10);
    // out of stock item
    when(varasto.saldo(3)).thenReturn(0);

    maito = new Tuote(1, "maito", 5);

    when(varasto.haeTuote(1)).thenReturn(maito);
    when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "makkara", 20));
    when(varasto.haeTuote(3)).thenReturn(new Tuote(2, "kana", 20));

    // sitten testattava kauppa
    k = new Kauppa(varasto, pankki, viite);
  }

  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki)
      .tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
  }

  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1); // ostetaan tuotetta numero 1 eli maitoa
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
  }

  /* 
  
  aloitetaan asiointi, koriin lisätään kaksi eri tuotetta, joita varastossa on ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla
  */
  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKunTuotteetOvatEri() {
    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 25);
  }

  /* aloitetaan asiointi, koriin lisätään kaksi samaa tuotetta, jota on varastossa tarpeeksi ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla */
  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKunTuotteetOvatSamat() {
    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
  }

  /* aloitetaan asiointi, koriin lisätään tuote, jota on varastossa tarpeeksi ja tuote joka on loppu ja suoritetaan ostos, varmista että kutsutaan pankin metodia tilisiirto oikealla asiakkaalla, tilinumerolla ja summalla */

  @Test
  public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKunYksiTuotteistaOnLoppu() {
    // tehdään ostokset
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.lisaaKoriin(3);
    k.tilimaksu("pekka", "12345");

    // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
    verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
  }

  @Test
  public void asionninAloittaminenNollaaEdellisenOstoksenTiedot() {
    k.aloitaAsiointi();

    verify(pankki, times(0))
      .tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
  }

  @Test
  public void kauppaPyytääUudenViiteumeronJokaiselleMaksutapahtumalle() {
    when(viite.uusi()).thenReturn(1).thenReturn(2).thenReturn(3);

    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.tilimaksu("pekka", "12345");

    verify(pankki)
      .tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());

    k.aloitaAsiointi();
    k.lisaaKoriin(2);
    k.tilimaksu("pekka", "12345");

    verify(pankki)
      .tilisiirto(anyString(), eq(2), anyString(), anyString(), anyInt());
  }

  @Test
  public void koristaPostaminenPostaaTuotteen() {
    k.aloitaAsiointi();
    k.lisaaKoriin(1);
    k.poistaKorista(1);

    verify(varasto, times(1)).palautaVarastoon(maito);
  }
}
