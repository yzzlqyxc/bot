import $ from 'jquery'

export const ModuleCombat = {
  namespace: false,
  state: {
    combatRecords : "",
  },
  getters: {

  },
  mutations: {
    updateCombat(state, combat) {
      state.combatRecords = combat
    },
  },
  actions: {
    uploadCombat(context, data) {
      $.ajax({
        url: "http://localhost:3000/combat/upload/",
        type: "POST",
        data: {
          winner : data.winner,
          loser : data.loser,
        },
        success(resp) {
          console.log(resp);
        },
        error(resp) {
          console.log(resp);
        }
      });
    },

    getCombat(context) {
      $.ajax({
        url: "http://localhost:3000/combat/getcombat/",
        type: "get",
        headers: {

        },
        success(resp) {
          context.commit("updateCombat", resp);          
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
  },
};