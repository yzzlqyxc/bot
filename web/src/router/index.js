import { createRouter, createWebHistory } from 'vue-router'
import CombatIndexView from '../views/combat/CombatIndexView'
import RanklistIndexView from '../views/ranklist/RanklistIndexView'
import RecordIndexView from '../views/record/RecordIndexView'

const routes = [
  {
    path : "/combat/",
    component : CombatIndexView, 
  },
  {
    path : "/ranklist/",
    component : RanklistIndexView,  
  },
  {
    path : "/record/",
    component : RecordIndexView, 
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router