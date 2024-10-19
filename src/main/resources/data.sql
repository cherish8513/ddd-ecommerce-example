CREATE TABLE tb_user (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone_number VARCHAR(20) NOT NULL,
    encrypted_password VARCHAR(100) NOT NULL
);

CREATE TABLE tb_product (
    product_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    chosung VARCHAR(100) NOT NULL,
    price BIGINT NOT NULL,
    description TEXT,
    barcode VARCHAR(255),
    expiration_ymd VARCHAR(10),
    size VARCHAR(10) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_user(user_id)
);