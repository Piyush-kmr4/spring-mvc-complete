package mvc.project.reciepeproject.controllers;

import mvc.project.reciepeproject.commands.RecipeCommand;
import mvc.project.reciepeproject.domain.Recipe;
import mvc.project.reciepeproject.service.ImageService;
import mvc.project.reciepeproject.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController controller;
    MockMvc mockMvc;


    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        controller=new ImageController(imageService,recipeService);
        mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void showUploadForm() throws Exception {
        //given
        RecipeCommand recipeCommand=new RecipeCommand();
        recipeCommand.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void handleImagePost() throws Exception{
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile","testing.txt", "text/plain",
                        "Spring FrameworkGuru".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/recipe/1/show"));

        verify(imageService, times(1)).saveImage(anyLong(), any());
    }

    @Test
    void renderImageFromDB() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String s = "fake image test";
        int i =0;
        Byte[] byteArray = new Byte[s.length()];

        for(Byte primbyte: byteArray){
            byteArray[i++] = primbyte;
        }

        command.setImage(byteArray);


        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes =response.getContentAsByteArray();

        assertEquals(s.getBytes().length, responseBytes.length);

    }
}