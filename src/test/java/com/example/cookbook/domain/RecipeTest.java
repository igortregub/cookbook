package com.example.cookbook.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeTest {

    @Test
    public void equalsVerifier() {
        Instant instant = Instant.now();

        Recipe parent = new Recipe();
        parent.setId(11L);

        Recipe recipe1 = new Recipe();
        recipe1.setId(22L);
        recipe1.setRecipeParent(parent);
        recipe1.setName("test");
        recipe1.setDescription("test description...");
        recipe1.setCreatedDate(instant);

        Recipe recipe2 = new Recipe();
        recipe2.setId(recipe1.getId());
        recipe2.setRecipeParent(parent);
        recipe2.setName(recipe1.getName());
        recipe2.setDescription(recipe1.getDescription());
        recipe2.setCreatedDate(instant);

        assertThat(recipe1).isEqualTo(recipe2);
        recipe2.setId(2L);

        assertThat(recipe1).isNotEqualTo(recipe2);
        recipe1.setId(null);
        assertThat(recipe1).isNotEqualTo(recipe2);
    }
}
