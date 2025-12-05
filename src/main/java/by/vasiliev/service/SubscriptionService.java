package by.vasiliev.service;

import by.vasiliev.exception.CrmValidationException;
import by.vasiliev.exception.ResourceNotFoundException;
import by.vasiliev.model.Subscription;
import by.vasiliev.repository.SubscriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository repository;
    private final CrmClientService crmClientService;
    private final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    @Transactional
    public Subscription createSubscription(Subscription subscription) {
        Long clientId = subscription.getClientId();
        Long productId = subscription.getProductId();  // ✅ ИСПРАВЛЕНО: productId из subscription

        if (!crmClientService.isClientExists(clientId)) {
            logger.warn("Client not found in CRM: {}", clientId);
            throw new CrmValidationException("Client not found: " + clientId);
        }
        if (!crmClientService.isProductExists(productId)) {
            logger.warn("Product not found in CRM: {}", productId);
            throw new CrmValidationException("Product not found: " + productId);
        }

        subscription.setCreatedAt(LocalDateTime.now());  // ✅ + убедись, что import java.time.LocalDateTime есть
        Subscription saved = repository.save(subscription);
        logger.info("Subscription created: ID={}", saved.getId());
        return saved;
    }
    public Subscription updateSubscription(Long id, Subscription updated) {
        Subscription existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found: " + id));

        // ✅ Проверяем CRM при изменении клиента или продукта
        if (!existing.getClientId().equals(updated.getClientId()) ||
                !existing.getProductId().equals(updated.getProductId())) {

            if (!crmClientService.isClientExists(updated.getClientId())) {
                throw new CrmValidationException("Client not found: " + updated.getClientId());
            }
            if (!crmClientService.isProductExists(updated.getProductId())) {
                throw new CrmValidationException("Product not found: " + updated.getProductId());
            }
        }

        existing.setClientId(updated.getClientId());
        existing.setProductId(updated.getProductId());
        existing.setEventType(updated.getEventType());
        return repository.save(existing);
    }

    public void deleteSubscription(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Subscription not found: " + id);
        }
        repository.deleteById(id);
        logger.info("Subscription deleted: ID={}", id);
    }

    public List<Subscription> getAllSubscriptions() {
        return repository.findAll();
    }

    public List<Subscription> getSubscriptionsByClientId(Long clientId) {
        return repository.findByClientId(clientId);
    }

    public Subscription getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Subscription not found: " + id));
    }
}
