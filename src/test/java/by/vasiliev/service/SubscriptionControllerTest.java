package by.vasiliev.service;


import by.vasiliev.controller.SubscriptionController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubscriptionController.class)
class SubscriptionControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SubscriptionService service;
//
//    @Test
//    void getById_shouldReturnSubscription() throws Exception {
//        Subscription sub = new Subscription(1L, 100L, 200L, EventType.NEW_VERSION, LocalDateTime.now());
//
//        when(service.getById(1L)).thenReturn(sub);
//
//        mockMvc.perform(get("/api/subscriptions/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.clientId").value(100))
//                .andExpect(jsonPath("$.productId").value(200));
//    }
}