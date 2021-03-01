package com.example.cookbook.service.dto;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeDTOTest {

    @Test
    public void dtoEqualsVerifier() {
        Instant instant = Instant.now();

        RecipeDTO recipeDTO1 = new RecipeDTO();
        recipeDTO1.setId(22L);
        recipeDTO1.setParent(11L);
        recipeDTO1.setName("test");
        recipeDTO1.setDescription("test description...");
        recipeDTO1.setCreatedDate(instant);

        RecipeDTO recipeDTO2 = new RecipeDTO();
        recipeDTO2.setId(recipeDTO1.getId());
        recipeDTO2.setParent(recipeDTO1.getParent());
        recipeDTO2.setName(recipeDTO1.getName());
        recipeDTO2.setDescription(recipeDTO1.getDescription());
        recipeDTO2.setCreatedDate(instant);

        assertThat(recipeDTO1).isEqualTo(recipeDTO2);
        recipeDTO2.setId(2L);

        assertThat(recipeDTO1).isNotEqualTo(recipeDTO2);
        recipeDTO1.setId(null);
        assertThat(recipeDTO1).isNotEqualTo(recipeDTO2);
    }
}
