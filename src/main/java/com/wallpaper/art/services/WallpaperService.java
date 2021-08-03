package com.wallpaper.art.services;
import com.wallpaper.art.dto.CategoryDTO;
import com.wallpaper.art.dto.WallpaperDTO;
import com.wallpaper.art.entities.Category;
import com.wallpaper.art.entities.Wallpaper;
import com.wallpaper.art.repositories.ICategoryRepository;
import com.wallpaper.art.repositories.IWallpaperRepository;
import com.wallpaper.art.utils.FileHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class WallpaperService implements IWallpaperService {

    private final IWallpaperRepository wallpaperRepository;
    private final ICategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public WallpaperService(IWallpaperRepository wallpaperRepository, ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.wallpaperRepository = wallpaperRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<WallpaperDTO> getAll(Pageable page) {
        return wallpaperRepository.findAll(page).map(wallpaper -> modelMapper.map(wallpaper,WallpaperDTO.class));
    }

    @Override
    public Page<WallpaperDTO> getBooksByGenres(Pageable page, List<Long> genresId) {
        List<Category> categories =new ArrayList<Category>();
        for(Long id : genresId){
            Optional<Category> category = categoryRepository.findById(id);
            if(category.isPresent())
                categories.add(category.get());
        }
        return wallpaperRepository.findWallpapersByCategoriesIn(categories,page).map(wallpaper->modelMapper.map(wallpaper,WallpaperDTO.class));
    }

    @Override
    public WallpaperDTO getWallpaperById(Long id) {
        Optional<Wallpaper> byId = wallpaperRepository.findById(id);
        if (byId.isPresent()){
            byId.get().setView(byId.get().getView()+1);
            return modelMapper.map(byId.get(),WallpaperDTO.class);
        }
        return null;
    }

    @Override
    public WallpaperDTO getWallpaperByDescription(String description){
        Optional<Wallpaper> byDescription = wallpaperRepository.findWallpaperByDescriptionContains(description);
        if(byDescription.isPresent()){
            return modelMapper.map(byDescription.get(),WallpaperDTO.class);
        }
        return null;
    }

    @Override
    public void uploadWallpaper(WallpaperDTO wallpaperDTO, MultipartFile image) throws IOException {
        Wallpaper wallpaper = modelMapper.map(wallpaperDTO,Wallpaper.class);
        wallpaper.setImage(FileHandler.uploadFile(image));
        List<Category> categories = new ArrayList<Category>();
        for (CategoryDTO category : wallpaperDTO.getCategories()){
            Optional<Category> byId = categoryRepository.findById(category.getId());
            if (byId.isPresent()) {
                categories.add(byId.get());
            }
        }
        wallpaper.setCategories(categories);
        wallpaperRepository.save(wallpaper);
    }
}
