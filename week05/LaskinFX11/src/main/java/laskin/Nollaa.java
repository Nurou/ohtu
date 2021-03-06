package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {

  public Nollaa(
    TextField tuloskentta,
    TextField syotekentta,
    Button nollaa,
    Button undo,
    Sovelluslogiikka sovellus
  ) {
    super(tuloskentta, syotekentta, nollaa, undo, sovellus);
  }

  @Override
  public void suorita() {
    this.prevResult = sovellus.tulos();
    sovellus.nollaa();
  }

  @Override
  public void peru() {
    sovellus.setTulos(prevResult);
  }
}
