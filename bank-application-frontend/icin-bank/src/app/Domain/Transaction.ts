import { Account } from "./Account";

export interface Transaction {
    transactionId : any,
    fromAccount : Account | undefined,
    toAccount : Account | undefined,
    transactionAmount : String | undefined,
    TransactionType : String | undefined,
    transactionTime : any,
    status : String | undefined,
    createdBy : String | undefined

}