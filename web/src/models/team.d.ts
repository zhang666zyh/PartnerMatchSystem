import { UserType } from "./user"

/**
 * 用户类型
 */
export type TeamType = {
    id: number,
    name: string,
    description: string,
    expireTime?: Date,
    maxNum: number,
    password: string,
    status: number, // todo 定义枚举值类型,更规范
    createTime: Date, 
    updateTime: Date,
    createUser?: UserType
}