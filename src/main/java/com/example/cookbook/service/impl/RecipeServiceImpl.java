package com.example.cookbook.service.impl;

import com.example.cookbook.domain.Recipe;
import com.example.cookbook.repository.RecipeRepository;
import com.example.cookbook.service.RecipeService;
import com.example.cookbook.service.dto.RecipeDTO;
import com.example.cookbook.service.mapper.RecipeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
        this.recipeRepository = recipeRepository;
        this.recipeMapper = recipeMapper;
    }

    @Override
    public RecipeDTO create(RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.toEntity(recipeDTO);
        recipe = recipeRepository.save(recipe);

        return recipeMapper.toDto(recipe);
    }

    @Override
    public RecipeDTO save(RecipeDTO recipeDTO) {
        Recipe recipe = recipeRepository.findById(recipeDTO.getId()).orElseThrow();
        recipe = recipeRepository.save(recipeMapper.merge(recipeDTO, recipe));

        return recipeMapper.toDto(recipe);
    }

    @Override
    public Optional<RecipeDTO> findOne(Long id) {
        return recipeRepository.findById(id).map(recipeMapper::toDto);
    }

    @Override
    public Page<RecipeDTO> findChildren(Long id, Pageable pageable) {
        return recipeRepository.findAllByRecipeParentId(id, pageable).map(recipeMapper::toDto);
    }

    @Override
    public Page<RecipeDTO> findAll(Pageable pageable) {
        return recipeRepository.findAll(pageable).map(recipeMapper::toDto);
    }
}
