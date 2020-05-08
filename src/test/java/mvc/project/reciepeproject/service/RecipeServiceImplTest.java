package mvc.project.reciepeproject.service;

import mvc.project.reciepeproject.domain.Recipe;
import mvc.project.reciepeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() throws Exception {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeHasSet = new HashSet<Recipe>();
        recipeHasSet.add(recipe);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipeHasSet);

        Set<Recipe> recipeSet = recipeService.getRecipes();
        assertEquals(recipeSet.size(), 1);
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}