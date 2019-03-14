package com.movie.access.web;

import com.movie.access.system.entitys.AverageInfo;
import com.movie.access.system.entitys.FullMovieInfo;
import com.movie.access.system.entitys.MovieList;
import com.movie.access.system.errors.TheMovieDBOperationException;
import com.movie.access.system.managers.GenreAverageManager;
import com.movie.access.system.managers.TheMovieDBOperationManager;
import com.movie.access.system.managers.imp.CustomGenreAverageManager;
import com.movie.access.system.managers.imp.CustomTheMovieDBOperationManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ApiControllerTest
{
    private MockMvc mockMvc;

    @Configuration
    static class MainControllerTestConfiguration
    {
        @Bean
        public ApiController mainController()
        {
            return new ApiController();
        }

        @Bean
        public TheMovieDBOperationManager createTheMovieDBOperationManager()
        {
            return Mockito.mock(CustomTheMovieDBOperationManager.class);
        }

        @Bean
        public GenreAverageManager createGenreAverageManager()
        {
            return Mockito.mock(CustomGenreAverageManager.class);
        }
    }

    @Autowired
    private ApiController mainController;

    @Autowired
    private TheMovieDBOperationManager theMovieDBOperationManager;

    @Autowired
    private GenreAverageManager customGenreAverageManager;

    @Before
    public void init() throws URISyntaxException, IOException, TheMovieDBOperationException
    {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();

        when(theMovieDBOperationManager.getMovieInfo(anyInt())).thenReturn(new FullMovieInfo());
        when(theMovieDBOperationManager.getMovieList(anyMap())).thenReturn(new MovieList());

        when(customGenreAverageManager.getAverage(anyInt())).thenReturn(new AverageInfo());
    }

    @Test
    public void getMovieInfoWithCorrectParam() throws Exception
    {
        mockMvc.perform(get("/movie/info").param("movieId", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieInfoWithOutParam() throws Exception
    {
        mockMvc.perform(get("/movie/info"))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieInfoWithUnCorrectParamType() throws Exception
    {
        mockMvc.perform(get("/movie/info").param("movieId", "movieId"))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieListWithCorrectParam() throws Exception
    {
        mockMvc.perform(get("/movie/list").param("page", "1").param("api_key", "fdsfd"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieListWithOutParam() throws Exception
    {
        mockMvc.perform(get("/movie/list"))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAverageInfoWithCorrectParam() throws Exception
    {
        mockMvc.perform(get("/movie/average").param("genreId", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAverageInfoWithOutParam() throws Exception
    {
        mockMvc.perform(get("/movie/average"))
                .andExpect(status().is4xxClientError())
                .andDo(MockMvcResultHandlers.print());
    }
}
