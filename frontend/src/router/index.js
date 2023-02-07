import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Login from '@/components/account/login/LoginPage.vue'
import LoginKakao from '@/components/account/login/LoginKakao.vue'
import FindId from '@/components/account/FindId.vue'
import FindPw from '@/components/account//FindPw.vue'
import SignUpClient from '@/components/account/signup/client/SignUpClient.vue'
import SignUpCounselor from '@/components/account/signup/counselor/SignUpCounselor.vue'
import SignUpCounselor2 from '@/components/account/signup/counselor/SignUpCounselor2.vue'
import CalendarView from '@/components/management/schedule/CalendarView.vue'
import KidBoard from '../views/board/kid/KidBoard.vue'
import KidBoardDetail from '../views/board/kid/KidBoardDetail.vue'
import Announce from '../views/helpdesk/announce/Announce.vue'
import AnnounceDetail from '../views/helpdesk/announce/AnnounceDetail.vue'
import Inquiry from '../views/helpdesk/inquiry/Inquiry.vue'
import InquiryDetail from '../views/helpdesk/inquiry/InquiryDetail.vue'
import ClientManage from '@/components/management/client/ClientManage.vue'
import KidBoardCreate from '../views/board/kid/KidBoardCreate.vue'
import AnnounceCreate from '../views/helpdesk/announce/AnnounceCreate.vue'
import InquiryCreate from '../views/helpdesk/inquiry/InquiryCreate.vue'
import VideoPage from '../components/video/VideoPage.vue'
import ConsultantCardItem from '@/components/ConsultantCard/ConsultantCardItem.vue'
import ConsultantCardItemReviewCreate from '@/components/ConsultantCard/ConsultantCardItemReviewCreate.vue'
import ConsultantCardItemReviewItemDetail from '@/components/ConsultantCard/ConsultantCardItemReviewItemDetail.vue'
import MyPage from '@/views/my/MyPage.vue'
import NoticeView from '../views/my/notice/NoticeView.vue'
import MyPostView from '@/views/my/mypost/MyPostView.vue'
import LikeView from '@/views/my/like/LikeView.vue'
import CheckEditInformation from '@/views/my/edit/CheckEditInformation.vue'
import EditInformation from '@/views/my/edit/EditInformation.vue'
import MainEdit from '@/views/my/edit/MainEdit.vue'
import ClientManageDetail from '@/components/management/client/ClientManageDetail.vue'

import AlarmView from '../views/alarm/AlarmView.vue'
import AlarmViewDetail from '../views/alarm/AlarmViewDetail.vue'

import PageNotFound from '../views/PageNotFound.vue';


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
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
    name: 'child',
    component: KidBoard
  },
  {
    path: '/KidBoardDetail/:postId?',
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
    path: '/AnnounceDetail/:id?',
    name: 'AnnounceDetail',
    component: AnnounceDetail
  },
  {
    path: '/Inquiry',
    name: 'Inquiry',
    component: Inquiry
  },
  {
    path: '/InquiryDetail/:id?',
    name: 'InquiryDetail',
    component: InquiryDetail
  },
  {
    path: '/ClientManage',
    name: 'ClientManage',
    component: ClientManage
  },
  {
    path: '/CheckEditInformation',
    name: 'CheckEditInformation',
    component: CheckEditInformation
  },
  {
    path: '/EditInformation',
    name: 'EditInformation',
    component: EditInformation
  },
  {
    path: '/KidBoardCreate/:postId?',
    name: 'KidBoardCreate',
    component: KidBoardCreate,
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
  {
    path: '/consultantcarditemreviewcreate/:id?',
    name: 'consultantcarditemreviewcreate',
    component: ConsultantCardItemReviewCreate,
    props:true
  },
  {
    path: '/ConsultantCardItemReviewItemDetail/:id?',
    name: 'ConsultantCardItemReviewItemDetail',
    component: ConsultantCardItemReviewItemDetail,
    props:true
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: MyPage,
  },
  {
    path: '/MyPostView',
    name: 'MyPostView',
    component: MyPostView,
  },
  {
    path: '/LikeView',
    name: 'LikeView',
    component: LikeView,
  },
  {
    path: '/NoticeView',
    name: 'NoticeView',
    component: NoticeView
  },
  {
    path: '/MainEdit',
    name: 'MainEdit',
    component: MainEdit
  },
  {
    path: '/ClientManageDetail/:id?',
    name: 'ClientManageDetail',
    component: ClientManageDetail,
    props:true
  },
  {
    path: '/AlarmView',
    name: 'AlarmView',
    component: AlarmView
  },
  {
    path: '/AlarmViewDetail/:id?',
    name: 'AlarmViewDetail',
    component: AlarmViewDetail
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404'
  },
  {
    path: '/404',
    name: 'PageNotFound',
    component: PageNotFound
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


export default router
