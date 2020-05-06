package mvc.project.reciepeproject.controllers;

import mvc.project.reciepeproject.domain.Category;
import mvc.project.reciepeproject.domain.UnitOfMeasure;
import mvc.project.reciepeproject.repositories.CategoryRepository;
import mvc.project.reciepeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","index"})
    public String getIndexPage(){
        System.out.println("Checking Live Reload.....");

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional =unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category id is:--"+categoryOptional.get().getId());
        System.out.println("UnitOfMeasure id is:--"+unitOfMeasureOptional.get().getId());
        return "index";
    }
}
