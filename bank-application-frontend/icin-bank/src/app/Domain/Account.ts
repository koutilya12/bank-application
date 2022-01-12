import { User } from "./User";

export interface Account {
    accountId   : any,
    user        : User | undefined,
    status      : String | undefined,
    accountType : String | undefined,
    balance     : Number | undefined,
    lastUpdated : String | undefined,

}