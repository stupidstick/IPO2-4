package ru.stupidstick.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


public class RestTemplateClient implements Client {

    private static final String AUTHORIZATION = "Authorization";

    private static final String IMAGE_PATH = "/image";

    private final String host;

    private final RestTemplate restTemplate = new RestTemplate();

    public RestTemplateClient(String host, int port) {
        this.host = host + ":" + port;
    }

    @Override
    public void sendImage(String images, String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, username);
        HttpEntity<String> httpEntity = new HttpEntity<>(images, headers);
        restTemplate.exchange(
                host + IMAGE_PATH,
                HttpMethod.POST,
                httpEntity,
                Void.class
        );
    }

    @Override
    public String getImages(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, username);
        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                host + IMAGE_PATH,
                HttpMethod.GET,
                httpEntity,
                String.class
        ).getBody();
    }

    @Override
    public void clearOnServer(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION, username);
        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
        restTemplate.exchange(
                host + IMAGE_PATH,
                HttpMethod.DELETE,
                httpEntity,
                Void.class
        );
    }

}
