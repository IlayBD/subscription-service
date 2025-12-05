package by.vasiliev.controller;

import by.vasiliev.model.Subscription;
import by.vasiliev.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @PostMapping
    public ResponseEntity<Subscription> create(@Valid @RequestBody Subscription subscription) {
        Subscription saved = service.createSubscription(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> update(
            @PathVariable Long id,
            @Valid @RequestBody Subscription subscription
    ) {
        Subscription updated = service.updateSubscription(id, subscription);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getAll() {
        return ResponseEntity.ok(service.getAllSubscriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getById(Long id) {
        Subscription sub = service.getById(id);
        return ResponseEntity.ok(sub);
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Subscription>> getByClient(@PathVariable Long clientId) {
        List<Subscription> subs = service.getSubscriptionsByClientId(clientId);
        return ResponseEntity.ok(subs);
    }
}