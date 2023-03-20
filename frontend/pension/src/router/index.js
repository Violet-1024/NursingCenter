import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login.vue'
import welcome from '../views/Welcome/welcome.vue'
import pathArr from './pathArr.js'

Vue.use(VueRouter)

const routes = [{
  path: '/',
  redirect: '/welcome',
},
{
  path: '/login',
  name: 'Login',
  component: () =>
    import('../views/login.vue')
},
  {
    path: '/welcome',
    name: 'welcome',
    component: () =>import ('../views/Welcome/welcome.vue')
  },


  {
    path: "/mainforuser",
    name: "mainforuser",
    component: () =>
        import('../views/Mainforuser/mainforuser.vue'),

    children: [
      {
        path:"customerPage",
        component:() => import('../views/Mainforuser/customerPage.vue')
      },
      {
        path: "addAbinfo",
        component:() => import('../views/ExceptionInfo/addAbinfo.vue')
      },
      {
        path: "addCustomer",
        component:() => import('../views/Custom/addCustomer.vue')
      },
      {
        path: "bedSelect",
        component:() => import('../views/Bed/bedSelect.vue')
      },
      {
        path: "bespokeFood",
        component:() => import('../views/Mainforuser/bespokeFood.vue')
      },
    ]


  },




{
  path: "/main",
  name: "main",
  component: () =>
      import('../views/Main.vue'),

  children: [
    {
      path:"",
      component:() => import('../views/Custom/customList')
    },
    {
      path: "customList",
      component: () => import('../views/Custom/customList')
    },
    {
      path:"evaluationList" ,
      name:"evaluationList" ,
      component: () => import('../views/Evaluation/evaluationList.vue')
    },
    {
      path:"evaluationConfig",
      name:"evaluationConfig",
      component:()=>import('../views/Evaluation/evaluationConfig.vue')
    },
    
    {
      path: 'bedList',
      name: 'bedList',
      component: () =>
          import ('../views/Bed/bedList.vue'),
    },
    {
      path: 'bedRecord',
      name: 'bedRecord',
      component: () =>
          import ('../views/Bed/bedRecord.vue'),
    },
    {
        path: 'nursingList',
        name: 'nursingList',
        component: () =>import ('../views/Nursing/nursingList.vue'),
    },
    {
      path:'healthDatas',
      name:'healthDatas',
      component:()=>import('../views/Health/healthDatas.vue')
    },
    {
      path:'healthRank',
      name:'healthRank',
      component:()=>import('../views/Health/healthRank.vue')
    },
    {
      path:'dailyApply',
      component:()=>import('../views/Apply/dailyApply.vue')
    },
    {
      path:'outingApply',
      component:()=>import('../views/Apply/outingApply.vue')
    },
    {
      path: 'approvalManagement',
      name: 'approvalManagement',
      component: () =>import ('../views/ExceptionInfo/approvalManagement.vue')
    },
    {
      path: 'applyManagement',
      name: 'applyManagement',
      component: () =>import ('../views/ExceptionInfo/applyManagement.vue')
    },
    {
      path: 'reservePlanList',
      name: 'reservePlanList',
      component: () => import('../views/ReservePlan/reservePlanList.vue')
    },
    {
      path: 'reservePlanEdit',
      name: 'reservePlanEdit',
      component: () => import('../views/ReservePlan/reservePlanEdit.vue')
    },
    {
      path: 'reservePlanCreate',
      name: 'reservePlanCreate',
      component: () => import('../views/ReservePlan/reservePlanCreate.vue')
    },

    {
      path:"orderFoodList" ,
      name:"orderFoodList" ,
      component: () => import('../views/Order/orderFoodList.vue')
    },
    {
      path:"bespokeFood",
      name:"bespokeFood",
      component:()=> import('../views/Food/bespokeFood.vue')
    },
    {
      path:"foodList",
      name:"foodList",
      component:()=>import('../views/Food/foodList.vue')
    },
    {
      path: 'employeeEnrolling',
      name: 'employeeEnrolling',
      component: () =>import ('../views/Employee/employeeEnrolling.vue')
    },
    {
      path: 'employeeList',
      name: 'employeeList',
      component: () =>import ('../views/Employee/employeeList.vue')
    },
    {
      path: 'employeeEdit',
      name: 'employeeEdit',
      component: () =>import ('../views/Employee/employeeEdit.vue')
    },
    {
      path: 'orderNursingList',
      name: 'orderNursingList',
      component: () =>import ('../views/Order/orderNursingList.vue')
    },




  ]
},
  /* {
       path:"",
       component: () => import('../views/TeamManagement.vue')
     } */
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes

})



//全局前置守卫，判断是否有token来判断是否有权限访问主页
//  router.beforeEach(function (to, from, next) {
//   if (pathArr.indexOf(to.path) !== -1) {//如果能够在路径文件中找到对应的路径
//     const token = sessionStorage.getItem('token');//获取token
//     // console.log('全局前置守卫',token);
//     if (token) {//如果存在token  表示已经登录
//       next();
//     } else {//表示未登录
//       next('/login');
//     }
//   } else {
//     next();
//   }
// })
export default router

