import axios, {AxiosInstance} from "axios";
import {router} from "../router"

const myAxios: AxiosInstance = axios.create({
    baseURL: "http://localhost:8080/api"
})

myAxios.defaults.withCredentials = true; // 请求携带cookie, 配置为true

myAxios.interceptors.request.use(function (config) {
    return config;
}, function (error) {
    return Promise.reject(error);
});


myAxios.interceptors.response.use(function (response) {
    return response.data;
}, function (error) {
    console.log(error)
    if(error.response.data.message == '无权限'){
        router.push("/userLogin");
    }
    
    return Promise.reject(error);
});

export default myAxios