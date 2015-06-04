package com.ponup.api;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScoreService {

    public String createApiUrl() {
        String cliProperty = System.getProperty("ponup.api.url");
        return (cliProperty != null ? cliProperty : Constants.API_URL);
    }

    public void add(Score score) {
        String apiUrl = createApiUrl();
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormEncodingBuilder()
                .add("game_name", score.getGameName())
                .add("game_level_number", score.getGameLevelNumber() + "")
                .add("player_name", score.getPlayerName())
                .add("value", "" + score.getValue())
                .build();
        Request request = new Request.Builder()
                .url(apiUrl + "/score/add")
                .post(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException ex) {
            Logger.getLogger(ScoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Score> list(String gameName) {
        String apiUrl = createApiUrl();
        List<Score> scores = new LinkedList<>();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl + "/score/list?game_name=" + gameName)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String body = response.body().string();

            JSONParser parser = new JSONParser();
            JSONArray jsonScores = (JSONArray) parser.parse(body);
            for (Object jsonScoreRaw : jsonScores) {
                JSONObject jsonScore = (JSONObject) jsonScoreRaw;
                Score score = new Score();
                score.setPlayerName((String) jsonScore.get("player_name"));
                score.setValue(((Long)jsonScore.get("value")).intValue());
                score.setUpdateTime(new Date());
                scores.add(score);
            }
        } catch (IOException e) {

        } catch (ParseException ex) {
            Logger.getLogger(ScoreService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scores;
    }
}
