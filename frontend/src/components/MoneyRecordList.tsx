import { useState, useEffect } from 'react';
import { MoneyRecordResponse } from '../types/moneyRecord';
import { MoneyRecordUpdateRequest } from '../types/moneyRecord';
import { CategoryMasterResponse } from '../types/moneyRecord';

export function MoneyRecordList() {
    const [records, setRecords] = useState<MoneyRecordResponse[]>([]);
    const [editFormData, setEditFormData] = useState<MoneyRecordUpdateRequest | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        fetch('http://localhost:8080/api/money-records')
        .then((response) => response.json())
        .then((data) => {
            setRecords(data);
            setLoading(false);
        })
        .catch((err) => {
            setError('収支記録の取得に失敗しました');
            setLoading(false);
        });
    },[]);

    const [categories, setCategories] = useState<CategoryMasterResponse[]>([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/categories')
        .then(response => response.json())
        .then(data => setCategories(data));
    }, []);

    const updateRecord = (record: MoneyRecordResponse) => {
        setEditFormData({
            id: record.id,
            recordDate: record.recordDate,
            amount: record.amount,
            categoryId: record.categoryId,
            memo: record.memo
        });
    };

    const handleUpdate = () => {
        if (editFormData) {
            fetch(`http://localhost:8080/api/money-records/${editFormData.id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(editFormData)
            })
            .then(() => {
                setEditFormData(null);
                fetch('http://localhost:8080/api/money-records')
                .then((response) => response.json())
                .then((data) => setRecords(data));
            })
            .catch((err) => {
                console.error('更新に失敗しました', err);
            });
        }
    };

    const handleDelete = (id: number) => {
        fetch(`http://localhost:8080/api/money-records/${id}`, {
            method: 'DELETE'
    })
    .then(() => {
        fetch('http://localhost:8080/api/money-records')
        .then((response) => response.json())
        .then((data) => setRecords(data));
    })
    .catch((err) => {
        console.error('削除に失敗しました', err);
    });
};

    if(loading) return <div>読み込み中...</div>;
    if(error) return <div>{error}</div>;
    
    return (
        <div>
            <h2>家計簿記録一覧</h2>
            <ul>
                {records.map((record) => (
                    <li key = {record.id}>
                        {record.recordDate}: {record.amount}円 ({record.categoryName}) - {record.memo}
                        <button onClick={() => updateRecord(record)}>更新</button>
                        <button onClick={() => handleDelete(record.id)}>削除</button>
                    </li>
                ))}
            </ul>
            {editFormData && (
                <div>
                    <h3>編集</h3>
                    <input 
                        type="date" 
                        value={editFormData.recordDate}
                        onChange={(e) => setEditFormData({...editFormData, recordDate: e.target.value})}
                    />
                    <input 
                        type="number" 
                        value={editFormData.amount}
                        onChange={(e) => setEditFormData({...editFormData, amount: Number(e.target.value)})}
                    />
                    <select
    value={editFormData.categoryId}
    onChange={(e) => setEditFormData({ ...editFormData, categoryId: Number(e.target.value) })}
>
    <option value="">カテゴリを選択してください</option>
    {categories.map(category => (
        <option key={category.id} value={category.id}>
            {category.name}
        </option>
    ))}
</select>
                    <input 
                        type="text" 
                        value={editFormData.memo}
                        onChange={(e) => setEditFormData({...editFormData, memo: e.target.value})}
                    />
                    <button onClick={handleUpdate}>保存</button>
                    <button onClick={() => setEditFormData(null)}>キャンセル</button>
                </div>
            )}
        </div>
    );
}
