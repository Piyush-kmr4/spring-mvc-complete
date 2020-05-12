package mvc.project.reciepeproject.service;

import mvc.project.reciepeproject.commands.RecipeCommand;
import mvc.project.reciepeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long l);
}
