package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {

    private Map<Button, Komento> komennot;
    private Komento viimeKomento;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        Sovelluslogiikka sovellus = new Sovelluslogiikka();

        this.komennot = new HashMap<>();
        this.komennot.put(plus, new SummaKomento(tuloskentta, syotekentta, sovellus, nollaa, undo));
        this.komennot.put(miinus, new MiinusKomento(tuloskentta, syotekentta, sovellus, nollaa, undo));
        this.komennot.put(nollaa, new NollaaKomento(tuloskentta, syotekentta, sovellus, nollaa, undo));
        
        this.viimeKomento = null;
    }

    @Override
    public void handle(Event event) {
        Komento komento = komennot.get((Button) event.getTarget());

        if (komento != null) {
            komento.suorita();
            viimeKomento = komento;
        } else {
            viimeKomento.peru();
            viimeKomento = null;
        }
    }
}
