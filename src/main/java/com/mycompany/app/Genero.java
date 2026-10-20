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

    public static String getGenre(int climaId) {
        
        //Céu limpo
        if (climaId == 800) {
            return "Rock";
        }

        // Tempestade
        if (climaId <= 232) {
            return "Jazz";
        }

        //Garoa
        if(climaId <= 321){
            return "Mpb";
        }

        //Chuva
        if (climaId < 600) { 
            return "Lo-fi";
        }

        //Neve
        if (climaId < 701) {
            return "Musica classica";
        }

        //Nublado
        if (climaId > 800) { 
            return "Pop";
        }

        return "null";
    }
}
