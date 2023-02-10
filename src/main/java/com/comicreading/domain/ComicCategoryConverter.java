package com.comicreading.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ComicCategoryConverter implements AttributeConverter<ComicCategory, String> {
    
    @Override
    public String convertToDatabaseColumn(ComicCategory category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public ComicCategory convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(ComicCategory.values())
          .filter(c -> c.getCode().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}
