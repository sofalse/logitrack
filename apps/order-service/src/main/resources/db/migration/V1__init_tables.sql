CREATE TABLE cargo
(
    id             UUID             NOT NULL,
    order_id       UUID,
    status         VARCHAR(255)     NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    length         DOUBLE PRECISION NOT NULL,
    width          DOUBLE PRECISION NOT NULL,
    height         DOUBLE PRECISION NOT NULL,
    distance_value DECIMAL,
    distance_unit  VARCHAR(255),
    weight_value   DECIMAL,
    weight_unit    VARCHAR(255),
    CONSTRAINT pk_cargo PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id                     UUID                        NOT NULL,
    expected_delivery_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status                 VARCHAR(255)                NOT NULL,
    expires_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at             TIMESTAMP WITHOUT TIME ZONE,
    updated_at             TIMESTAMP WITHOUT TIME ZONE,
    "from"                 VARCHAR(255),
    "to"                   VARCHAR(255),
    distance_value         DECIMAL,
    distance_unit          VARCHAR(255),
    price                  DECIMAL,
    currency               VARCHAR(255),
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE cargo
    ADD CONSTRAINT fk_cargo_order_id FOREIGN KEY (order_id) REFERENCES orders (id);