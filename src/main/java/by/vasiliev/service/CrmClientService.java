package by.vasiliev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class CrmClientService {

    private final WebClient webClient;
    @Value("${crm.base-url}")
    private String crmBaseUrl;
    @Value("${crm.stub-enabled:false}") // ← для переключения заглушки
    private boolean stubEnabled;

    public boolean isClientExists(Long clientId) {
        if (stubEnabled) return true; // ✅ ЗАГЛУШКА: всегда true
        try {
            ResponseEntity<Boolean> response = webClient.get()
                    .uri(crmBaseUrl + "/clients/{id}/exists", clientId)
                    .retrieve()
                    .toEntity(Boolean.class)
                    .block();
            return response != null && response.getBody() != null && response.getBody();
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }

    public boolean isProductExists(Long productId) {
        if (stubEnabled) return true; // ✅ ЗАГЛУШКА
        try {
            ResponseEntity<Boolean> response = webClient.get()
                    .uri(crmBaseUrl + "/products/{id}/exists", productId)
                    .retrieve()
                    .toEntity(Boolean.class)
                    .block();
            return response != null && response.getBody() != null && response.getBody();
        } catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }
}
