package mvc.project.reciepeproject.controllers;

import mvc.project.reciepeproject.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"","/","index"})
    public String getIndexPage(Model model){
        System.out.println("Checking Live Reload.....");
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}
