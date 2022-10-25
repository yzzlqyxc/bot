import $ from 'jquery'

export const ModuleRanklist = {
  namespace: false,
  state: {
    ranklist: "",
  },
  getters: {

  },
  mutations: {
    updateRankList(state, ranklist) {
      state.ranklist = ranklist
    },
  },
  actions: {
    getRankList(context) {
      $.ajax({
        url: "http://localhost:3000/ranklist/",
        type: "get",
        headers : {
        },
        success(resp) {
          context.commit("updateRankList", resp);
        },
        error(resp) {
          console.log(resp);
        }
      });
    },
  },
};