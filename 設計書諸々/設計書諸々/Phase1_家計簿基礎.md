# 01_目的完了条件
| 項目 | 内容 |
| --- | --- |
| Phase | Phase1 |
| テーマ | 家計簿基礎 |
| 目的 | 家計簿のINSERT/SELECT/UPDATE/DELETEを完成 |
| 完了条件 | 3件登録/一覧表示/再読込保持/更新機能/削除機能/月別日別取得/カテゴリマスタ化/月合計機能 |
| 次回改善 | バリデーション強化, レスポンス形式統一 |


# 02_ユーザーフロー
| No | ステップ | 入力/条件 | 処理 | 出力/確認 |
| --- | --- | --- | --- | --- |
| 1 | 入力 | 金額/カテゴリ/日付 | フロント検証 | 登録可否判定 |
| 2 | 登録 | POST /api/records | DB保存 | 成功メッセージ |
| 3 | 一覧 | GET /api/records | データ取得 | 履歴表示 |


# 03_画面項目定義
| 画面ID | 画面名 | 項目名 | 型 | 必須 | 説明 |
| --- | --- | --- | --- | --- | --- |
| SCR-002 | 家計簿入力 | amount | number | 必須 | 金額 |
| SCR-002 | 家計簿入力 | category | select | 必須 | カテゴリ |
| SCR-002 | 家計簿入力 | recordDate | date | 必須 | 記録日 |
| SCR-003 | 履歴一覧 | recordList | table | - | 履歴一覧 |


# 04_API一覧
| API名 | Method | URL | 概要 | リクエスト例 | レスポンス例 |
| --- | --- | --- | --- | --- | --- |
| 一覧取得 | GET | /api/money-records | 家計簿一覧 | - | [{id:1,amount:1200,...}] |
| 月別取得 | GET | /api/money-records/month/{year}/{month} | 月別家計簿 | /month/2026/6 | [{id:1,amount:1200,...}] |
| 日別取得 | GET | /api/money-records/day/{date} | 日別家計簿 | /day/2026-06-01 | [{id:1,amount:1200,...}] |
| 登録 | POST | /api/money-records | 家計簿登録 | {amount:1200,...} | - |
| 更新 | PUT | /api/money-records/{id} | 家計簿更新 | {id:1,amount:2000,...} | - |
| 削除 | DELETE | /api/money-records/{id} | 家計簿削除 | - | - |
| カテゴリ一覧 | GET | /api/categories | カテゴリマスタ | - | [{id:1,name:食費},...] |


# 05_DB設計
| テーブル | カラム | 型 | 必須 | 説明 | 初心者メモ |
| --- | --- | --- | --- | --- | --- |
| money_record | id | BIGINT | 必須 | 主キー | 自動採番 |
| money_record | amount | INT | 必須 | 金額 | まずは整数でOK |
| money_record | category_id | BIGINT | 必須 | カテゴリID | 外部キー |
| money_record | record_date | DATE | 必須 | 記録日 | 登録日と分ける |
| money_record | memo | VARCHAR(255) | 任意 | メモ | 空でも可 |
| money_record | created_at | TIMESTAMP | 必須 | 作成日時 | 自動設定 |
| money_record | updated_at | TIMESTAMP | 必須 | 更新日時 | 自動更新 |
| category_master | id | BIGINT | 必須 | 主キー | 自動採番 |
| category_master | name | VARCHAR(255) | 必須 | カテゴリ名 | 食費、交通費など |
| category_master | created_at | TIMESTAMP | 必須 | 作成日時 | 自動設定 |
| category_master | updated_at | TIMESTAMP | 必須 | 更新日時 | 自動更新 |
| sub_category_master | id | BIGINT | 必須 | 主キー | 自動採番 |
| sub_category_master | category_id | BIGINT | 必須 | 親カテゴリID | 外部キー |
| sub_category_master | name | VARCHAR(255) | 必須 | サブカテゴリ名 | 外食、スーパーなど |
| sub_category_master | created_at | TIMESTAMP | 必須 | 作成日時 | 自動設定 |
| sub_category_master | updated_at | TIMESTAMP | 必須 | 更新日時 | 自動更新 |


# 06_テスト観点
| No | テスト観点 | 入力例 | 期待結果 |
| --- | --- | --- | --- |
| 1 | 必須エラー | amount空 | 登録不可 |
| 2 | 登録 | 1200/食費 | 一覧に反映 |
| 3 | 永続化 | 再読込 | データ保持 |
| 4 | 更新 | 金額変更 | 一覧に反映 |
| 5 | 削除 | ID指定 | 一覧から削除 |
| 6 | 月別取得 | /month/2026/6 | 6月の記録のみ |
| 7 | 日別取得 | /day/2026-06-01 | 6月1日の記録のみ |
| 8 | 月合計 | /month/2026/6 | 合計金額表示 |
| 9 | カテゴリ選択 | select選択 | マスタから選択 |


# 07_タスク分解
| 順序 | タスク | 担当 | 完了条件 | 次回改善 |
| --- | --- | --- | --- | --- |
| 1 | DBテーブル作成 | あなた | DDL完了 | index検討 |
| 2 | POST API作成 | あなた | 登録成功 | DTO分離 |
| 3 | GET API作成 | あなた | 取得成功 | 絞込追加 |
| 4 | Reactフォーム+一覧 | あなた | 画面で確認 | UI改善 |
| 5 | 更新機能 | あなた | PUT API + フロント | - |
| 6 | 削除機能 | あなた | DELETE API + フロント | - |
| 7 | 月別・日別取得 | あなた | API + フロント | - |
| 8 | カテゴリマスタ化 | あなた | テーブル + API + フロントselect | - |
| 8-1 | category_master/sub_category_master DDL作成 | あなた | DDL完了 | データ初期化 |
| 8-2 | Entity/Repository/Service/Controller作成 | あなた | API動作確認 | DTO分離 |
| 8-3 | money_recordのcategoryをcategory_idに変更 | あなた | ALTER実行 | 既存データ移行 |
| 8-4 | DTO/Entity/Service/テストの型変更 | あなた | String→Long | テスト修正 |
| 8-5 | フロントのTypeScript型変更 | あなた | number入力→select | カテゴリ取得実装 |
| 9 | 月合計機能 | あなた | 集計 + 表示 | - |
| 10 | テスト追加 | あなた | Controller + Service | - |


# 08_UIイメージ
| 項目 | 内容 |
| --- | --- |
| Phase | Phase1 |
| 画像ファイル | C:\Users\hide\Desktop\ChatGPT Image 2026年5月30日 13_56_37.png |
| 使い方 | このPhaseで関係するUI要素を画像から抽出して設計に反映 |


