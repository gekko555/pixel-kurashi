CREATE TABLE sub_category_master (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL COMMENT 'カテゴリID',
    name VARCHAR(255) NOT NULL COMMENT 'サブカテゴリ名',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日時',
    FOREIGN KEY (category_id) REFERENCES category_master(id),
    INDEX idx_sub_category_category_id(category_id)
) COMMENT 'サブカテゴリマスタ';