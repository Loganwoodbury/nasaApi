package org.example.service;

import org.example.model.NeoFeedResponse;
import org.springframework.web.client.RestTemplate;

public class NASAService {
    //send requests to nasa api and get back responses
    RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://api.nasa.gov/neo/rest/v1/feed?";

    private final String api_key = System.getenv("NASA_KEY");

    public NeoFeedResponse getAllNeos(String startDate, String endDate){
        String url = API_URL + "start_date=" + startDate +
                "&end_date=" + endDate +
                "&api_key=" + api_key;

//        String response = restTemplate.getForObject(url, String.class);//.toString();
//        System.out.println(response);

        NeoFeedResponse response = restTemplate.getForObject(url, NeoFeedResponse.class);
        return response;

    }
}
