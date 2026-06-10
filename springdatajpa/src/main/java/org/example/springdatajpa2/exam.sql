CREATE TABLE customers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255),
                           email VARCHAR(255) UNIQUE,
                           age INT
);


CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        product VARCHAR(255),
                        date DATE,
                        customer_id BIGINT,

                        CONSTRAINT fk_order_customer
                            FOREIGN KEY (customer_id)
                                REFERENCES customers(id)
);

INSERT INTO customers(name, email, age)
VALUES ('김철수', 'kim@test.com', 20);

INSERT INTO customers(name, email, age)
VALUES ('이영희', 'lee@test.com', 25);

INSERT INTO customers(name, email, age)
VALUES ('박민수', 'park@test.com', 30);

INSERT INTO orders(product, date, customer_id)
VALUES ('노트북', '2026-06-01', 1);

INSERT INTO orders(product, date, customer_id)
VALUES ('마우스', '2025-06-02', 1);

INSERT INTO orders(product, date, customer_id)
VALUES ('키보드', '2026-06-03', 1);

INSERT INTO orders(product, date, customer_id)
VALUES ('모니터', '2026-06-05', 2);

INSERT INTO orders(product, date, customer_id)
VALUES ('헤드셋', '2026-06-06', 2);

INSERT INTO orders(product, date, customer_id)
VALUES ('스피커', '2024-06-10', 3);