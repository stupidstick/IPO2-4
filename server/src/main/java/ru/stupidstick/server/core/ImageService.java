package ru.stupidstick.server.core;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stupidstick.server.core.entity.Image;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional
    public void save(String serializedImage, String username) {
        Image image = Image.builder()
                .username(username)
                .serializedImage(serializedImage)
                .build();
        imageRepository.save(image);
    }

    public String findByUsername(String username) {
        List<Image> images = imageRepository.findByUsername(username);
        if (images.isEmpty()) {
            return "";
        }
        return Stream.concat(
                Stream.of(String.valueOf(images.size())),
                images.stream().map(Image::getSerializedImage)
        ).collect(Collectors.joining("\n"));
    }

    @Transactional
    public void clearImages(String username) {
        imageRepository.deleteAllByUsername(username);
    }

}
