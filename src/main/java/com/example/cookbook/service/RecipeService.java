package com.example.cookbook.service;

import com.example.cookbook.service.dto.RecipeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RecipeService {

    RecipeDTO create(RecipeDTO recipeDTO);

    RecipeDTO save(RecipeDTO recipeDTO);

    Optional<RecipeDTO> findOne(Long id);

    Page<RecipeDTO> findChildren(Long id, Pageable pageable);

    Page<RecipeDTO> findAll(Pageable pageable);
}
