import axios from "axios";

const myAxios = axios.create({
    baseURL: "http://localhost:8080/api"
})

myAxios.interceptors.request.use(function (config) {
    return config;
}, function (error) {
    return Promise.reject(error);
});


myAxios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    return Promise.reject(error);
});

export default myAxios