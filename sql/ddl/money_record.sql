CREATE TABLE money_record(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    record_date DATE NOT NULL COMMENT '記録日',
    amount INT NOT NULL COMMENT '金額(収入:+, 支出:-)',
    category VARCHAR(255) NOT NULL COMMENT 'カテゴリ',
    memo VARCHAR(255) COMMENT 'メモ',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日',
    INDEX idx_money_record_record_date(record_date)
)COMMENT ='家計簿記録';