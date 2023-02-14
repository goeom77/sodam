import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'
import createPersistedState from "vuex-persistedstate";

// 로딩 임포트
export const LOADING_STATUS = 'LOADING_STATUS'
// import * as types from './mutation_types'


const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default new Vuex.Store({
    plugins: [createPersistedState()],
    state: {
        loadingStatus: false,
        newNotiCount: 0,
        KidBoardarticles: [],
        Announcearticles: [],
        Inquiryarticles: [],
        AlarmViewarticles: [],
        SangdamAlarmViewarticles: [],
        BoardAlarmViewarticles: [],
        HelpAlarmViewarticles: [],
        GuitarAlarmViewarticles: [],
        token: null,
        payload: {
            id: null,
            password: null,
            common_code: null
        },
        userSignupData: {
            id: null,
            password: null,
            name: null,
            tel: null,
            email: null,
            gender: null,
            enterprise_id: null,
        },
    },
    getters: {
        isLogin(state) {
            return state.token ? true : false
        },
        getUserData(state) {
            return state.userSignupData
        },
        getNotiCount(state) {
            return state.newNotiCount;
        },
        getIdData(state) {
            return state.payload.id
        }
    },
    mutations: {
        // 로딩 타입
        LOADING_STATUS(state, loadingStatus) {
            console.log("state : " + JSON.stringify(state) + " loadingStatus" + loadingStatus)
            state.loadingStatus = loadingStatus
        },

        GET_BOARDARTICLES(state, Boardarticles) {
            console.log(Boardarticles)
            state.Boardarticles = Boardarticles
        },
        GET_BOARDMYARTICLES(state, BoardMyarticles) {
            console.log(BoardMyarticles)
            state.BoardMyarticles = BoardMyarticles
        },
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
        SAVECOUNSELOR(state, payload) {
            state.userSignupData = payload
        },
        DELETE_TOKEN(state) {
            state.token = null,
            state.userSignupData = null
        },
        GETCOUNSELORINFO(state) {
            console.log(state)
        },
        RESERVECONSULT(state) {
            console.log(state)
        },
        UNREAD_NOTI_COUNT(state, payload) {
            state.newNotiCount = payload;
        },
        COUNT_NOTI(state) {
            state.newNotiCount += 1;
        },
        DISCOUNT_NOTI(state) {
            state.newNotiCount -= 1;
        }
    },
    actions: {
        // 고민게시판 전체 불러오기
        getBoardArticles(context) {
            axios({
                method: 'get',
                url: `${VUE_APP_API_URL}/api/trouble/list`,
            })
                .then((res) => {
                    context.commit('GET_BOARDARTICLES', res.data)
                })
                .catch((err) => {
                    console.log('게시글이 존재하지 않습니다.')
                })
        },
        // 고민게시판 전체 불러오기 (내 글)
        getBoardMyArticles(context) {
            axios({
                method: 'get',
                url: `${VUE_APP_API_URL}/api/trouble/my-trouble`,
            })
                .then((res) => {
                    context.commit('GET_BOARDMYARTICLES', res.data)
                })
                .catch((err) => {
                    console.log('게시글이 존재하지 않습니다.')
                })
        },
        // 고민게시판 아동 불러오기
        getKidBoardArticles(context) {
            axios({
                method: 'get',
                url: `${VUE_APP_API_URL}/api/trouble/list/child`,
            })
                .then((res) => {
                    context.commit('GET_KIDBOARDARTICLES', res.data)
                })
                .catch((err) => {
                    console.log('게시글이 존재하지 않습니다.')
                })
        },
        // HELP DESK - 공지사항
        getAnnounceArticles(context) {
            axios({
                method: 'get',
                url: `${VUE_APP_API_URL}/api/help-desk/notice/list`,
                // data: {
                //   category : category ,
                // },
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
                url: `${VUE_APP_API_URL}/api/help-desk/qna/list`,
                // data: {
                //   category : category ,
                // },
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
                    "Authorization": `Bearer ${this.state.token.token.access_token}`
                }
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
                    "Authorization": `Bearer ${this.state.token.token.access_token}`
                }
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
                    "Authorization": `Bearer ${this.state.token.token.access_token}`
                }
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
                    "Authorization": `Bearer ${this.state.token.token.access_token}`
                }
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
                headers: {
                    Authorization: `Bearer ${this.$store.state.token.token.access_token}`
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
        login(context, payload) {
            axios({
                method: 'post',
                url: `${VUE_APP_API_URL}/api/auth/login`,
                data: {
                    id: payload.id,
                    password: payload.password,
                    common_code: payload.common_code,
                },
            })
                .then((res) => {
                    console.log(res)
                    context.commit('SAVE_TOKEN', res.data)
                    context.commit('SET_USER_DATA', payload)
                })
                .then(res => {
                    router.push({name: 'home'})

                })
                .catch((res) => {
                    console.log(res)
                    console.log('err')
                })
        },

        signupClient(context, payload) {
            axios({
                method: 'post',
                url: `${VUE_APP_API_URL}/api/auth/signup/client`,
                data: {
                    id: payload.id,
                    password: payload.password,
                    name: payload.name,
                    email: payload.email,
                    tel: payload.tel,
                },
            })
                .then((response) => {

                    context.commit('SAVE_TOKEN', response.data.key)
                })
        },

        saveCounselor(context, payload) {
            context.commit('SAVECOUNSELOR', payload)
        },

        signupCounselor(context, payload) {
            const formdata = new FormData()
            formdata.append('id', payload.id)
            formdata.append('password', payload.password)
            formdata.append('name', payload.name)
            formdata.append('tel', payload.tel)
            formdata.append('email', payload.email)
            formdata.append('gender', payload.gender)
            formdata.append('enterprisestr', payload.enterprise_id)
            console.log('payload - certificate start')
            if (payload.certificate.length > -1) {
                for (let i = 0; i < payload.certificate.length; i++) {
                    const certificateForm = payload.certificate[i]
                    formdata.append(`certificates[${i}].name`, certificateForm.certificate_name)
                    formdata.append(`certificates[${i}].serial_num`, certificateForm.certificate_number)
                    formdata.append(`certificates[${i}].agency`, certificateForm.certificate_agency)
                    formdata.append(`certificates[${i}].file`, certificateForm.certificate_file[0])
                }
            }
            if (payload.education.length > -1) {
                for (let i = 0; i < payload.education.length; i++) {
                    const educationForm = payload.education[i]
                    formdata.append(`educations[${i}].degree`, educationForm.degree)
                    formdata.append(`educations[${i}].school`, educationForm.school)
                    formdata.append(`educations[${i}].major`, educationForm.major)
                    formdata.append(`educations[${i}].is_graduate`, educationForm.is_graduate)
                    formdata.append(`educations[${i}].file`, educationForm.education_file[0])
                }
            }
            if (payload.career.length > -1) {
                for (let i = 0; i < payload.career.length; i++) {
                    const careerForm = payload.career[i]
                    formdata.append(`careers[${i}].name`, careerForm.career_name)
                    formdata.append(`careers[${i}].content`, careerForm.career_content)
                    formdata.append(`careers[${i}].period`, careerForm.career_period)
                }
            }
            // form 객체 확인 key-value
            for (var pair of formdata.entries()) {
                console.log(pair[0] + ', ' + pair[1]);
            }
            return axios({
                method: 'post',
                url: `${VUE_APP_API_URL}/api/auth/signup/counselor`,
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
                data: formdata,
                dataType: 'json',
                processData: false,
                contentType: false,
            })
                .then((res) => {
                    console.log(res)
                })
                .catch((res) => {
                    console.log(res)
                })
        },

        counselorUpdate(context, payload) {
            const formdata = new FormData()
            formdata.append('id', payload.id)
            formdata.append('password', payload.password)
            formdata.append('name', payload.name)
            formdata.append('tel', payload.name)
            formdata.append('email', payload.email)
            formdata.append('gender', payload.gender)
            formdata.append('enterprisestr', payload.enterprise_id)
            console.log('payload - certificate start')
            if (payload.certificate.length > -1) {
                for (let i = 0; i < payload.certificate.length; i++) {
                    const certificateForm = payload.certificate[i]
                    formdata.append(`certificates[${i}].name`, certificateForm.certificate_name)
                    formdata.append(`certificates[${i}].serial_num`, certificateForm.certificate_number)
                    formdata.append(`certificates[${i}].agency`, certificateForm.certificate_agency)
                    formdata.append(`certificates[${i}].file`, certificateForm.certificate_file[0])
                }
            }
            if (payload.education.length > -1) {
                for (let i = 0; i < payload.education.length; i++) {
                    const educationForm = payload.education[i]
                    formdata.append(`educations[${i}].degree`, educationForm.degree)
                    formdata.append(`educations[${i}].school`, educationForm.school)
                    formdata.append(`educations[${i}].major`, educationForm.major)
                    formdata.append(`educations[${i}].is_graduate`, educationForm.is_graduate)
                    formdata.append(`educations[${i}].file`, educationForm.education_file[0])
                }
            }
            if (payload.career.length > -1) {
                for (let i = 0; i < payload.career.length; i++) {
                    const careerForm = payload.career[i]
                    formdata.append(`careers[${i}].name`, careerForm.career_name)
                    formdata.append(`careers[${i}].content`, careerForm.career_content)
                    formdata.append(`careers[${i}].period`, careerForm.career_period)
                }
            }
            // form 객체 확인 key-value
            for (var pair of formdata.entries()) {
                console.log(pair[0] + ', ' + pair[1]);
            }
            return axios({
                method: 'put',
                url: `${VUE_APP_API_URL}/api/counselor/${this.$store.state.payload.id}`,
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
                data: formdata,
                dataType: 'json',
                processData: false,
                contentType: false,
            })
                .then((res) => {
                    console.log(res)
                })
                .catch((res) => {
                    console.log(res)
                })
        },


        logOut(context) {
            context.commit('DELETE_TOKEN')
        },
        reserveConsult(context, payload) {
            axios({
                method: 'POST',
                url: `${API_URL}/api/consultApplicant`,
                data: {
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
                    Authorization: `Bearer ${this.$store.state.token.token.access_token}`
                }
            })
                .then((res) => {
                    console.log(res, payload)
                    context.commit('RESERVECONSULT')
                })
        },
        unreadNotiCount(context) {
            axios({
                method: 'GET',
                url: `${VUE_APP_API_URL}/api/my-page/unread-noti`,
                headers: {
                    Authorization: `Bearer ${this.state.token.token.access_token}`
                }
            })
                .then((res) => {
                    context.commit('UNREAD_NOTI_COUNT', res.data);
                })
        },
        countNoti(context) {
            context.commit('COUNT_NOTI');
        },
        discountNoti(context) {
            context.commit('DISCOUNT_NOTI');
        },
    },
    modules: {}
})
