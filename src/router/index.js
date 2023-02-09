import Vue from 'vue'
import VueRouter from 'vue-router'
// import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)
const routes = [

  {
    path: '/admin',
    name: 'adminplus',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "AdminViewPlus" */ '../views/AdminViewPlus.vue'),
    redirect:"/admin/adminHomeView",
    children:[
      {path: 'adminHomeView',name: 'AdminHomeView',component: () => import(/* webpackChunkName: "HomeView" */ '../views/HomeView.vue'),  },
      {path: 'adminView',name: 'AdminView',component: () => import(/* webpackChunkName: "Rent" */ '../views/AdminView.vue'), },
      {path: 'adminPerson',name: 'AdminPerson',component: () => import(/* webpackChunkName: "Person" */ '../views/Person.vue'), },

    ],

  },

  {
    path: '/',
    name: 'login',
    component: () => import(/* webpackChunkName: "login" */ '../views/Login.vue'),

  },

  {
    path: '/register',
    name: 'register',
    component: () => import(/* webpackChunkName: "register" */ '../views/Register.vue'),

  },


  {
    path: '/rent',
    name: 'rent',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "RentViewPlus" */ '../views/RentViewPlus.vue'),
     redirect:"/rent/rentHomeView",
    children:[
      {path: 'rentHomeView',name: 'RentHomeView',component: () => import(/* webpackChunkName: "rentHomeView" */ '../views/RentHomeView.vue'), },
      {path: 'rentView',name: 'RentView',component: () => import(/* webpackChunkName: "RentView" */ '../views/RentView.vue'), },
      {path: 'rentPerson',name: 'RentPerson',component: () => import(/* webpackChunkName: "RentPerson" */ '../views/RentPerson.vue'), },

    ],

  },

  {
    path: '/user',
    name: 'user',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "UserViewPlus" */ '../views/UserViewPlus.vue'),
    redirect:"/user/userHomeView",

    //GroupView

    children:[
      {path: 'userHomeView',name: 'UserHomeView',component: () => import(/* webpackChunkName: "UserHomeView" */ '../views/UserHomeView.vue'), },
      {path: 'qnAnswer',name: 'QnAnswer',component: () => import(/* webpackChunkName: "QnAnswer" */ '../views/QnAnswer.vue'), },
      {path: 'doneAnswer',name: 'DoneAnswer',component: () => import(/* webpackChunkName: "DoneAnswer" */ '../views/DoneAnswer.vue'), },
      {path: 'GroupView',name: 'GroupView',component: () => import(/* webpackChunkName: "UserView" */ '../views/GroupView.vue'), },
      {path: 'userView',name: 'UserView',component: () => import(/* webpackChunkName: "UserView" */ '../views/UserView.vue'), },
      {path: 'userQnShow',name: 'UserQnShow',component: () => import(/* webpackChunkName: "UserQnShow" */ '../views/UserQnShow.vue'), },
      {path: 'userCos',name: 'UserCos',component: () => import(/* webpackChunkName: "UserCos" */ '../views/UserCos.vue'), },

  //    {path: 'stadata',name: 'stadata',component: () => import(/* webpackChunkName: "UserCos" */ '../views/stadata.html'), },

      {path: 'userQsBank',name: 'UserQsBank',component: () => import(/* webpackChunkName: "UserQsBank" */ '../views/UserQsBank.vue'), },
      {path: 'qnView',name: 'QnView',component: () => import(/* webpackChunkName: "QnView" */ '../views/QnView.vue'), },
      {path: 'qnEditView',name: 'QnEditView',component: () => import(/* webpackChunkName: "QnEditView" */ '../views/QnEditView.vue'), },
      {path: 'userPerson',name: 'UserPerson',component: () => import(/* webpackChunkName: "UserPerson" */ '../views/UserPerson.vue'), },

    ],

  },

  {
    path: '/answer',
    name: 'answer',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "AnswerViewPlus" */ '../views/AnswerViewPlus.vue'),
    redirect:"/answer/answerHomeView",
    children:[
      {path: 'answerHomeView',name: 'AnswerHomeView',component: () => import(/* webpackChunkName: "AnswerHomeView" */ '../views/AnswerHomeView.vue'), },
      {path: 'answerView',name: 'AnswerView',component: () => import(/* webpackChunkName: "AnswerView" */ '../views/AnswerView.vue'), },
      {path: 'qndoView',name: 'QnDoView',component: () => import(/* webpackChunkName: "QnDoView" */ '../views/QnDoView.vue'), },
      {path: 'qndoneView',name: 'QndoneView',component: () => import(/* webpackChunkName: "QndoneView" */ '../views/QndoneView.vue'), },
      {path: 'answerPerson',name: 'AnswerPerson',component: () => import(/* webpackChunkName: "AnswerPerson" */ '../views/AnswerPerson.vue'), },

    ],

  },

]

const router = new VueRouter({
  routes
})

export default router