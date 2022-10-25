import { createStore } from 'vuex'
import { ModuleUser } from './user';
import { ModuleCombat } from './combat';
import { ModuleRanklist } from './ranklist';
import { ModulePk } from './fight';

export default createStore({
  namespace : false,
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user: ModuleUser,
    combat : ModuleCombat,
    ranklist : ModuleRanklist,
    pk : ModulePk,
  }
})
