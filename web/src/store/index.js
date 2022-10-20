import { createStore } from 'vuex'
import { ModuleUser } from './user';

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
  }
})
