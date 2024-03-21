package main.java.org.example.week02.APIExercise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.ToString;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

interface DataStorage<T> {
    String store(T data); // return a unique ID for the stored data or the filename
    T retrieve(String source); // retrieve data from the specified source (like a file or database table or ID)
}



public class APIExercise {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        APIExercise ap = new APIExercise();
        List<MovieDTO> movies = new ArrayList<>();
        movies.add(ap.getMovie("tt0111161"));
        movies.add(ap.getMovie("tt0068646"));
        movies.add(ap.getMovie("tt0468569"));
        movies.add(ap.getMovie("tt0050083"));
        movies.add(ap.getMovie("tt0108052"));
        movies.add(ap.getMovie("tt0167260"));
        movies.add(ap.getMovie("tt0111161"));
        movies.add(ap.getMovie("tt0060196"));
        movies.add(ap.getMovie("tt0137523"));
        movies.add(ap.getMovie("tt0120737"));
        movies.add(ap.getMovie("tt0109830"));
        movies.add(ap.getMovie("tt2724064"));
        movies.add(ap.getMovie("tt0071562"));
        movies.add(ap.getMovie("tt0110912"));

        MovieController mc = new APIExercise().new MovieController();

        List<MovieDTO> moviesAboveRating = mc.getByRatingOrAbove(8.5, movies);

        List<MovieDTO> moviesSortedByReleaseDate = mc.getSortedByReleaseDate(movies);

        moviesSortedByReleaseDate.forEach(movieDTO -> System.out.println(movieDTO.getTitle() + " -------Released:" + dateFormat.format(movieDTO.getRelease_date())));
    }

    private String getResponseBody(String url) {
        // Using OkHttp: Can sometime cause program to hang. Then use Apache HttpClient instead.
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNGYxNDMwNGYzYzRlYzRhMTA4NGI0Yzg5OWVmYTgyOSIsInN1YiI6IjY1YzIyNmY3MDkyOWY2MDBjOWUzY2U5ZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.VKIxtUzR8fIBpeYf6-kMFq5WMKGP_P9pvQc9OITL1HY")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String res = response.body().string();
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MovieDTO getMovie(String movieId) {
        String url = "https://api.themoviedb.org/3/find/{movieId}?external_source=imdb_id"
                .replace("{movieId}", movieId);
        String movieJson = getResponseBody(url);
        return gson.fromJson(movieJson, ResultDTO.class).movie_results[0];
    }



    class MovieController{

        public List<MovieDTO> getByRatingOrAbove(double rating, List<MovieDTO> movies){
           return movies.stream()
                   .filter(movie -> movie.getVote_average() >= rating)
                   .toList();

        }

        public List<MovieDTO> getSortedByReleaseDate(List<MovieDTO> movies){
            return movies.stream()
                    .sorted((m1, m2) -> m2.getRelease_date().compareTo(m1.getRelease_date()))
                    .toList();

        }

    }


    @Getter
    @ToString
    public class MovieDTO {
        public boolean adult;
        public String backdrop_path;
        public String id;
        public String title;
        public String original_language;
        public String original_title;
        public String overview;
        public String poster_path;
        public String media_type;
        public int[] genre_ids;
        public double popularity;
        public Date release_date;
        public boolean video;
        public double vote_average;
        public int vote_count;
    }

    @Getter
    @ToString
    public class ResultDTO {
        public MovieDTO movie_results[];
        public String person_results[];
        public String tv_results[];
        public String tv_episode_results[];
    }

}