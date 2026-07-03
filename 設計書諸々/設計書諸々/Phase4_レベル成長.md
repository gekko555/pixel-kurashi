# 01_目的完了条件
| 項目 | 内容 |
| --- | --- |
| Phase | Phase4 |
| テーマ | レベル成長 |
| 目的 | ptからレベル計算 |
| 完了条件 | Lv表示/次Lv表示/解放反映 |
| 次回改善 | 演出強化, 複数ルート |


# 02_ユーザーフロー
| No | ステップ | 入力/条件 | 処理 | 出力/確認 |
| --- | --- | --- | --- | --- |
| 1 | 加点 | point増加 | Lv再計算 | state更新 |
| 2 | 表示 | ホーム | Lv取得 | Lvバッジ表示 |
| 3 | 解放 | Lv到達 | 条件判定 | 要素解放 |


# 03_画面項目定義
| 画面ID | 画面名 | 項目名 | 型 | 必須 | 説明 |
| --- | --- | --- | --- | --- | --- |
| SCR-001 | ホーム | levelBadge | label | - | 現在Lv |
| SCR-001 | ホーム | nextLevelPoint | label | - | 次Lvまでpt |


# 04_API一覧
| API名 | Method | URL | 概要 | リクエスト例 | レスポンス例 |
| --- | --- | --- | --- | --- | --- |
| Lv情報 | GET | /api/character/level | Lv/次条件 | - | {success:true,data:{level:2,nextRequired:30}} |
| 再計算 | POST | /api/character/recalc | Lv再計算 | {trigger:'POINT'} | {success:true,data:{level:3}} |


# 05_DB設計
| テーブル | カラム | 型 | 必須 | 説明 | 初心者メモ |
| --- | --- | --- | --- | --- | --- |
| level_rule | level | INT | 必須 | レベル | 主キー |
| level_rule | required_point | INT | 必須 | 必要pt | 昇順 |
| level_rule | unlock_code | VARCHAR(30) | 任意 | 解放要素 | room_tier2等 |


# 06_テスト観点
| No | テスト観点 | 入力例 | 期待結果 |
| --- | --- | --- | --- |
| 1 | 計算 | pt=0/10/30 | 期待Lv |
| 2 | 次Lv | pt=12 | 残pt正しい |


# 07_タスク分解
| 順序 | タスク | 担当 | 完了条件 | 次回改善 |
| --- | --- | --- | --- | --- |
| 1 | level_rule作成 | あなた | マスタ投入 | 管理画面 |
| 2 | 計算ロジック | あなた | Lv算出 | 式調整 |
| 3 | UI表示 | あなた | Lv反映 | 演出追加 |


# 08_UIイメージ
| 項目 | 内容 |
| --- | --- |
| Phase | Phase4 |
| 画像ファイル | C:\Users\hide\Desktop\ChatGPT Image 2026年5月30日 13_56_37.png |
| 使い方 | このPhaseで関係するUI要素を画像から抽出して設計に反映 |


