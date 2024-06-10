package ru.stupidstick.server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stupidstick.server.core.ImageService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<String> findImages(@RequestHeader(AUTHORIZATION) String username) {
        return ResponseEntity.ok(imageService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<Void> saveImage(@RequestBody String image, @RequestHeader(AUTHORIZATION) String username) {
        imageService.save(image, username);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearImages(@RequestHeader(AUTHORIZATION) String username) {
        imageService.clearImages(username);
        return ResponseEntity.ok().build();
    }

}
