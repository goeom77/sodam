
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'
import createPersistedState from "vuex-persistedstate";


const API_URL = 'http://127.0.0.1:8080'
export default new Vuex.Store({
  plugins: [createPersistedState()],
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
    },
    
  },
  getters: {
    isLogin(state) {
      return state.token ? true : false
    },

  },
  mutations: {
    GET_KIDBOARDARTICLES(state, KidBoardarticles) {
      console.log(KidBoardarticles)
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
    SAVECOUNSELOR(state,payload){
      state.userSignupData = payload
    },
    DELETE_TOKEN(state){
      state.token = null,
      state.userSignupData = null
    },
    GETCOUNSELORINFO(state){
      console.log(state)
    }

  },
  actions: {
    getKidBoardArticles(context) {
      axios({
        method: 'get',
        url: `${API_URL}/api/trouble/list/child`,
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
          console.log('게시글이 존재하지 않습니다.')
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
          console.log(res.data)
          context.commit('GET_HISTORYVIEWARTICLES', res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    getCounselorInfo(context) {
      axios({
        method: 'get',
        url: `${API_URL}/api/client/`,
        headers: {
          Authorization: `Token ${context.state.token}`
        }
      })
        .then((res) => {
          console.log(res, context)
          context.commit('GETCOUNSELORINFO', res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    login(context, payload){
      axios({
        method:'post',
        url:`${API_URL}/api/auth/login`,
        data:{
          id: payload.id,
          password: payload.password,
          common_code: payload.common_code,
        }
      })
        .then((res)=>{
          console.log(res)
          context.commit('SAVE_TOKEN', res.data)
        })
        .then(res=>{
          router.push({ name: 'home' })

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

    saveCounselor(context, payload){
      context.commit('SAVECOUNSELOR',payload)
    },

    signupCounselor(context, payload){
      console.log(payload)
      axios({
        method:'post',
        url: `${API_URL}/api/auth/signup/counselor`,
        data:{
          id: payload.id,
          password: payload.password,
          name: payload.name,
          tel: payload.tel,
          email: payload.email,
          gender: payload.gender,
          enterprise_id: payload.enterprise_id,
        }
      })
      .then((res)=>{
        console.log(res)
        context.commit('SAVE_TOKEN',res)
      })
      .catch((res)=>{
        console.log(res)
      })
    },
    logOut(context){
      console.log(this.state.token)
      context.commit('DELETE_TOKEN')
    }
  },
  modules: {
  }
})
