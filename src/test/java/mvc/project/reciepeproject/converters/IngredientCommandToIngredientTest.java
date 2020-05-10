package mvc.project.reciepeproject.converters;

import mvc.project.reciepeproject.commands.IngredientCommand;
import mvc.project.reciepeproject.commands.UnitOfMeasureCommand;
import mvc.project.reciepeproject.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "ingredient";
    private static final BigDecimal AMOUNT = new BigDecimal(2);
    private static final Long UOM_ID = 2L;
    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        //Given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    void covertWithNullUOM() {
        //Given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);



        //when
        Ingredient ingredient = converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());

    }
}