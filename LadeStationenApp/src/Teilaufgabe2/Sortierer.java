package Teilaufgabe2;

import Teilaufgabe1.Worngout;
import model.LadeStation;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sortierer {
    private final Worngout worngout = new Worngout();

    public static void sortiereNachPostleitzahl(List<LadeStation> ladestationen) {
        Collections.sort(ladestationen, new Comparator<LadeStation>() {
            @Override
            public int compare(LadeStation o1, LadeStation o2) {
                return o1.getPostleitzahl().compareTo(o2.getPostleitzahl());
            }
        });
    }

    public List<LadeStation> sortiereLadestationen(String dateiPfad) throws IOException {
        //Lade die Ladestationen
        List<LadeStation> ladestationen = Worngout.ladeDaten(dateiPfad);

        //Sortiere die Ladestationen nach Postleitzahl
        worngout.sortiereNachPostleitzahl(ladestationen);

        //Sortiere die Ladestationen nach Anschlussleistung
        worngout.sortiereNachAnschlussleistung(ladestationen);

        // Sortiere die Ladestationen nach Betreiber
        worngout.sortiereNachBetreiber(ladestationen);

        // Sortiere die Ladestationen nach Ort
        worngout.sortiereNachOrt(ladestationen);

        // Sortiere die Ladestationen nach Bundesland
        worngout.sortiereNachBundesland(ladestationen);
        return ladestationen;
    }


}

