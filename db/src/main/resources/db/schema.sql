CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100)        NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    created_at    TIMESTAMP DEFAULT NOW()
);

CREATE TABLE user_address
(
    id         SERIAL PRIMARY KEY,
    users      INT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    address    VARCHAR(100) NOT NULL,
    added_at   TIMESTAMP DEFAULT NOW()
);

CREATE TABLE category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT NOW()
);

CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2) NOT NULL,
    category    INT            NOT NULL REFERENCES category (id) ON DELETE CASCADE,
    created_at  TIMESTAMP DEFAULT NOW()
);

CREATE TABLE inventory
(
    id         SERIAL PRIMARY KEY,
    product    INT NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    quantity   INT NOT NULL DEFAULT 0,
    updated_at TIMESTAMP    DEFAULT NOW()
);

CREATE TABLE cart
(
    id         SERIAL PRIMARY KEY,
    users      INT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE cart_item
(
    id         SERIAL PRIMARY KEY,
    cart       INT NOT NULL REFERENCES cart (id) ON DELETE CASCADE,
    product    INT NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    quantity   INT NOT NULL CHECK (quantity > 0),
    added_at   TIMESTAMP DEFAULT NOW()
);

CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    users       INT            NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    status      VARCHAR(50)    NOT NULL DEFAULT 'PENDING' check ( status IN ('PENDING', 'PROCESSED', 'DELIVERED' )),
    total_price NUMERIC(10, 2) NOT NULL,
    delivery_address INT REFERENCES user_address(id) ON DELETE SET NULL,
    payment_reference VARCHAR(50)    NOT NULL,
    created_at  TIMESTAMP               DEFAULT NOW()
);

CREATE TABLE order_item
(
    id         SERIAL PRIMARY KEY,
    orders     INT            NOT NULL REFERENCES orders (id) ON DELETE CASCADE,
    product    INT            NOT NULL REFERENCES product (id) ON DELETE CASCADE,
    quantity   INT            NOT NULL CHECK (quantity > 0),
    price      NUMERIC(10, 2) NOT NULL,
    added_at   TIMESTAMP DEFAULT NOW()
);
