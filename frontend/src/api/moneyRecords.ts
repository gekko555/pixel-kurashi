// 家計簿・カテゴリで使う型定義を読み込む
import {
  MoneyRecordResponse,
  MoneyRecordCreateRequest,
  MoneyRecordUpdateRequest,
  CategoryMasterResponse
} from '../types/moneyRecord';

// 家計簿APIの共通URL
// 一覧取得・登録・更新・削除で共通して使う
const BASE_URL = 'http://localhost:8080/api/money-records';

// カテゴリ一覧APIのURL
const CATEGORIES_URL = 'http://localhost:8080/api/categories';

// APIレスポンスが成功かどうかを確認する共通関数
// response.ok が false の場合、400や500などのエラーなので例外を投げる
const assertOk = async (response: Response) => {
  if (!response.ok) {
    throw new Error(`API request failed: ${response.status}`);
  }
};

// 家計簿記録を全件取得する関数
// GET /api/money-records を呼び出し、家計簿一覧を配列で返す
export const fetchMoneyRecords = async (): Promise<MoneyRecordResponse[]> => {
  const response = await fetch(BASE_URL);
  await assertOk(response);
  return response.json();
};

// 家計簿記録を新規登録する関数
// POST /api/money-records に入力データをJSONで送信する
// バックエンドがレスポンスボディを返さない場合は Promise<void> にする
export const createMoneyRecord = async (
  data: MoneyRecordCreateRequest
): Promise<void> => {
  const response = await fetch(BASE_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });
 
  await assertOk(response);
  // レスポンスボディがないので何も返さない
};

// 家計簿記録を更新する関数
// PUT /api/money-records/{id} に更新データをJSONで送信する
// id は更新対象の家計簿ID
export const updateMoneyRecord = async (
  id: number,
  data: MoneyRecordUpdateRequest
): Promise<MoneyRecordResponse> => {
  const response = await fetch(`${BASE_URL}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });

  await assertOk(response);
  return response.json();
};

// 家計簿記録を削除する関数
// DELETE /api/money-records/{id} を呼び出す
// 削除処理は返却データが不要なので Promise<void>
export const deleteMoneyRecord = async (id: number): Promise<void> => {
  const response = await fetch(`${BASE_URL}/${id}`, {
    method: 'DELETE'
  });

  await assertOk(response);
};

// カテゴリ一覧を取得する関数
// GET /api/categories を呼び出し、カテゴリ一覧を配列で返す
export const fetchCategories = async (): Promise<CategoryMasterResponse[]> => {
  const response = await fetch(CATEGORIES_URL);
  await assertOk(response);
  return response.json();
};