import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'
import createPersistedState from "vuex-persistedstate";


const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    KidBoardarticles: [],
    Announcearticles: [],
    Inquiryarticles: [],
    token:null,
    payload:{
      id: null,
      password: null,
      common_code: null
    },
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
    getUserData(state) {
      return state.userSignupData
    },

  },
  mutations: {
    GET_KIDBOARDARTICLES(state, KidBoardarticles) {
      console.log(KidBoardarticles)
      state.KidBoardarticles = KidBoardarticles
    },
    GET_ANNOUNCEARTICLES(state, Announcearticles) {
      console.log(Announcearticles)
      state.Announcearticles = Announcearticles
    },
    GET_INQUIRYARTICLES(state, Inquiryarticles) {
      console.log(Inquiryarticles)
      state.Inquiryarticles = Inquiryarticles
    },
    

    SAVE_TOKEN(state, token) {
      state.token = token
    },
    SET_USER_DATA(state, payload) {
      state.payload = {
        id: payload.id,
        password: payload.password,
        common_code: payload.common_code
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
    },
    RESERVECONSULT(state){
      console.log(state)
    }
  },
  actions: {
    getKidBoardArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/trouble/list/child`,
        // data: {
        //   category : category ,
        // },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
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
    getAnnounceArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/notice`,
        // data: {
        //   category : category ,
        // },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
        .then((res) => {
          // console.log(res, context)
          // console.log(res.data)
          context.commit('GET_ANNOUNCEARTICLES', res.data)
        })
        .catch((err) => {
          console.log('게시글이 존재하지 않습니다.')
        })
    },
    getInquiryArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/qna`,
        // data: {
        //   category : category ,
        // },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
        .then((res) => {
          // console.log(res, context)
          // console.log(res.data)
          context.commit('GET_INQUIRYARTICLES', res.data)
        })
        .catch((err) => {
          console.log('게시글이 존재하지 않습니다.')
        })
    },

    getCounselorInfo(context) {
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/client/`,
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
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
        url:`${VUE_APP_API_URL}/api/auth/login`,
        data:{
          id: payload.id,
          password: payload.password,
          common_code: payload.common_code,
        },
      })
        .then((res)=>{
          console.log(res)
          context.commit('SAVE_TOKEN', res.data)
          context.commit('SET_USER_DATA', payload)
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
        url: `${VUE_APP_API_URL}/api/auth/signup/client`,
        data: {
          id:payload.id,
          password:payload.password,
          name:payload.name,
          email:payload.email,
          tel:payload.tel,
        },
      })
        .then((response)=>{
          
          context.commit('SAVE_TOKEN',response.data.key)
        })
    },

    saveCounselor(context, payload){
      context.commit('SAVECOUNSELOR',payload)
    },

    signupCounselor(context, payload){
      const formdata = new FormData()
      formdata.append('id',payload.id)
      formdata.append('password',payload.password)
      formdata.append('name',payload.name)
      formdata.append('tel',payload.name)
      formdata.append('email',payload.email)
      formdata.append('gender',payload.gender)
      formdata.append('enterprise_id',payload.enterprise_id)

      if (payload.certificate.length > -1){
        for (let i=0;i<payload.certificate.length; i++){
          const certificateForm = payload.certificate[i]
          formdata.append(`certificates[${i}`, certificateForm)
        }
      }
      if (payload.education.length > -1){
        for (let i=0;i<payload.education.length; i++){
          const educationForm = payload.education[i]
          formdata.append(`educations[${i}`, educationForm)
        }
      }
      if (payload.career.length > -1){
        for (let i=0;i<payload.career.length; i++){
          const careerForm = payload.career[i]
          formdata.append(`careers[${i}`, careerForm)
        }
      }
      return axios({
        method:'post',
        url: `${VUE_APP_API_URL}/api/auth/signup/counselor`,
        headers:{
          'Content-Type': 'multipart/form-data',
        },
        data:{
          id: payload.id,
          password: payload.password,
          name: payload.name,
          tel: payload.tel,
          email: payload.email,
          gender: payload.gender,
          enterprise_id: payload.enterprise_id,
          data: formdata,
        }
      })
      .then((res)=>{
        console.log(res)
      })
      .catch((res)=>{
        console.log(res)
      })
    },

    logOut(context){
      context.commit('DELETE_TOKEN')
    },
    reserveConsult(context, payload){
      axios({
        method:'POST',
        url: `${API_URL}/api/consultApplicant`,
        data:{
          age: payload.age,
          clientId: payload.clientId,
          consultType: payload.consultType,
          content: payload.content,
          counselorId: payload.counselorId,
          dueDate: payload.dueDate,
          email: payload.email,
          gender: payload.gender,
          name: payload.name,
          state: payload.state,
          tel: payload.tel
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then((res)=>{
        console.log(res, payload)
        context.commit('RESERVECONSULT')
      })
    }
  },
  modules: {
  }
})
