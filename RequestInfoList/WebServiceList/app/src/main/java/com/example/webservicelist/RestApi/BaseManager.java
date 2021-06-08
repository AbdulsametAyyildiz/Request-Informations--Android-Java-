package com.example.webservicelist.RestApi;

public class BaseManager {
    public RestApi getRestApi(){
        RestApiClient restApiClient = new RestApiClient(BaseUrl.url);
        return restApiClient.getRestApi();
    }
}
