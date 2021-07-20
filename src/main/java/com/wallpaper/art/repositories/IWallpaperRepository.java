package com.wallpaper.art.repositories;

import com.wallpaper.art.entities.Wallpaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWallpaperRepository extends JpaRepository<Wallpaper,Long> {
    Page<Wallpaper> findAll(Pageable page);
}
