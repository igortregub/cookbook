package com.example.cookbook.web.api;

import com.example.cookbook.service.RecipeService;
import com.example.cookbook.service.dto.RecipeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RecipesController {

    private final RecipeService recipeService;

    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipes")
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO) throws URISyntaxException {

        if (recipeDTO.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A new recipe cannot already have an ID");
        }

        RecipeDTO recipe = recipeService.create(recipeDTO);
        return ResponseEntity.created(new URI("/api/recipes/" + recipe.getId())).body(recipe);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long id) {

        Optional<RecipeDTO> recipeDTO = recipeService.findOne(id);

        return ResponseEntity.ok(recipeDTO.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/recipes/{id}/child")
    public ResponseEntity<Page<RecipeDTO>> getRecipeChildList(@PathVariable Long id, Pageable pageable) {

        return ResponseEntity.ok().body(recipeService.findChildren(id, pageable));
    }

    @GetMapping("/recipes")
    public ResponseEntity<Page<RecipeDTO>> getAllRecipes(Pageable pageable) {

        return ResponseEntity.ok().body(recipeService.findAll(pageable));
    }

    @PutMapping("/recipes")
    public ResponseEntity<RecipeDTO> updateRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {

        if (recipeDTO.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid recipe id!");
        }

        return ResponseEntity.ok().body(recipeService.save(recipeDTO));
    }
}
