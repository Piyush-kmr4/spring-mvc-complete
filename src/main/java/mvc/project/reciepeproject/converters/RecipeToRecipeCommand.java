package mvc.project.reciepeproject.converters;

import lombok.Synchronized;
import mvc.project.reciepeproject.commands.RecipeCommand;
import mvc.project.reciepeproject.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingrdientConverter;
    private final CatgeoryToCategoryCommand categoryConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingrdientConverter, CatgeoryToCategoryCommand categoryConverter, NotesToNotesCommand notesConverter) {
        this.ingrdientConverter = ingrdientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
       if(source == null)
           return  null;

       final RecipeCommand recipeCommand = new RecipeCommand();
       recipeCommand.setId(source.getId());
       recipeCommand.setDescription(source.getDescription());
       recipeCommand.setPrepTime(source.getPrepTime());
       recipeCommand.setCookTime(source.getCookTime());
       recipeCommand.setServing(source.getServing());
       recipeCommand.setSource(source.getSource());
       recipeCommand.setUrl(source.getUrl());
       recipeCommand.setDirections(source.getDirections());
       recipeCommand.setDifficulty(source.getDifficulty());
       recipeCommand.setNotes(notesConverter.convert(source.getNotes()));

       if(source.getCategories() != null && source.getCategories().size()>0){
           source.getCategories()
                   .forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
       }

       if(source.getIngredient() != null && source.getIngredient().size()>0){
           source.getIngredient()
                   .forEach(ingredient -> recipeCommand.getIngredients().add(ingrdientConverter.convert(ingredient)));
       }

       return  recipeCommand;
    }
}
