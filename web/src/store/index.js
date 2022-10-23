import { createStore } from 'vuex'
import { ModuleUser } from './user';
import { ModuleCombat } from './combat';

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
  }
})
