# 01_目的完了条件
| 項目 | 内容 |
| --- | --- |
| Phase | Phase6 |
| テーマ | 部屋と家具 |
| 目的 | 家具購入と部屋反映 |
| 完了条件 | 一覧/購入/反映 |
| 次回改善 | 配置ドラッグ, テーマ部屋 |


# 02_ユーザーフロー
| No | ステップ | 入力/条件 | 処理 | 出力/確認 |
| --- | --- | --- | --- | --- |
| 1 | 一覧 | 部屋画面 | master取得 | 購入可能表示 |
| 2 | 購入 | ボタン押下 | pt消費+所持追加 | 成功表示 |
| 3 | 反映 | 再描画 | 所持家具取得 | 部屋表示 |


# 03_画面項目定義
| 画面ID | 画面名 | 項目名 | 型 | 必須 | 説明 |
| --- | --- | --- | --- | --- | --- |
| SCR-004 | 部屋 | furnitureList | list | - | 家具一覧 |
| SCR-004 | 部屋 | buyButton | button | - | 購入 |
| SCR-004 | 部屋 | ownedFurniture | canvas | - | 部屋反映 |


# 04_API一覧
| API名 | Method | URL | 概要 | リクエスト例 | レスポンス例 |
| --- | --- | --- | --- | --- | --- |
| 家具一覧 | GET | /api/furnitures | マスタ取得 | - | {success:true,data:[...]} |
| 家具購入 | POST | /api/furnitures/purchase | 購入処理 | {furnitureId:1} | {success:true,data:{remainingPoint:20}} |
| 所持一覧 | GET | /api/furnitures/owned | 所持取得 | - | {success:true,data:[...]} |


# 05_DB設計
| テーブル | カラム | 型 | 必須 | 説明 | 初心者メモ |
| --- | --- | --- | --- | --- | --- |
| furniture_master | id | BIGINT | 必須 | 主キー | 家具定義 |
| furniture_master | name | VARCHAR(50) | 必須 | 家具名 | 表示名 |
| furniture_master | cost_point | INT | 必須 | 必要pt | 購入条件 |
| user_furniture | id | BIGINT | 必須 | 主キー | 購入履歴ID |
| user_furniture | furniture_id | BIGINT | 必須 | 家具ID | master参照 |


# 06_テスト観点
| No | テスト観点 | 入力例 | 期待結果 |
| --- | --- | --- | --- |
| 1 | 購入可 | pt>=cost | 購入成功 |
| 2 | 購入不可 | pt<cost | エラー |
| 3 | 反映 | 購入後リロード | 部屋表示 |


# 07_タスク分解
| 順序 | タスク | 担当 | 完了条件 | 次回改善 |
| --- | --- | --- | --- | --- |
| 1 | 家具テーブル作成 | あなた | DDL完了 | 在庫概念 |
| 2 | 購入Service | あなた | pt消費+登録 | 排他制御 |
| 3 | 部屋画面 | あなた | 表示完了 | 配置機能 |


# 08_UIイメージ
| 項目 | 内容 |
| --- | --- |
| Phase | Phase6 |
| 画像ファイル | C:\Users\hide\Desktop\ChatGPT Image 2026年5月30日 13_56_37.png |
| 使い方 | このPhaseで関係するUI要素を画像から抽出して設計に反映 |


