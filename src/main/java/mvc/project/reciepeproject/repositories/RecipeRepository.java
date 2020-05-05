package mvc.project.reciepeproject.repositories;

import mvc.project.reciepeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
