import myAxios from "../plugins/myAxios";
import {getCurrentUserState, setCurrentUserState} from "../state/user";

export const getCurrentUser = async () => {
    // const currentUser = getCurrentUserState()
    // if (currentUser) { // 用户存在
    //     return currentUser;
    // } else { // 不存在则从远程获取
        const res = await myAxios.get("/user/current");
        if (res.code === 0) {
            setCurrentUserState(res.data);
            return res.data;
        }
        return null;
    // }

}

