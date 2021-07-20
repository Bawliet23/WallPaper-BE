package com.wallpaper.art.dto;

import com.wallpaper.art.entities.Resolution;
import com.wallpaper.art.utils.Env;
import lombok.Data;
import java.util.List;

@Data
public class WallpaperDTO {
    private Long id;
    private String description;
    private int view;
    private int download;
    private Resolution resolution;
    private String image;
    private Long fileSize;
    private List<CategoryDTO> categories;
    public String getImage() {
        return Env.getUrlImages()+image;
    }
}
