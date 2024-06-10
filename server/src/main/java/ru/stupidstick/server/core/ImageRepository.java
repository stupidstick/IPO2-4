package ru.stupidstick.server.core;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stupidstick.server.core.entity.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByUsername(String username);

    void deleteAllByUsername(String username);

}
