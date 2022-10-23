import { createRouter, createWebHistory } from 'vue-router'
import CombatIndexView from '../views/combat/CombatIndexView'
import RanklistIndexView from '../views/ranklist/RanklistIndexView'
import RecordIndexView from '../views/record/RecordIndexView'
import UserAccountLoginView from '../views/account/UserAccountLogin'
import UserAccountRegisterView from '../views/account/UserAccountRegister'
import NotFoundView from '../views/others/404NotFound'
import store from '@/store'

const routes = [
  { path: '/', component: CombatIndexView},
  {
    path : "/combat/",
    component : CombatIndexView, 
    name : "combat",
    meta:{
      requestAuth: true,
    }
  },
  {
    path : "/ranklist/",
    component : RanklistIndexView,  
    name : "ranklist",
    meta:{
      requestAuth: false,
    }
  },
  {
    path : "/record/",
    component : RecordIndexView, 
    name : "record",
    meta:{
      requestAuth: false,
    }
  },
  {
    path : "/register/",
    component : UserAccountRegisterView, 
    name : "user_account_register",
    meta:{
      requestAuth: false,
    }
  },
  {
    path : "/login/",
    component : UserAccountLoginView, 
    name : "user_account_login",
    meta:{
      requestAuth: false,
    }
  },
  {
    path : "/404notfound/",
    component : NotFoundView, 
    name : "404_not_found",
    meta:{
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404notfound/"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(async (to, from, next) => {
  const mypromise = new Promise((resolve, reject) => {
    const jwt_token = localStorage.getItem("jwt_token");
    if(jwt_token) {
      store.commit("updateToken", jwt_token);
      store.dispatch("getinfo", {
        success() {
          resolve();
        },
        error(resp){
          console.log(resp);
          reject();
        }
      })
    }
    else resolve();
  })

  const b = () => {
    if( to.meta.requestAuth && !store.state.user.is_login) {
      next({name :"user_account_login"});
    } 
    else next();
  }

  await mypromise.then(b).catch(() => {
    next({name :"user_account_login"});
  });

})

export default router