import { useState, useEffect } from 'react';
import { MoneyRecordResponse } from '../types/moneyRecord';

export function MoneyRecordList() {
    const [records, setRecords] = useState<MoneyRecordResponse[]>([]);
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
                        {record.recordDate}: {record.amount}円 ({record.category}) - {record.memo}
                        <button onClick={() => handleDelete(record.id)}>削除</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}
