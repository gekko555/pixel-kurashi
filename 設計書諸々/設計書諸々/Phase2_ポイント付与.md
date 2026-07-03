# 01_目的完了条件
| 項目 | 内容 |
| --- | --- |
| Phase | Phase2 |
| テーマ | ポイント付与 |
| 目的 | 行動でポイント加算 |
| 完了条件 | 家計簿+1pt/ログイン+1pt(1日1回)/残高履歴一致 |
| 次回改善 | ボーナス条件, 失効ルール |


# 02_ユーザーフロー
| No | ステップ | 入力/条件 | 処理 | 出力/確認 |
| --- | --- | --- | --- | --- |
| 1 | ログイン | 当日初回 | +1pt | 履歴にLOGIN |
| 2 | 家計簿登録 | POST成功 | +1pt | 履歴にRECORD_ADD |
| 3 | 表示 | ホーム | 残高取得 | 現在pt表示 |


# 03_画面項目定義
| 画面ID | 画面名 | 項目名 | 型 | 必須 | 説明 |
| --- | --- | --- | --- | --- | --- |
| SCR-001 | ホーム | currentPoint | label | - | 現在pt |
| SCR-006 | pt履歴 | historyList | table | - | 増減履歴 |


# 04_API一覧
| API名 | Method | URL | 概要 | リクエスト例 | レスポンス例 |
| --- | --- | --- | --- | --- | --- |
| 残高取得 | GET | /api/points | 現在pt | - | {success:true,data:{totalPoint:10}} |
| 履歴取得 | GET | /api/points/history | pt履歴 | ?limit=50 | {success:true,data:[...]} |


# 05_DB設計
| テーブル | カラム | 型 | 必須 | 説明 | 初心者メモ |
| --- | --- | --- | --- | --- | --- |
| point_balance | id | BIGINT | 必須 | 主キー | 1ユーザー1件 |
| point_balance | total_point | INT | 必須 | 合計pt | 画面表示用 |
| point_balance | last_login_date | DATE | 任意 | 同日重複防止 | 1日1回判定 |
| point_history | event_type | VARCHAR(30) | 必須 | 理由 | LOGIN等 |
| point_history | point | INT | 必須 | 増減pt | 増加は+ |


# 06_テスト観点
| No | テスト観点 | 入力例 | 期待結果 |
| --- | --- | --- | --- |
| 1 | ログイン重複 | 同日2回 | 1回のみ加点 |
| 2 | 登録加点 | 家計簿登録 | +1pt |
| 3 | 一致 | 残高と履歴 | 合計一致 |


# 07_タスク分解
| 順序 | タスク | 担当 | 完了条件 | 次回改善 |
| --- | --- | --- | --- | --- |
| 1 | point系テーブル作成 | あなた | 2テーブル作成 | event_type定義 |
| 2 | 加点Service | あなた | ロジック分離 | Tx強化 |
| 3 | GET API | あなた | 残高/履歴返却 | 月別集計 |


# 08_UIイメージ
| 項目 | 内容 |
| --- | --- |
| Phase | Phase2 |
| 画像ファイル | C:\Users\hide\Desktop\ChatGPT Image 2026年5月30日 13_56_37.png |
| 使い方 | このPhaseで関係するUI要素を画像から抽出して設計に反映 |


