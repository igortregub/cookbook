package com.example.cookbook.service.mapper;

import com.example.cookbook.domain.Recipe;
import com.example.cookbook.service.dto.RecipeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RecipeMapperTest {

    @Autowired
    private RecipeMapper recipeMapper;

    @BeforeEach
    public void setUp() {
        recipeMapper = new RecipeMapperImpl();
    }

    @Test
    public void testToDto() {
        Instant instant = Instant.now();

        Recipe parent = new Recipe();
        parent.setId(11L);

        Recipe recipe = new Recipe();
        recipe.setId(22L);
        recipe.setName("test");
        recipe.setDescription("test description...");
        recipe.setCreatedDate(instant);
        recipe.setRecipeParent(parent);

        RecipeDTO dto = recipeMapper.toDto(recipe);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(recipe.getId());
        assertThat(dto.getName()).isEqualTo(recipe.getName());
        assertThat(dto.getDescription()).isEqualTo(recipe.getDescription());
        assertThat(dto.getCreatedDate()).isEqualTo(instant);
        assertThat(dto.getParent()).isEqualTo(recipe.getRecipeParent().getId());
        assertThat(dto.getParentName()).isEqualTo(recipe.getRecipeParent().getName());
    }

    @Test
    public void testToEntity() {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(22L);
        dto.setParent(21L);
        dto.setName("test");
        dto.setDescription("test description...");

        Recipe recipe = recipeMapper.toEntity(dto);

        assertThat(recipe).isNotNull();
        assertThat(recipe.getId()).isEqualTo(dto.getId());
        assertThat(recipe.getName()).isEqualTo(dto.getName());
        assertThat(recipe.getDescription()).isEqualTo(dto.getDescription());
        assertThat(recipe.getRecipeParent().getId()).isEqualTo(dto.getParent());
    }

    @Test
    public void testMerge() {
        Recipe recipe = new Recipe();
        recipe.setId(22L);
        recipe.setName("test");
        recipe.setDescription("test description...");

        RecipeDTO dto = new RecipeDTO();
        dto.setId(22L);
        dto.setParent(214L);
        dto.setName("test2");
        dto.setDescription("test description... additional text");

        Recipe merge = recipeMapper.merge(dto, recipe);

        assertThat(merge).isNotNull();
        assertThat(merge.getId()).isEqualTo(dto.getId());
        assertThat(merge.getName()).isEqualTo(dto.getName());
        assertThat(merge.getDescription()).isEqualTo(dto.getDescription());
    }
}
