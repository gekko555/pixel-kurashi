CREATE TABLE money_record(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    record_date DATE NOT NULL COMMENT '記録日',
    amount INT NOT NULL COMMENT '金額(収入:+, 支出:-)',
    category_id BIGINT NOT NULL COMMENT 'カテゴリID',
    memo VARCHAR(255) COMMENT 'メモ',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
    CONSTRAINT fk_money_record_category
    FOREIGN KEY (category_id) REFERENCES category_master(id),
    INDEX idx_money_record_category_id(category_id),
    INDEX idx_money_record_record_date(record_date)
)COMMENT ='家計簿記録';