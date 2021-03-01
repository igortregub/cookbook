package com.example.cookbook.service.mapper;

import com.example.cookbook.domain.Recipe;
import com.example.cookbook.service.dto.RecipeDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecipeMapper {

    @Mapping(source = "recipeParent.id", target = "parent")
    RecipeDTO toDto(Recipe recipe);

    @Mapping(source = "parent", target = "recipeParent.id")
    @Mapping(target = "createdDate", ignore = true)
    Recipe toEntity(RecipeDTO recipeDTO);

    @Mapping(target = "recipeParent", ignore = true)
    Recipe merge(RecipeDTO recipeDTO, @MappingTarget Recipe recipe);
}
