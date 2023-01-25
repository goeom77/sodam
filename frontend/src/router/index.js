import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '@/components/account/login/LoginPage.vue'
import FindId from '@/components/account/findid/FindId.vue'
import FindPw from '@/components/account/findpw/FindPw.vue'
import LoginKakao from '@/components/account/login/LoginKakao.vue'
import SignUpClient from '@/components/account/signup/client/SignUpClient.vue'
import SignUpCounselor from '@/components/account/signup/counselor/SignUpCounselor.vue'
import CalendarView from '@/components/management/CalendarView.vue'
import KidBoard from '../views/board/Kid/KidBoard.vue'
import announce from '../views/helpdesk/announce/announce.vue'
import ClientManage from '@/components/management/ClientManage.vue'
import notice from '../views/my/notice/notice.vue'
import CheckEditInformation from '../views/editinformation/CheckEditInformation.vue'
import EditInformation from '../views/editinformation/EditInformation.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
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
    path:'/api/auth/signup/client',
    name:'signupclient',
    component: SignUpClient
  },
  {
    path:'/signupcounselor',
    name:'signupcounselor',
    component: SignUpCounselor
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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
