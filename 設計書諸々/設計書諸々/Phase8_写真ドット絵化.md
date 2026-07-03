# 01_目的完了条件
| 項目 | 内容 |
| --- | --- |
| Phase | Phase8 |
| テーマ | 写真ドット絵化 |
| 目的 | 写真アップロード→ドット絵化→適用 |
| 完了条件 | アップロード/プレビュー/適用 |
| 次回改善 | 顔切抜き, 色数UI |


# 02_ユーザーフロー
| No | ステップ | 入力/条件 | 処理 | 出力/確認 |
| --- | --- | --- | --- | --- |
| 1 | 画像選択 | ファイル入力 | アップロード | source保存 |
| 2 | 変換 | 変換ボタン | 縮小+色削減 | プレビュー |
| 3 | 適用 | 適用ボタン | current更新 | ホーム反映 |


# 03_画面項目定義
| 画面ID | 画面名 | 項目名 | 型 | 必須 | 説明 |
| --- | --- | --- | --- | --- | --- |
| SCR-005 | アバター設定 | uploadInput | file | 必須 | 画像選択 |
| SCR-005 | アバター設定 | pixelPreview | image | - | 変換結果 |
| SCR-005 | アバター設定 | applyButton | button | - | 適用 |


# 04_API一覧
| API名 | Method | URL | 概要 | リクエスト例 | レスポンス例 |
| --- | --- | --- | --- | --- | --- |
| アップロード | POST | /api/avatar/upload | 元画像保存 | multipart/form-data | {success:true,data:{sourceId:1}} |
| 変換 | POST | /api/avatar/pixelate | ドット絵変換 | {sourceId:1,pixelSize:8,colorCount:16} | {success:true,data:{previewUrl:'...'}} |
| 現在取得 | GET | /api/avatar/current | 適用中取得 | - | {success:true,data:{avatarUrl:'...'}} |


# 05_DB設計
| テーブル | カラム | 型 | 必須 | 説明 | 初心者メモ |
| --- | --- | --- | --- | --- | --- |
| avatar_source | id | BIGINT | 必須 | 主キー | 画像ID |
| avatar_source | origin_path | VARCHAR(255) | 必須 | 元画像 | 保存先パス |
| avatar_source | pixel_path | VARCHAR(255) | 任意 | 変換画像 | 変換後セット |
| avatar_source | status | VARCHAR(20) | 必須 | 状態 | UPLOADED/PIXELATED/APPLIED |


# 06_テスト観点
| No | テスト観点 | 入力例 | 期待結果 |
| --- | --- | --- | --- |
| 1 | アップロード | jpg | sourceId返却 |
| 2 | 変換 | pixelSize=8 | プレビュー表示 |
| 3 | 適用 | 適用実行 | ホーム反映 |


# 07_タスク分解
| 順序 | タスク | 担当 | 完了条件 | 次回改善 |
| --- | --- | --- | --- | --- |
| 1 | avatar_source作成 | あなた | DDL完了 | 世代管理 |
| 2 | upload API | あなた | 保存成功 | サイズ制限 |
| 3 | pixelate API | あなた | 変換成功 | 非同期化 |
| 4 | 設定画面 | あなた | 適用可能 | 編集UI |


# 08_UIイメージ
| 項目 | 内容 |
| --- | --- |
| Phase | Phase8 |
| 画像ファイル | C:\Users\hide\Desktop\ChatGPT Image 2026年5月30日 13_56_37.png |
| 使い方 | このPhaseで関係するUI要素を画像から抽出して設計に反映 |


