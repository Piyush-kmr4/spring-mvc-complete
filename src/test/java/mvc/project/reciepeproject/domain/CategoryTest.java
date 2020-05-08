package mvc.project.reciepeproject.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;

    @BeforeEach
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() {
        Long idValue=4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
        String desc="Demo description";
        category.setDescription(desc);
        assertEquals(desc,category.getDescription());
    }

    @Test
    public void getRecipes() {
    }
}