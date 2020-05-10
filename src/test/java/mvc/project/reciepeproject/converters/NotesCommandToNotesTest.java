package mvc.project.reciepeproject.converters;

import mvc.project.reciepeproject.commands.NotesCommand;
import mvc.project.reciepeproject.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {
    private static final Long ID_VALUE = 1L;
    private static final String RECIPE_NOTES = "recipe";
    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}