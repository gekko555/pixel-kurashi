# 01_概要
| 項目 | 内容 |
| --- | --- |
| 目的 | Phase1の家計簿機能で必要なDB仕様を明確化する |
| スコープ | money_recordテーブル + category_masterテーブル |
| 収支表現 | amountの符号で表現（収入=プラス、支出=マイナス） |
| カテゴリ正規化 | category_masterテーブルによるマスタ化・FK化 |


# 02_テーブル一覧
| 論理名 | 物理名 | 用途 | 主な操作 |
| --- | --- | --- | --- |
| 家計簿記録 | money_record | 収支の1件記録 | INSERT / SELECT |
| カテゴリマスタ | category_master | カテゴリの定義 | INSERT / SELECT |
| ユーザー | app_user | ログインユーザー | INSERT / SELECT |


# 03_money_record定義
| カラム名 | 型 | NULL | 説明 |
| --- | --- | --- | --- |
| id | BIGINT | NOT NULL | 主キー(AUTO_INCREMENT) |
| record_date | DATE | NOT NULL | 記録日 |
| amount | INT | NOT NULL | 収入:+ / 支出:- |
| category_id | BIGINT | NOT NULL | カテゴリID（FK） |
| user_id | BIGINT | NOT NULL | ユーザーID（FK: app_user.id） |
| memo | VARCHAR(255) | NULL | 任意メモ |
| created_at | TIMESTAMP | NOT NULL | 作成日時 |
| updated_at | TIMESTAMP | NOT NULL | 更新日時 |

# 04_category_master定義
| カラム名 | 型 | NULL | 説明 |
| --- | --- | --- | --- |
| id | BIGINT | NOT NULL | 主キー(AUTO_INCREMENT) |
| name | VARCHAR(255) | NOT NULL | カテゴリ名 |
| created_at | TIMESTAMP | NOT NULL | 作成日時 |
| updated_at | TIMESTAMP | NOT NULL | 更新日時 |


# 04-2_app_user定義
| カラム名 | 型 | NULL | 説明 |
| --- | --- | --- | --- |
| id | BIGINT | NOT NULL | 主キー(AUTO_INCREMENT) |
| username | VARCHAR(255) | NOT NULL | ログインID（UNIQUE） |
| password | VARCHAR(255) | NOT NULL | BCryptハッシュ |
| created_at | TIMESTAMP | NOT NULL | 作成日時 |
| updated_at | TIMESTAMP | NOT NULL | 更新日時 |


# 05_インデックス設計
| インデックス名 | 対象 | 目的 |
| --- | --- | --- |
| idx_money_record_record_date | record_date | 月次検索高速化 |
| idx_money_record_category_id | category_id | カテゴリ検索高速化 |
| idx_money_record_user_id | user_id | ユーザー別検索高速化 |
| uq_app_user_username | username | ログインID一意制約 |


# 06_DDL
| SQL |
| --- |
| -- money_recordテーブル |
| CREATE TABLE money_record ( |
| id BIGINT AUTO_INCREMENT PRIMARY KEY, |
| record_date DATE NOT NULL COMMENT '記録日', |
| amount INT NOT NULL COMMENT '金額(収入:+, 支出:-)', |
| category_id BIGINT NOT NULL COMMENT 'カテゴリID', |
| memo VARCHAR(255) COMMENT 'メモ', |
| created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日', |
| updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日', |
| INDEX idx_money_record_record_date (record_date), |
| INDEX idx_money_record_category_id (category_id), |
| FOREIGN KEY (category_id) REFERENCES category_master(id) |
| ) COMMENT ='家計簿記録'; |
| |
| -- category_masterテーブル |
| CREATE TABLE category_master ( |
| id BIGINT AUTO_INCREMENT PRIMARY KEY, |
| name VARCHAR(255) NOT NULL COMMENT 'カテゴリ名', |
| created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日', |
| updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日' |
| ) COMMENT ='カテゴリマスタ'; |
| |
| -- app_userテーブル |
| CREATE TABLE app_user ( |
| id BIGINT AUTO_INCREMENT PRIMARY KEY, |
| username VARCHAR(255) NOT NULL UNIQUE COMMENT 'ログインID', |
| password VARCHAR(255) NOT NULL COMMENT 'BCryptハッシュ', |
| created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日', |
| updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日' |
| ) COMMENT ='ユーザー'; |


# 07_拡張メモ
| テーマ | 内容 |
| --- | --- |
| ユーザー分離 | money_recordにuser_id列を追加（認証基盤フェーズで実施） |
| サブカテゴリ | sub_category_masterテーブル追加で対応 |
| 認証 | app_userテーブル + Spring Security + BCrypt（詳細は認証基盤_設計書.md） |


