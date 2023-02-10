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


// 고민게시판 페이지
import BoardView from '../views/board/BoardView.vue'
// 고민게시판 상세보기
import BoardDetailView from '../views/board/BoardDetailView.vue'
// 고민게시판 작성하기
import BoardCreateView from '../views/board/BoardCreateView.vue'


// 헬프데스크 페이지
import HelpView from '../views/helpdesk/HelpView.vue'
// 공지사항 상세보기
import NoticeDetailView from '../views/helpdesk/NoticeDetailView.vue'
// 공지사항 작성하기
import NoticeCreateView from '../views/helpdesk/NoticeCreateView.vue'
// 문의사항 상세보기
import QnaDetailView from '../views/helpdesk/QnaDetailView.vue'
// 문의사항 작성하기
import QnaCreateView from '../views/helpdesk/QnaCreateView.vue'


import ClientManage from '@/components/management/client/ClientManage.vue'
import VideoPage from '../components/video/VideoPage.vue'
import ConsultantCardItem from '@/components/ConsultantCard/ConsultantCardItem.vue'
import ConsultantCardItemReviewCreate from '@/components/ConsultantCard/ConsultantCardItemReviewCreate.vue'
import ConsultantCardItemReviewItemDetail from '@/components/ConsultantCard/ConsultantCardItemReviewItemDetail.vue'
import ConsultantCardItemReviewUpdate from '@/components/ConsultantCard/ConsultantCardItemReviewUpdate.vue'
import MyPage from '@/views/my/MyPage.vue'
import MyPostView from '@/views/my/mypost/MyPostView.vue'
import LikeView from '@/views/my/like/LikeView.vue'
import CheckEditInformation from '@/views/my/edit/CheckEditInformation.vue'
import EditInformation from '@/views/my/edit/EditInformation.vue'
import MainEdit from '@/views/my/edit/MainEdit.vue'
import ClientManageDetail from '@/components/management/client/ClientManageDetail.vue'
import AlarmView from '../views/alarm/AlarmView.vue'
import AlarmViewDetail from '../views/alarm/AlarmViewDetail.vue'
import CalendarView22 from "@/components/management/schedule/CalendarView22.vue";
import CalendarTest from "@/components/management/schedule copy/CalendarTest.vue";


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
    component: CalendarTest
    // component: CalendarView
  },
  
  
  // 고민게시판 페이지
  {
    path: '/BoardView',
    name: 'BoardView',
    component: BoardView
  },
  // 고민게시판 상세보기
  {
    path: '/BoardDetailView/:postId?',
    name: 'BoardDetailView',
    component: BoardDetailView,
  },
  // 고민게시판 작성하기
  {
    path: '/BoardCreateView/:postId?',
    name: 'BoardCreateView',
    component: BoardCreateView,
  },
  
  
  // 헬프데스크 페이지
  {
    path: '/HelpView',
    name: 'HelpView',
    component: HelpView
  },
  // 공지사항 상세보기
  {
    path: '/NoticeDetailView/:id?',
    name: 'NoticeDetailView',
    component: NoticeDetailView
  },
  // 공지사항 작성하기
  {
    path: '/NoticeCreateView',
    name: 'NoticeCreateView',
    component: NoticeCreateView
  },
  // 문의사항 상세보기 
  {
    path: '/QnaDetailView/:id?',
    name: 'QnaDetailView',
    component: QnaDetailView
  },
  // 문의사항 작성하기  
  {
    path: '/QnaCreateView',
    name: 'QnaCreateView',
    component: QnaCreateView
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
    path: '/VideoPage',
    name: 'VideoPage',
    component: VideoPage,
    props:true
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
    path: '/ConsultantCardItemReviewUpdate/:id?',
    name: 'ConsultantCardItemReviewUpdate',
    component: ConsultantCardItemReviewUpdate,
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

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})


export default router
