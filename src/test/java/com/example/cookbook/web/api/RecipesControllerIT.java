package com.example.cookbook.web.api;

import com.example.cookbook.CookbookApplication;
import com.example.cookbook.config.TestConfig;
import com.example.cookbook.domain.Recipe;
import com.example.cookbook.repository.RecipeRepository;
import com.example.cookbook.service.dto.RecipeDTO;
import com.example.cookbook.service.mapper.RecipeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {CookbookApplication.class, TestConfig.class})
@AutoConfigureMockMvc
public class RecipesControllerIT {

    private final Recipe recipe = new Recipe() {{
        Recipe parent = new Recipe();
        parent.setId(1L);

        setName("Test name");
        setDescription("Test description");
        setRecipeParent(parent);
    }};

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void createRecipe() throws Exception {
        int size = recipeRepository.findAll().size();

        RecipeDTO recipeDTO = recipeMapper.toDto(recipe);
        recipeDTO.setId(null);

        mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.objectToJsonBytes(recipeDTO)))
                .andExpect(status().isCreated());

        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(size + 1);
        Recipe testRecipe = recipeList.get(recipeList.size() - 1);
        assertThat(testRecipe.getName()).isEqualTo(recipe.getName());
        assertThat(testRecipe.getDescription()).isEqualTo(recipe.getDescription());
        assertThat(testRecipe.getRecipeParent().getId()).isEqualTo(recipe.getRecipeParent().getId());
    }

    @Test
    public void createRecipeWithExistingId() throws Exception {
        int size = recipeRepository.findAll().size();

        RecipeDTO recipeDTO = recipeMapper.toDto(recipe);
        recipeDTO.setId(1L);

        mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.objectToJsonBytes(recipeDTO)))
                .andExpect(status().is4xxClientError());

        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(size);
    }

    @Test
    public void createRecipeValidationFail() throws Exception {
        int size = recipeRepository.findAll().size();

        RecipeDTO recipeDTO = recipeMapper.toDto(recipe);
        recipeDTO.setId(null);
        recipeDTO.setName(null);

        mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.objectToJsonBytes(recipeDTO)))
                .andExpect(status().is4xxClientError());

        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(size);
    }

    @Test
    public void getRecipe() throws Exception {
        Recipe recipe1 = recipeRepository.findById(3L).orElseThrow();

        mockMvc.perform(get("/api/recipes/{id}", recipe1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(recipe1.getId().intValue()))
                .andExpect(jsonPath("$.name").value(recipe1.getName()))
                .andExpect(jsonPath("$.description").value(recipe1.getDescription()))
                .andExpect(jsonPath("$.createdDate").value(recipe1.getCreatedDate().toString()));
    }

    @Test
    public void getRecipeChildList() throws Exception {

        mockMvc.perform(get("/api/recipes/2/child"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.numberOfElements").value(2))
                .andExpect(jsonPath("$.content.*.id").value(hasItems(6, 7)))
                .andExpect(jsonPath("$.content.*.name").value(hasItems("Wedge salad", "Dinner salad")));
    }

    @Test
    public void getNonExistingRecipe() throws Exception {

        mockMvc.perform(get("/api/recipes/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    public void getAllRecipes() throws Exception {

        // see db/changelog/cookbook_public_recipe.csv for test data
        mockMvc.perform(get("/api/recipes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.content.[*].id").value(hasItem(10)))
                .andExpect(jsonPath("$.content.[*].name").value(hasItem("Pudding")))
                .andExpect(jsonPath("$.content.[*].description").value(hasItem("Pudding is a pudding.")));
    }


    @Test
    @Transactional
    public void updateRecipe() throws Exception {

        Recipe recipeEntity = new Recipe();
        recipeEntity.setName("name");
        recipeEntity.setDescription("name");
        recipeRepository.saveAndFlush(recipeEntity);

        RecipeDTO recipeDTO = recipeMapper.toDto(recipeEntity);
        recipeDTO.setName("new name");
        recipeDTO.setDescription("name description");

        int size = recipeRepository.findAll().size();

        mockMvc.perform(put("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.objectToJsonBytes(recipeDTO)))
                .andExpect(status().isOk());

        assertThat(recipeRepository.findAll()).hasSize(size);
        Recipe testRecipe = recipeRepository.findById(recipeEntity.getId()).orElseThrow();

        assertThat(testRecipe.getName()).isEqualTo(recipeDTO.getName());
        assertThat(testRecipe.getDescription()).isEqualTo(recipeDTO.getDescription());
    }

    @Test
    public void updateNonExistingRecipe() throws Exception {
        int size = recipeRepository.findAll().size();

        RecipeDTO recipeDTO = recipeMapper.toDto(recipe);

        mockMvc.perform(put("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.objectToJsonBytes(recipeDTO)))
                .andExpect(status().isBadRequest());

        List<Recipe> recipeList = recipeRepository.findAll();
        assertThat(recipeList).hasSize(size);
    }
}
