package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {

  public Summa(
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
    sovellus.plus(0);
  }

  @Override
  public void peru() {
    return;
  }
}
