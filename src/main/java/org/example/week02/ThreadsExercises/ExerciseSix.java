package main.java.org.example.week02.ThreadsExercises;
import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class ExerciseSix {

    @Getter
    static
    class DTO {
        private String data;

        public DTO(String data) {
            this.data = data;
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

            String[] urls = new String[]{
                    "https://api.chucknorris.io/jokes/random",
                    "https://api.kanye.rest",
                    "https://api.whatdoestrumpthink.com/api/v1/quotes/random",
                    "https://pokeapi.co/api/v2/pokemon/ditto",
                    "https://api.agify.io?name=Morten",
                    "https://dog.ceo/api/breeds/image/random",
                    "https://api.adviceslip.com/advice",
                    "https://catfact.ninja/fact",
                    "https://www.boredapi.com/api/activity"
            };

            DTO megaDTO = new DTO("");
            for (String url : urls) {
                String response = getResponse(url);
                String serviceName = url.split("/")[2];
                megaDTO = populateDTO(megaDTO, serviceName, response);
            }
            System.out.println(megaDTO.getData());

    }

    public static DTO populateDTO(DTO megaDTO, String serviceName, String serviceData) {
            megaDTO = new DTO(megaDTO.getData() + "\nService: " + serviceName + "\n" + serviceData + "\n");
            return megaDTO;
    }

    private static String getResponse(String url) {
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .header("Accept", "application/json")
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


}