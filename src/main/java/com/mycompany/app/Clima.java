package com.mycompany.app;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.enums.WeatherCondition;
import com.github.prominence.openweathermap.api.exception.InvalidAuthTokenException;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import io.github.cdimascio.dotenv.Dotenv;


public class Clima {
  Dotenv envfile = Dotenv.load();

  String apiKey = envfile.get("WEATHER_API_KEY");
  String cidade = "Natal";
  OpenWeatherMapClient weatherClient = null; // Cliente que vai se conectar a API da OpenWeather
  Weather weather = null; // Obj contendo as infos. de tempo da cidade fornecida

  Clima(String cidade) {
    this.cidade = cidade;
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
  public String getWeatherName() { return weather.getWeatherState().getName(); }
  public WeatherCondition getWeatherEnum() { return weather.getWeatherState().getWeatherConditionEnum(); }
  public String getWeatherDescription() { return weather.getWeatherState().getDescription(); }
  public int getWeatherID() { return weather.getWeatherState().getId(); }

}