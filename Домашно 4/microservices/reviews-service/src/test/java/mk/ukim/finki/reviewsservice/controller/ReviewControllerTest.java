package mk.ukim.finki.reviewsservice.controller;

import mk.ukim.finki.reviewsservice.model.Review;
import mk.ukim.finki.reviewsservice.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@SpringBootTest
@ActiveProfiles("test")
class ReviewControllerTest {

    @Mock
    private ReviewServiceImpl reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private MockMvc mockMvc;

    @Test
    void getReviewsByWineryId() throws Exception {
        Mockito.when(reviewService.findAllByWinery_Id(1L)).thenReturn(Collections.emptyList());
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/all/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getBestNReviewsByWineryId() throws Exception {
        Mockito.when(reviewService.getNBestByWineryId(1L, 5)).thenReturn(Collections.emptyList());
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/best/1/5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getWineryAverageScore() throws Exception {
        Mockito.when(reviewService.getWineryAverageScoreById(1L)).thenReturn(4.5);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/score/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("4.5"));
    }


    @Test
    void getReview() throws Exception {
        Mockito.when(reviewService.findById(1L)).thenReturn(new Review());
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/review/get/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}