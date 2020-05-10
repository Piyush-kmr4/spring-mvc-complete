package mvc.project.reciepeproject.converters;

import lombok.Synchronized;
import mvc.project.reciepeproject.commands.CategoryCommand;
import mvc.project.reciepeproject.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CatgeoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if(source == null)
            return null;

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());
        return categoryCommand;
    }
}
