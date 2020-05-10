package mvc.project.reciepeproject.converters;

import lombok.Synchronized;
import mvc.project.reciepeproject.commands.IngredientCommand;
import mvc.project.reciepeproject.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomCommandConverter) {
        this.uomCommandConverter = uomCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null)
            return null;

        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setUnitOfMeasure(uomCommandConverter.convert(source.getUnitOfMeasure()));
        return ingredientCommand;
    }
}
