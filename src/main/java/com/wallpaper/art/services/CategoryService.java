package com.wallpaper.art.services;

import com.wallpaper.art.dto.CategoryDTO;
import com.wallpaper.art.repositories.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    public CategoryService(ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(category -> modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
    }
}
