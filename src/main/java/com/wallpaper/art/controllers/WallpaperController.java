package com.wallpaper.art.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallpaper.art.dto.WallpaperDTO;
import com.wallpaper.art.services.IWallpaperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/wallpapers")
@CrossOrigin("*")
public class WallpaperController {
     private final IWallpaperService wallpaperService;

     public WallpaperController(IWallpaperService wallpaperService) {
        this.wallpaperService = wallpaperService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllByPage(Pageable page){
        return ResponseEntity.ok()
                .body(wallpaperService.getAll(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getWallpaperById(@PathVariable("id") Long id) {
        WallpaperDTO wallpaper = wallpaperService.getWallpaperById(id);
        if (wallpaper!=null){
            return ResponseEntity.ok()
                    .body(wallpaper);
        }
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadWallpaper(@RequestParam("image") MultipartFile image, @RequestParam("wallpaper") String  wallpaper) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WallpaperDTO wallpaperDTO = objectMapper.readValue(wallpaper,WallpaperDTO.class);
        wallpaperService.uploadWallpaper(wallpaperDTO,image);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/genres")
    public ResponseEntity<Page<WallpaperDTO>> getBookByGenre(@PageableDefault(size = 21)Pageable page, @RequestParam List<Long> genresId){
        return ResponseEntity.ok()
                .body(wallpaperService.getWallpapersByGenres(page, genresId));
    }
    @GetMapping("/search/{search}")
    public ResponseEntity<Page<WallpaperDTO>> getBookByGenre(@PageableDefault(size = 21)Pageable page, @PathVariable String search){
        return ResponseEntity.ok()
                .body(wallpaperService.getWallpaperByDescription(search,page));
    }


}
