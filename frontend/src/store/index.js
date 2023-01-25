
import Vuex from 'vuex'
import axios from 'axios'

import router from '@/router'

export default new Vuex.Store({
  state: {
  },
  getters: {
  },
  mutations: {
    SAVE_TOKEN(state, token) {
      state.token = token
      router.push({ name: 'movie' })
    },
    SET_USER_DATA(state, payload) {
      state.payload = {
        username: payload.username,
        password: payload.password
      }
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

    login(context,payload){
      axios({
        method:'post',
        url:'192.33333333',
        data:{
          ID: payload.ID,
          password: payload.password,
        }
      })
        .then((res)=>{
          context.commit('SAVE_TOKEN', res.data.key)
          context.commit('SET_USER_DATA', payload)
          console.log(res)
          
        })
    },
  },
  modules: {
  }
})
