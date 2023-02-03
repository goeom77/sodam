import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '@/components/account/login/LoginPage.vue'

import FindId from '@/components/account/FindId.vue'
import FindPw from '@/components/account//FindPw.vue'
import LoginKakao from '@/components/account/login/LoginKakao.vue'
import SignUpClient from '@/components/account/signup/client/SignUpClient.vue'
import SignUpCounselor from '@/components/account/signup/counselor/SignUpCounselor.vue'
import SignUpCounselor2 from '@/components/account/signup/counselor/SignUpCounselor2.vue'
import CalendarView from '@/components/management/CalendarView.vue'
import KidBoard from '../views/board/kid/KidBoard.vue'
import KidBoardDetail from '../views/board/kid/KidBoardDetail.vue'
import Announce from '../views/helpdesk/announce/Announce.vue'
import Inquiry from '../views/helpdesk/inquiry/Inquiry.vue'
import ClientManage from '@/components/management/ClientManage.vue'
import Notice from '../views/my/notice/Notice.vue'
import HistoryView from '../views/my/history/HistoryView.vue'
import Write from '../views/my/writereview/Write.vue'
import CheckEditInformation from '@/components/account/edit/CheckEditInformation.vue'
import EditInformation from '@/components/account/edit/EditInformation.vue'
import KidBoardCreate from '../views/board/kid/KidBoardCreate.vue'
import AnnounceCreate from '../views/helpdesk/announce/AnnounceCreate.vue'
import InquiryCreate from '../views/helpdesk/inquiry/InquiryCreate.vue'
import SignUpFinish from '../components/account/signup/counselor/SignUpFinish.vue'
import VideoPage from '../components/video/VideoPage.vue'
import ConsultantCardItem from '@/components/ConsultantCardItem.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path:'/findId',
    name:'findId',
    component: FindId
  },
  {
    path:'/findPw',
    name:'findPw',
    component: FindPw
  },
  {
    path:'/loginKakao',
    name:'loginKakao',
    component: LoginKakao
  },
  {
    path:'/signupclient',
    name:'signupclient',
    component: SignUpClient
  },
  {
    path:'/signupcounselor',
    name:'signupcounselor',
    component: SignUpCounselor
  },
  {
    path:'/signupcounselor2',
    name:'signupcounselor2',
    component: SignUpCounselor2
  },
  {
    path:'/Calendar',
    name:'Calendar',
    component: CalendarView
  },
  {
    path: '/KidBoard',
    name: 'KidBoard',
    component: KidBoard
  },
  {
    path: '/KidBoardDetail/:postId',
    name: 'KidBoardDetail',
    component: KidBoardDetail,
    // props: true
  },
  
  {
    path: '/Announce',
    name: 'Announce',
    component: Announce
  },
  {
    path: '/Inquiry',
    name: 'Inquiry',
    component: Inquiry
  },
  {
    path: '/Notice',
    name: 'Notice',
    component: Notice
  },
  {
    path: '/HistoryView',
    name: 'HistoryView',
    component: HistoryView
  },

  {
    path: '/Write',
    name: 'Write',
    component: Write
  },
  {
    path: '/Clientmanage',
    name: 'Clientmanage',
    component: ClientManage
  },
  {
    path: '/Checkeditinformation',
    name: 'Checkeditinformation',
    component: CheckEditInformation
  },
  {
    path: '/editinformation',
    name: 'editinformation',
    component: EditInformation
  },
  {
    path: '/KidBoardCreate',
    name: 'KidBoardCreate',
    component: KidBoardCreate
  },
  {
    path: '/AnnounceCreate',
    name: 'AnnounceCreate',
    component: AnnounceCreate
  },
  {
    path: '/InquiryCreate',
    name: 'InquiryCreate',
    component: InquiryCreate
  },
  {
    path: '/signupfinish',
    name: 'signupfinish',
    component: SignUpFinish
  },
  {
    path: '/VideoPage',
    name: 'VideoPage',
    component: VideoPage
  },
  {
    path: '/consultantcarditem/:id?',
    name: 'consultantcarditem',
    component: ConsultantCardItem,
    props:true
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
