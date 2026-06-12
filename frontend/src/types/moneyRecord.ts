export interface MoneyRecordResponse {
    id: number;
    recordDate: string;
    amount: number;
    category: string;
    memo: string;
    createdAt: string;
    updatedAt: string;
} 
export interface MoneyRecordCreateRequest{
    recordDate: string;
    amount: number;
    category: string;
    memo: string;
}
export interface MoneyRecordUpdateRequest{
    id: number;
    recordDate: string;
    amount: number;
    category: string;
    memo: string;
}