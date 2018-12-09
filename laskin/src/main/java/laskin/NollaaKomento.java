/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author samisaukkonen
 */
public class NollaaKomento implements Komento {

    private TextField tulosKentta;
    private TextField syotekentta;
    private Sovelluslogiikka sovellus;

    private Button nollaa;
    private Button undo;

    private int viimeTulos;

    public NollaaKomento(TextField tulosKentta, TextField syotekentta, Sovelluslogiikka sovellus, Button nollaa, Button undo) {
        this.tulosKentta = tulosKentta;
        this.syotekentta = syotekentta;
        this.sovellus = sovellus;

        this.nollaa = nollaa;
        this.undo = undo;

        this.viimeTulos = 0;
    }

    @Override
    public void suorita() {
        viimeTulos = sovellus.tulos();

        sovellus.nollaa();

        tulosKentta.setText("0");
        syotekentta.setText("");

        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        sovellus.asetaArvoksi(viimeTulos);

        int laskunTulos = sovellus.tulos();

        syotekentta.setText("");
        tulosKentta.setText("" + laskunTulos);

        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }

        undo.disableProperty().set(true);
    }
}
