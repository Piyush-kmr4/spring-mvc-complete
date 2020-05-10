package mvc.project.reciepeproject.converters;

import lombok.Synchronized;
import mvc.project.reciepeproject.commands.IngredientCommand;
import mvc.project.reciepeproject.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if(source == null)
            return null;

        final  Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}
