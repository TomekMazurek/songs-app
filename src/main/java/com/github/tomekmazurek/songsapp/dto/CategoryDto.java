package com.github.tomekmazurek.songsapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@Builder
public class CategoryDto {

    int id;
    String category;

    @JsonCreator
    public CategoryDto(@JsonProperty int id, @JsonProperty String category) {
        this.id = id;
        this.category = category;
    }
}
