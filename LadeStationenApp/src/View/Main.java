package View;

import Teilaufgabe2.Sortierer;
import model.LadeStation;
import res.Strings;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

//-------------------------------------------------------------------------------------------
// Teilaufgabe1
/*
        //Erstelle eine Instanz der Klasse LadeStationenLoader
        Worngout loader = new Worngout();

        //Lade Daten aus der Datei und speichere sie in einer Collection
        List<LadeStation> validePositionen = Worngout.ladeDaten(Strings.DATEI_PATH);

        //Gib die Anzahl der validen Positionen aus
        System.out.println("Anzahl valider Ladestationen: " + validePositionen.size() +
                " (" + loader.getValidierungszeit() + " Millisekunden)");

<
 */

//-------------------------------------------------------------------------------------------
//Teilaufgabe2


        Sortierer sortierer = new Sortierer();
        List<LadeStation> ladestationen = sortierer.sortiereLadestationen(Strings.DATEI_PATH);

        // Ausgabe der sortierten Ladestationen
        for (LadeStation ladeStation : ladestationen) {
            System.out.println("Betreiber: " + ladeStation.getBetreiber() + " , Adresse: " +
                    ladeStation.getStrasse() + " " + ladeStation.getHausnummer() + ", " +
                    ladeStation.getPostleitzahl() + " " + ladeStation.getOrt() + ", " + ladeStation.getBundesland()
                    + " , GeoPosition: " + "Breitengrad: " + ladeStation.getBreitengrad() + " Längengrad: " +
                    ladeStation.getLaengengrad() + " , Anschlussleistung: " + ladeStation.getAnschlussleistung());
        }
penis

//-------------------------------------------------------------------------------------------
// Teilaufgabe3
        Sortierer sortierer = new Sortierer();
        List<LadeStation> ladestationen = sortierer.sortiereLadestationen(Strings.DATEI_PATH);
        RedundancyChecker redundancyChecker = new RedundancyChecker();
        redundancyChecker.removeRedundantStations(ladestationen, 25, 250);


// Ausgabe der entfernten redundaten Ladestationen


    }
}
