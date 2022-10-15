import $ from 'jquery'
import { createStore } from 'vuex'

export default createStore({
  namespace: false,
  state: {
    id:"",
    username:"",
    photo: "",
    token: "",
    is_login: false,
  },

  getters: {

  },

  mutations: {
    updateUser(state, user) {
      state.id = user.id;
      state.username = user.username;
      state.photo = user.photo;
      state.is_login = user.is_login;
    },
    updateToken(state, token) {
      state.token = token;
    }
  },

  actions: {
    login(context, data) {
      $.ajax({
        url: "http://localhost:3000/user/account/token/",
        type: "POST",
        data: {
          username : data.username,
          password : data.password,
        },
        success(resp) {
          if(resp.response === "success") {
            context.commit("updateToken", resp.token);
            data.success(resp);
          } else {
            data.error(resp);
          }
        },
        error(resp) {
          data.error(resp);
        }
      });
    }, 

  },

  modules: {

  }
})

