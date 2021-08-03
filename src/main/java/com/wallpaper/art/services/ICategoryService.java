package com.wallpaper.art.services;

import com.wallpaper.art.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
}
