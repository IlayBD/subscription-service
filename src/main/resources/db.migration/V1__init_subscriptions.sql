CREATE TABLE subscriptions (
                               id BIGSERIAL PRIMARY KEY,
                               client_id BIGINT NOT NULL,
                               product_id BIGINT NOT NULL,
                               event_type VARCHAR(50) NOT NULL CHECK (event_type IN ('PRICE_CHANGE', 'STOCK_UPDATE', 'PROMO_START')),
                               created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_client_id ON subscriptions(client_id);
CREATE INDEX idx_product_id ON subscriptions(product_id);