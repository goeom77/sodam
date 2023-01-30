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
import KidBoard from '../views/board/Kid/KidBoard.vue'
import announce from '../views/helpdesk/announce/announce.vue'
import inquiry from '../views/helpdesk/inquiry/inquiry.vue'
import ClientManage from '@/components/management/ClientManage.vue'
import notice from '../views/my/notice/notice.vue'
import CheckEditInformation from '../views/editinformation/CheckEditInformation.vue'
import EditInformation from '../views/editinformation/EditInformation.vue'
import KidBoardCreate from '../views/board/Kid/KidBoardCreate.vue'
import AnnounceCreate from '../views/helpdesk/announce/AnnounceCreate.vue'
import InquiryCreate from '../views/helpdesk/inquiry/InquiryCreate.vue'

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
    path:'/calendar',
    name:'calendar',
    component: CalendarView
  },
  {
    path: '/KidBoard',
    name: 'KidBoard',
    component: KidBoard
  },

  {
    path: '/announce',
    name: 'announce',
    component: announce
  },
  {
    path: '/inquiry',
    name: 'inquiry',
    component: inquiry
  },
  {
    path: '/notice',
    name: 'notice',
    component: notice
  },
  {
    path: '/clientmanage',
    name: 'clientmanage',
    component: ClientManage
  },
  {
    path: '/checkeditinformation',
    name: 'checkeditinformation',
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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
