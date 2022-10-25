export const ModulePk = {
  namespace: false,
  state: {
    status : "home",
    opponent : "",
    socket : null,
  },
  getters: {

  },
  mutations: {
    updateStatus(state, status) {
      state.status = status;
    },
    updateSocket(state, socket) {
      state.socket = socket;
    } 
  },
  actions: {
    
  },
};