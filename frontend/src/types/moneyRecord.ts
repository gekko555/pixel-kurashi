export interface MoneyRecordResponse {
    id: number;
    recordDate: string;
    amount: number;
    categoryId: number;
    categoryName: string;
    memo: string;
    createdAt: string;
    updatedAt: string;
}

export interface MoneyRecordCreateRequest{
    recordDate: string;
    amount: number;
    categoryId: number;
    memo: string;
}

export interface MoneyRecordUpdateRequest{
    id: number;
    recordDate: string;
    amount: number;
    categoryId: number;
    memo: string;
}

export interface CategoryMasterResponse{
    id: number;
    name: string;
    createdAt: string;
    updatedAt: string;
}
