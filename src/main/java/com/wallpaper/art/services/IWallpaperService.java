package com.wallpaper.art.services;


import com.wallpaper.art.dto.WallpaperDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IWallpaperService {
    Page<WallpaperDTO> getAll(Pageable page);
    WallpaperDTO getWallpaperById(Long id);
    WallpaperDTO getWallpaperByDescription(String description);
    void uploadWallpaper(WallpaperDTO wallpaperDTO, MultipartFile image) throws IOException;
}
