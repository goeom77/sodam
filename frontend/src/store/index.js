
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

const API_URL = 'http://127.0.0.1:8080'
export default new Vuex.Store({
  state: {
    KidBoardarticles: [],
    HistoryViewarticles: [],
    token:null,
    userSignupData:{
      id:null,
      password:null,
      name:null,
      tel:null,
      email:null,
      gender:null,
      enterprise_id:null,
    }
  },
  getters: {
    isLogin(state) {
      return state.token ? true : false
    },

  },
  mutations: {
    GET_KIDBOARDARTICLES(state, KidBoardarticles) {
      state.KidBoardarticles = KidBoardarticles
    },
    GET_HISTORYVIEWARTICLES(state, HistoryViewarticles) {
      state.HistoryViewarticles = HistoryViewarticles
    },
    SAVE_TOKEN(state, token) {
      state.token = token
    },
    SET_USER_DATA(state, payload) {
      state.payload = {
        username: payload.username,
        password: payload.password
      }
    },
    SIGNUPCOUNSELOR(state,payload){
      state.userSignupData = payload
    },
  },
  actions: {
    getKidBoardArticles(context) {
      axios({
        method: 'get',
        url: `${API_URL}/backend/`,
        headers: {
          Authorization: `Token ${context.state.token}`
        }
      })
        .then((res) => {
          // console.log(res, context)
          // console.log(res.data)
          context.commit('GET_KIDBOARDARTICLES', res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    getHistoryViewArticles(context) {
      axios({
        method: 'get',
        url: `${API_URL}/backend/`,
        headers: {
          Authorization: `Token ${context.state.token}`
        }
      })
        .then((res) => {
          // console.log(res, context)
          // console.log(res.data)
          context.commit('GET_HISTORYVIEWARTICLES', res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    login(context,payload){
      axios({
        method:'post',
        url:`${API_URL}/api/auth/login`,
        data:{
          ID: payload.ID,
          password: payload.password,
        }
      })
        .then((res)=>{
          console.log(res)  
          context.commit('SAVE_TOKEN', res.data.key)
        })
        .catch((res) =>{
          console.log(res)
          console.log('err')
        })
    },
    signupClient(context, payload){
      axios({
        method:'post',
        url: `${API_URL}/api/auth/signup/client`,
        data: {
          id:payload.id,
          password:payload.password,
          password2:payload.password2,
          name:payload.name,
          email:payload.email,
          tel:payload.tel,
        }
      })
        .then((response)=>{
          console.log(response)
          context.commit('SAVE_TOKEN',response.data.key)
        })
    },
    signupCounselor(context, payload){
      console.log(payload)
      context.commit('SIGNUPCOUNSELOR',payload)
      
    }
  },
  modules: {
  }
})
