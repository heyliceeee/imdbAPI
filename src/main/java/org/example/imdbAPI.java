package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

public class imdbAPI {

    static void get250Movies(){
        try {
            //criar um httpclient
            HttpClient client = HttpClient.newHttpClient();

            //definir o url para o request
            URI uri = new URI("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json");

            //criar um httprequest
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .GET() //especifica que é um request do tipo GET
                    .build();

            //enviar o request e obter a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //imprimir a resposta
            System.out.println(response.body());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String[] parseJsonMovies(String json){
        Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("no match in " + json);
        }

        String[] moviesArray = matcher.group(1).split("\\},\\{");
        moviesArray[0] = moviesArray[0].substring(1);

        int last = moviesArray.length - 1;
        String lastString = moviesArray[last];
        moviesArray[last] = lastString.substring(0, lastString.length() - 1);

        return moviesArray;
    }

    private static List<String> parseTitles(String[] moviesArray) {
        return parseAttribute(moviesArray, 3);
    }

    private static List<String> parseUrlImages(String[] moviesArray) {
        return parseAttribute(moviesArray, 5);
    }

    private static List<String> parseAttribute(String[] moviesArray, int pos) {
        return Stream.of(moviesArray)
                .map(e -> e.split("\",\"")[pos])
                .map(e -> e.split(":\"")[1])
                .map(e -> e.replaceAll("\"", ""))
                .collect(Collectors.toList());
    }

    static void getTitleURLImage250Movies(){
            String returnJSON;

            //criar um httpclient
            HttpClient client = HttpClient.newHttpClient();

            //criar um httprequest
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"))
                    .GET() //especifica que é um request do tipo GET
                    .build();

            //enviar o request e obter a resposta
            returnJSON = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();

            JSONArray jsonMovies = new JSONObject(returnJSON).getJSONArray("items");

            List<Movie> movies = new ArrayList<>();

            for(Object obj : jsonMovies){
                JSONObject movieJson = (JSONObject) obj;
                Movie movie = new Movie(
                        movieJson.getString("title"),
                        movieJson.getString("image"),
                        movieJson.getDouble("imDbRating"),
                        movieJson.getInt("year"));

                movies.add(movie);
            }

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}

