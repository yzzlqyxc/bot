import { createRouter, createWebHistory } from 'vue-router'
import CombatIndexView from '../views/combat/CombatIndexView'
import RanklistIndexView from '../views/ranklist/RanklistIndexView'
import RecordIndexView from '../views/record/RecordIndexView'
import UserAccountLoginView from '../views/account/UserAccountLogin'
import UserAccountRegisterView from '../views/account/UserAccountRegister'

const routes = [
  {
    path : "/combat/",
    component : CombatIndexView, 
    name : "combat"
  },
  {
    path : "/ranklist/",
    component : RanklistIndexView,  
    name : "ranklist"
  },
  {
    path : "/record/",
    component : RecordIndexView, 
    name : "record"
  },
  {
    path : "/register/",
    component : UserAccountRegisterView, 
    name : "user_account_reigster"
  },
  {
    path : "/login/",
    component : UserAccountLoginView, 
    name : "user_account_login"
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router