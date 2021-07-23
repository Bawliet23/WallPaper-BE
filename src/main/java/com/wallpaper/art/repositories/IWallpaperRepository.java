package com.wallpaper.art.repositories;

import com.wallpaper.art.entities.Wallpaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWallpaperRepository extends JpaRepository<Wallpaper,Long> {
    Page<Wallpaper> findAll(Pageable page);
    Optional<Wallpaper> findWallpaperByDescriptionContains(String description);
}
