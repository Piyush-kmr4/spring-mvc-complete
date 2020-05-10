package mvc.project.reciepeproject.converters;

import mvc.project.reciepeproject.commands.CategoryCommand;
import mvc.project.reciepeproject.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatgeoryToCategoryCommandTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String DESCRIPTION = "descript";
    CatgeoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CatgeoryToCategoryCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}