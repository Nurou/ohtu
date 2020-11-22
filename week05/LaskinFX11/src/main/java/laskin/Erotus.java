package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento {

  public Erotus(
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
    sovellus.miinus(0);
  }

  @Override
  public void peru() {
    // sovellus.miinus(0);
    return;
  }
}
