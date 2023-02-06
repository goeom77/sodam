import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'
import createPersistedState from "vuex-persistedstate";


const VUE_APP_API_URL = process.env.VUE_APP_API_URL


// const state = {
//   LoadingStatus: false
// }



export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
    KidBoardarticles: [],
    Announcearticles: [],
    Inquiryarticles: [],
    AlarmViewarticles: [],
    SangdamAlarmViewarticles: [],
    BoardAlarmViewarticles: [],
    HelpAlarmViewarticles: [],
    GuitarAlarmViewarticles: [],
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
    GET_HISTORYVIEWARTICLES(state, HistoryViewarticles) {
      state.HistoryViewarticles = HistoryViewarticles
    },
    // 여기부터 알람 데이터 저장
    // 여기는 전체 알람 데이터
    GET_ALARMARTICLES(state, AlarmViewarticles) {
      console.log(AlarmViewarticles)
      state.AlarmViewarticles = AlarmViewarticles
    },
    // 여기는 상담 알람 데이터

    GET_SANGDAMALARMARTICLES(state, SangdamAlarmViewarticles) {
      console.log(SangdamAlarmViewarticles)
      state.SangdamAlarmViewarticles = SangdamAlarmViewarticles
    },
    // 여기는 고민게시판 알람 데이터
    GET_BOARDALARMARTICLES(state, BoardAlarmViewarticles) {
      console.log(BoardAlarmViewarticles)
      state.BoardAlarmViewarticles = BoardAlarmViewarticles
    },
    // 여기는 헬프데스크 알람 데이터
    GET_HELPALARMARTICLES(state, HelpAlarmViewarticles) {
      console.log(HelpAlarmViewarticles)
      state.HelpAlarmViewarticles = HelpAlarmViewarticles
    },
    // 여기는 기타 알람 데이터
    GET_GUITARALARMARTICLES(state, GuitarAlarmViewarticles) {
      console.log(GuitarAlarmViewarticles)
      state.GuitarAlarmViewarticles = GuitarAlarmViewarticles
    },
    // 여기까지 알람데이터 저장

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
    },
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
    getAnnounceArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/notice`,
        // data: {
        //   category : category ,
        // },
        headers: {
          Authorization: `Token ${context.state.token}`
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
          Authorization: `Token ${context.state.token}`
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
    // 여기서부터 알람목록 엑시오스
    // 알람 전체 목록
    getAlarmArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification`,
        headers: {
          "Authorization" : `Bearer ${this.state.token.token.access_token}`}
      })
        .then((res) => {
          console.log('전체알람 인덱스')
          context.commit('GET_ALARMARTICLES', res.data)
        })
        .catch((err) => {
          console.log('알림이 존재하지 않습니다.')
        })
    },
    // 알람 상담 목록
    getSangdamAlarmArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification?type=1`,
        headers: {
          "Authorization" : `Bearer ${this.state.token.token.access_token}`}
      })
        .then((res) => {
          context.commit('GET_SANGDAMALARMARTICLES', res.data)
        })
        .catch((err) => {
          console.log('알림이 존재하지 않습니다.')
        })
    },
    // 알람 고민게시판 목록
    getBoardAlarmArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification?type=2`,
        headers: {
          "Authorization" : `Bearer ${this.state.token.token.access_token}`}
      })
        .then((res) => {

          context.commit('GET_BOARDALARMARTICLES', res.data)
        })
        .catch((err) => {
          console.log('알림이 존재하지 않습니다.')
        })
    },
    // 알람 헬프데스크 목록
    getHelpAlarmArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification?type=3`,
        headers: {
          "Authorization" : `Bearer ${this.state.token.token.access_token}`}
      })
        .then((res) => {
          console.log('헬프알람 인덱스')
          context.commit('GET_HELPALARMARTICLES', res.data)
        })
        .catch((err) => {
          console.log('알림이 존재하지 않습니다.')
        })
    },
    // 알람 기타 목록
    // getGuitarAlarmArticles(context) {
    //   axios({
    //     method: 'get',
    //     url: `${VUE_APP_API_URL}/api/my-page/notification`,
    //     headers: {
    //       Authorization: `Token ${context.state.token}`}
    //   })
    //     .then((res) => {

    //       context.commit('GET_GUITARALARMARTICLES', res.data)
    //     })
    //     .catch((err) => {
    //       console.log('알림이 존재하지 않습니다.')
    //     })
    // },
    //여기까지 알람목록 엑시오스



    getHistoryViewArticles(context) {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/backend/`,
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
        method: 'post',
        url: `${VUE_APP_API_URL}/api/client/`,
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
        }
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
        }
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
          Authorization: `Token ${context.state.token}`
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
