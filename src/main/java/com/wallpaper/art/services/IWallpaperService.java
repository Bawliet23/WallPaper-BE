package com.wallpaper.art.services;


import com.wallpaper.art.dto.WallpaperDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IWallpaperService {
    Page<WallpaperDTO> getAll(Pageable page);
    Page<WallpaperDTO> getBooksByGenres(Pageable page, List<Long> genresId);
    WallpaperDTO getWallpaperById(Long id);
    WallpaperDTO getWallpaperByDescription(String description);
    void uploadWallpaper(WallpaperDTO wallpaperDTO, MultipartFile image) throws IOException;
}
