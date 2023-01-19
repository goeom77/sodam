
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
    }
  },
  modules: {
  }
})
