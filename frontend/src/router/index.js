import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '@/components/account/login/LoginPage.vue'
import FindId from '@/components/account/findid/FindId.vue'
import FindPw from '@/components/account/findpw/FindPw.vue'
import LoginKakao from '@/components/account/login/LoginKakao.vue'

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
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
