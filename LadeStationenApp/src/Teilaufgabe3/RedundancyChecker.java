package Teilaufgabe3;

import model.LadeStation;

import java.util.ArrayList;
import java.util.List;

public class RedundancyChecker {
    public static boolean check = true;

    public RedundancyChecker() {
        //removeRedundantStations();
    }

    public List<LadeStation> removeRedundantStations(List<LadeStation> ladestationen, int epsilon, int maxDistance) {
        // Prüfe, ob die Argumente korrekt sind
        if (epsilon < 0 || maxDistance < 0) {
            System.out.println("Ungültige Argumente");

        } else {
            return ladestationen;
        }
        //Liste um die redundanten Stationen zu speichern
        List<LadeStation> redundantStations = new ArrayList<>();
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
                // calculate distance using Haversine formula
                double a = Math.pow(Math.sin(latDistance / 2), 2) +
                        Math.cos(refLat) * Math.cos(compLat) *
                                Math.pow(Math.sin(lonDistance / 2), 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double distance = 6371 * c * 1000;
                if (distance <= maxDistance) {
                    double powerDifference = Math.abs(referenceStation.getAnschlussleistung() - comparisonStation.getAnschlussleistung());
                    if (powerDifference <= epsilon) {
                        redundantStations.add(comparisonStation);
                    }
                }
            }
        }
//Entferne die redundanten Ladestationen aus der Liste
        ladestationen.removeAll(redundantStations);
//Speichere die Dauer der Ausführung
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
//Gib die Anzahl der Ladestationen und die Dauer der Ausführung aus
        System.out.println("Anzahl Ladestationen nach Löschung redundanter Stationen: " + ladestationen.size() + " (Millisekunden: " + duration + ")");

        return ladestationen;
    }
}