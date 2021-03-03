package com.example.cookbook.service.mapper;

import com.example.cookbook.domain.Recipe;
import com.example.cookbook.service.dto.RecipeDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecipeMapper {

    @Mapping(source = "recipeParent.id", target = "parent")
    @Mapping(source = "recipeParent.name", target = "parentName")
    RecipeDTO toDto(Recipe recipe);

    @Mapping(source = "parent", target = "recipeParent")
    @Mapping(target = "createdDate", ignore = true)
    Recipe toEntity(RecipeDTO recipeDTO);

    @Mapping(target = "recipeParent", ignore = true)
    Recipe merge(RecipeDTO recipeDTO, @MappingTarget Recipe recipe);

    default Recipe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(id);
        return recipe;
    }
}
