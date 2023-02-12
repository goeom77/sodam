
// axios 로딩
import axios from "axios";
import store from "@/store/index";


  axios.interceptors.request.use(function (config) {
    store.commit('LOADING_STATUS', true)

    console.log("loading status start")
    const token = localStorage.getItem('user_token');
    config.headers.Authorization = "Bearer " + token;
    return config;
  });

  axios.interceptors.response.use(function (config) {
    store.commit('LOADING_STATUS', false)
    console.log("loading status end")
    return config
  });

export default axios;
