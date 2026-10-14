package com.mycompany.app;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.weather.Weather;


public class Clima {
  String apiKey = null;
  String cidade = "Natal";
  OpenWeatherMapClient weatherClient = null; // Cliente que vai se conectar a API da OpenWeather
  Weather weather = null; // Obj contendo as infos. de tempo da cidade fornecida

  Clima(String cidade, String apiKey) {
    this.cidade = cidade;
    this.apiKey = apiKey;
    authenticate();
    setWeatherByCity();
  }

  private void authenticate() {
    try {
      weatherClient = new OpenWeatherMapClient(apiKey);
    } catch (InvalidAuthTokenException e) {
      System.out.println("Erro ao autenticar token de acesso: " + e.getMessage());
    }
  }

  private void setWeatherByCity() {
    try {
      weather = weatherClient
      .currentWeather()
      .single()
      .byCityName(cidade)
      .language(Language.PORTUGUES_BRAZIL)
      .unitSystem(UnitSystem.METRIC)
      .retrieve()
      .asJava();
    } catch (NoDataFoundException e) {
      System.out.println("Cidade não encontrada/inválida.");
    }
  } 


  public String getSummarizedInfo() { return weather.toString(); }
  public double getTemperatura() { return weather.getTemperature().getValue(); } // Retorna em Celsius
  public double getNuvens() { return weather.getClouds().getValue(); } // Retorna a porcentagem de nuvens
  public double getChuva() { return weather.getRain().getOneHourLevel(); } // Retorna quantos mm de chuva na ultima hora
}