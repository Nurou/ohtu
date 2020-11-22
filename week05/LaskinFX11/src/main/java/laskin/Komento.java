package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {

  protected TextField tuloskentta;
  protected TextField syotekentta;
  protected Button nollaa;
  protected Button undo;
  protected Sovelluslogiikka sovellus;

  protected int prevResult;

  public Komento(
    TextField tuloskentta,
    TextField syotekentta,
    Button nollaa,
    Button undo,
    Sovelluslogiikka sovellus
  ) {
    this.tuloskentta = tuloskentta;
    this.syotekentta = syotekentta;
    this.nollaa = nollaa;
    this.undo = undo;
    this.sovellus = sovellus;

    this.prevResult = 0;
  }

  public abstract void suorita();

  public abstract void peru();
}
