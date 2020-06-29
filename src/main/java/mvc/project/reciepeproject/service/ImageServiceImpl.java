package mvc.project.reciepeproject.service;

import lombok.extern.slf4j.Slf4j;
import mvc.project.reciepeproject.domain.Recipe;
import mvc.project.reciepeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    @Transactional
    public void saveImage(Long recipeId, MultipartFile file) {
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();

            Byte[] byteobjects = new Byte[file.getBytes().length];

            int i=0;

            for (byte b: file.getBytes()
                 ) {
                byteobjects[i++] = b;
            }

            recipe.setImage(byteobjects);

            recipeRepository.save(recipe);
        }
        catch (Exception e){
            System.out.println("Error while storing image into database.."+ e.toString());

        }
    }
}
