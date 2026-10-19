package com.mycompany.app;

public class Genero {

    /**
     * 2XX Tempestade -> Jazz
     * 3XX Garoa
     * 5XX Chuva
     * 6XX Neve
     * 7XX Atmosphere
     * 800 Ceu limpo
     * 80X Nublado
     * 
     * @param climaId     ID do tempo da cidade. (Diz condição do céu)
     * @param temperatura Temperatura atual da cidade.
     * @return Gênero musical correspondente ao tempo da cidade.
     */

    public static String getGenre(int climaId, double temperatura) {
        if (climaId == 800) {
            return "desert rock";
        }

        // Tempestade
        if (climaId <= 232) {
            return "jazz";
        }
        if (climaId < 600) { // Garoa e chuva
            return "mpb";
        }
        if (climaId < 701) { // Neve
            return "musica classica";
        }
        if (climaId > 800) { // nublado/parcialmente nublado
            return "pop/raggae";
        }
        return "null";
    }
}
