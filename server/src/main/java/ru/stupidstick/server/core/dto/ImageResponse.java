package ru.stupidstick.server.core.dto;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
public class ImageResponse {

    private final List<String> images;

}
