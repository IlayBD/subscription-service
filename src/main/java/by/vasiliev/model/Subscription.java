package by.vasiliev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="client_id", nullable = false)
    private Long clientId;

    @Column(name="product_id", nullable = false)
    private Long productId;


    @Enumerated(EnumType.STRING)
    @Column(name="event_type", nullable = false)
    private EventType eventType;


    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


    public enum EventType {
        PRICE_CHANGE,
        STOCK_UPDATE,
        PROMOTION,
        NEW_VERSION,
        DISCOUNT_AVAILABLE
    }


}
