import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '@/components/account/login/LoginPage.vue'
import Find_Id from '@/components/account/find_id/Find_Id.vue'
import Find_Pw from '@/components/account/find_pw/Find_Pw.vue'
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
    path:'/find_Id',
    name:'find_Id',
    component: Find_Id
  },
  {
    path:'/find_Pw',
    name:'find_Pw',
    component: Find_Pw
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
