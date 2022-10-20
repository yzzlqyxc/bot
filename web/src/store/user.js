import $ from 'jquery'

export const ModuleUser = {
  namespace: false,
  state: {
    id:"",
    username:"",
    photo: "",
    token: "",
    is_login: false,
    registe_ok: true,
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
    },
    updateRegist(state, ok) {
      state.registe_ok = ok
    },
    logout(state) {
      state.id = "";
      state.username = "";
      state.photo = "";
      state.token = "";
      state.is_login = "";
      localStorage.removeItem("jwt_token");
    }
  },
   actions: {
    register(context, data) {
      $.ajax({
        url: "http://localhost:3000/user/account/register/",
        type: "POST",
        data: {
          username : data.username,
          password : data.password,
          confirmedPassword : data.confirmedPassword,
        },
        success(resp) {
          if(resp.response === "success")
            data.success(resp)
          else 
            data.error(resp)
        },
        error(resp) {
          data.error(resp)
        }
      })
    },

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
            localStorage.setItem("jwt_token", resp.token);
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

    getinfo(context, data) {
      $.ajax({
        url: "http://localhost:3000/user/account/info/",
        type: "get",
        headers: {
          Authorization: "Bearer " + context.state.token  
        },
        success(resp) {
          if(resp.response === "success") {
            context.commit("updateUser", {
              ...resp, 
              is_login: true,
            }) 
            data.success(resp)
          }
        },
        error(resp) {
          console.log(resp);
          data.error(resp)
        }
      });
    },
    logout(context) {
      context.commit("logout");
    }
 },
};