package mvc.project.reciepeproject.service;

import mvc.project.reciepeproject.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Long recipeId, Long idToDelete);

}
