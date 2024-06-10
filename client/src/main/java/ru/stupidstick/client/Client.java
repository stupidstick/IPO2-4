package ru.stupidstick.client;

public interface Client {

    void sendImage(String images, String username);

    String getImages(String username);

    void clearOnServer(String username);

}