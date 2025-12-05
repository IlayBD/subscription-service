package by.vasiliev.service;

import by.vasiliev.exception.ResourceNotFoundException;
import by.vasiliev.model.Subscription;
import by.vasiliev.repository.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository repository;

    @Mock
    private CrmClientService crm;

    @InjectMocks
    private SubscriptionService service;

    @Test
    void getById_shouldReturnSubscription_whenExists() {
        Subscription sub = new Subscription(1L, 10L, 20L, Subscription.EventType.PROMOTION, LocalDateTime.now());
        when(repository.findById(1L)).thenReturn(Optional.of(sub));

        Subscription result = service.getById(1L);

        assertEquals(1L, result.getId());
        verify(repository).findById(1L);
    }

    @Test
    void getById_shouldThrow_whenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(99L));
    }
}