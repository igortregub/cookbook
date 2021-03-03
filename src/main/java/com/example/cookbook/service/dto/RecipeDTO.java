package com.example.cookbook.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
public class RecipeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String name;

    private String description;

    private Instant createdDate;

    private Long parent;
    private String parentName;
}
