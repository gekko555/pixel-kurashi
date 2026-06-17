import { useState, useEffect } from "react";
import { MoneyRecordCreateRequest } from '../types/moneyRecord';
import { CategoryMasterResponse } from '../types/moneyRecord';
import { createMoneyRecord, fetchCategories } from '../api/moneyRecords';

export function MoneyRecordForm() {
    const [formData, setFormData] = useState<MoneyRecordCreateRequest>({
        recordDate: '',
        amount: 0,
        categoryId: 0,
        memo: ''
    });

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
         try {
            await createMoneyRecord(formData);
            alert('家計簿の登録が完了しました');
            setFormData({ recordDate: '', amount: 0, categoryId: 0, memo: '' });
            window.location.reload();
        } catch (err) {
            alert('家計簿の登録に失敗しました');
        }
    };

    const [categories, setCategories ] = useState<CategoryMasterResponse[]>([]);

    useEffect(() => {
        fetchCategories().then(data => setCategories(data));
    }, []);

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>日付:</label>
                <input 
                    type="date"
                    value={formData.recordDate}
                    onChange={(e) => setFormData({...formData, recordDate: e.target.value})}
                    />
            </div>
            <div>
                <label>金額:</label>
                <input 
                    type="number"
                    value={formData.amount}
                    onChange={(e) => setFormData({ ...formData, amount: Number(e.target.value) })}
                />
                </div>
                <div>
        <label>カテゴリ:</label>
        <select
          value={formData.categoryId}
          onChange={(e) => setFormData({ ...formData, categoryId: Number(e.target.value) })}
        >
            <option value = "">カテゴリを選択してください</option>
            {categories.map(category => (
                <option key = {category.id} value = {category.id}>
                    {category.name}
                </option>
            ))}
        </select>
      </div>
      <div>
        <label>メモ:</label>
        <input
          type="text"
          value={formData.memo}
          onChange={(e) => setFormData({ ...formData, memo: e.target.value })}
        />
      </div>
      <button type="submit">登録</button>
    </form>
    );
}