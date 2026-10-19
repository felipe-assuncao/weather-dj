package com.mycompany.app;

import io.github.cdimascio.dotenv.Dotenv;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.neovisionaries.i18n.CountryCode;
import java.util.Arrays;
import java.util.List;

public class SpotifyClient {
  Dotenv envfile = Dotenv.load();

  String apiKey = envfile.get("SPOTIFY_API_KEY");
  String token = "";
  SpotifyApi spotifyApi = new SpotifyApi.Builder()
      .setAccessToken(token)
      .build();

  private List<Track> listarMusicasPorGenero(String genero) throws Exception {
    SearchTracksRequest request = spotifyApi
        .searchTracks("genre:\"" + genero + "\"")
        .limit(10) // 10 músicas
        .offset(0)
        .market(CountryCode.BR) // opcional
        .build();

    Paging<Track> resultado = request.execute();
    return Arrays.asList(resultado.getItems());
  }

  public void imprimirMusicas(String genero) {
    try {
      List<Track> lista = listarMusicasPorGenero(genero);

      for (Track t : lista) {
        System.out.println(t.getName() + " - " + t.getArtists()[0].getName());
      }
    } catch (Exception e) {
      System.err.println("Erro ao buscar musicas: " + e.getMessage());
    }
  }
}