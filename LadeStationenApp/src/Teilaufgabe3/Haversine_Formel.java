package Teilaufgabe3;

import Teilaufgabe1.Worngout;
import Teilaufgabe2.Sortierer;
import model.LadeStation;
import res.Strings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Haversine_Formel {
    //Liste um die Ladestationen zu speichern
    List<LadeStation> ladestationen;

    public void removeRedundantStations(int epsilon, int maxDistance) {
        //Gibt den Wert von Epsilon und maxDistance aus
        System.out.println("Prüfe auf Redundanz mit Epsilon = " + epsilon + " und maxDistance = " + maxDistance);
        // Prüfe, ob die Argumente korrekt sind
        if (epsilon < 0 || maxDistance < 0) {
            System.out.println("Ungültige Argumente");
        } else {
            //Liste um die redundanten Stationen zu speichern
            List<LadeStation> redundantStations = new ArrayList<>();
            //Lese und speichere die Ladestationen
            try {
                this.ladestationen = Worngout.ladeDaten(Strings.DATEI_PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Lade Daten aus Datei: " + Strings.DATEI_PATH);
            //Variable um die Startzeit der Ausführung zu speichern
            long startTime = System.currentTimeMillis();
            //Schleife um die Ladestationen zu vergleichen
            for (int i = 0; i < ladestationen.size(); i++) {
                LadeStation referenceStation = ladestationen.get(i);
                for (int j = i + 1; j < ladestationen.size(); j++) {
                    LadeStation comparisonStation = ladestationen.get(j);
                    //Berechne die Entfernung zwischen den Ladestationen (( Harversine_Formel ))
                    // distance between latitudes and longitudes
                    double latDistance = Math.toRadians(comparisonStation.getBreitengrad() - referenceStation.getBreitengrad());
                    double lonDistance = Math.toRadians(comparisonStation.getLaengengrad() - referenceStation.getLaengengrad());
                    // convert to radians
                    double refLat = referenceStation.getBreitengrad();
                    double compLat = comparisonStation.getBreitengrad();
                    refLat = Math.toRadians(refLat);
                    compLat = Math.toRadians(compLat);
                    // HarversineFormel_einSetzen
                    double a = Math.pow(Math.sin(latDistance / 2), 2) +
                            Math.pow(Math.sin(lonDistance / 2), 2) *
                                    Math.cos(refLat) *
                                    Math.cos(compLat);
                    double rad = 6371;
                    double c = 2 * Math.asin(Math.sqrt(a));
                    double distance = rad * c;
                    //Überprüfe, ob die Entfernung kleiner als ((Epsilon)) ist
                    if (distance <= epsilon || distance > maxDistance) {
                        redundantStations.add(comparisonStation);
                    }
                }
            }
            //Entferne die Array redundantStations
            ladestationen.removeAll(redundantStations);
            //Sortiere die Liste nach Postleitzahlen
            Sortierer.sortiereNachPostleitzahl(ladestationen);
            //Variable um die Endzeit der Ausführung zu speichern
            long endTime = System.currentTimeMillis();
            //Anzahl der Ladestationen nach Entfernung der redundanten Stationen ausgeben
            System.out.println("Anzahl Ladestationen nach Löschung redundanter Stationen: " + ladestationen.size() + " (Millisekunden: " + (endTime - startTime) + ")");
            //Ausgabe der verbleibenden Ladestationen
            for (LadeStation station : ladestationen) {
                System.out.println(station.toString());
            }
        }
    }
}