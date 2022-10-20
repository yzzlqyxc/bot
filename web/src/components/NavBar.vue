<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <router-link class="navbar-brand" :to="{name : 'combat'}">King Of PSS</router-link>
    <div class="collaps navbar-collapse" id = "navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link active" aria-current="page" :to="{name : 'combat'}">对战</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name : 'record'}">对局列表</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name : 'ranklist'}">排行榜</router-link>
        </li>
      </ul>

      <ul class="navbar-nav" v-if="$store.state.user.is_login ">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            {{$store.state.user.username}}
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="#">我的bot</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" @click="logout" href="#">退出</a>
          </div>
        </li>
      </ul>

      <ul class="navbar-nav" v-else>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name : 'user_account_login'}">登录</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name : 'user_account_register'}">注册</router-link>
        </li>
      </ul>

    </div>
  </div>
</nav>
</template>


<script>
import { useRoute } from 'vue-router';
import {computed} from 'vue'
import { useStore } from 'vuex';

export default {
  setup() {
    const store = useStore();
    const route = useRoute();
    let route_name = computed(()=>route.name);

    const logout = ()=>{
      store.dispatch("logout");
    }
    return {
      logout,
      route_name
    };
  }
}

</script>


<style scoped>

</style>