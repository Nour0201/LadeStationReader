package Teilaufgabe1;

import Teilaufgabe3.RedundancyChecker;
import model.LadeStation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Worngout {
    private static Instant startZeit;
    private static Instant endZeit;

    public static List<LadeStation> ladeDaten(String dateiPfad) throws IOException {
        //Startzeit für das Lesen der Datei
        startZeit = Instant.now();

        //Lese die Datei und speichere die Daten in einer Liste
        File datei = new File(dateiPfad);
        BufferedReader br = new BufferedReader(new FileReader(datei));
        List<String> zeilen = new ArrayList<>();
        String zeile;
        while ((zeile = br.readLine()) != null) {
            zeilen.add(zeile);
        }
        br.close();

        //Endzeit für das Lesen der Datei
        endZeit = Instant.now();
        System.out.println("Lade Daten aus Datei: " + datei.getPath() + " Eingelesene Zeilen: "
                + zeilen.size() + " (" + Duration.between(startZeit, endZeit).toMillis() +
                " Millisekunden)");

        //Startzeit für die Datenvalidierung
        startZeit = Instant.now();

        //Validiere die Daten und speichere valide Daten in einer Collection
        List<LadeStation> validePositionen = new ArrayList<>();
        for (String z : zeilen) {
            String[] werte = z.split(";");
            double breite = Double.parseDouble(werte[6]);
            double laenge = Double.parseDouble(werte[7]);

            if (RedundancyChecker.check) {
                if (breite > -90 && breite < 90 && laenge > -180 && laenge < 180) {
                    validePositionen.add(new LadeStation(werte[0], werte[1], werte[2],
                            werte[3], werte[4], werte[5], breite, laenge, Double.parseDouble(werte[8])));
                } else if (RedundancyChecker.check) {
                    System.out.println("Trage Ladestation nicht ein. Breiten- oder Längengrad " +
                            "nicht korrekt: GeoPosition{breitenGrad=" + breite + ", laengenGrad=" +
                            laenge + "}");
                }
            }
        }

        //Endzeit für die Datenvalidierung
        endZeit = Instant.now();

        return validePositionen;
    }

    //-----------------------------------------------------------------------------------
    public long getValidierungszeit() {
        return Duration.between(startZeit, endZeit).toMillis();
    }

    public void sortiereNachAnschlussleistung(List<LadeStation> ladestationen) {
    }

    public void sortiereNachPostleitzahl(List<LadeStation> ladestationen) {
    }

    public void sortiereNachBetreiber(List<LadeStation> ladestationen) {
    }

    public void sortiereNachOrt(List<LadeStation> ladestationen) {
    }

    public void sortiereNachBundesland(List<LadeStation> ladestationen) {
    }
}

