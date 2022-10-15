<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from './components/NavBar.vue';
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap" 
// import $ from 'jquery'

export default {
  components :{
    NavBar
  },
  setup() {
    // $.ajax({
    //   url: "http://localhost:3000/user/account/info/",
    //   type: "get",
    //   headers: {
    //     Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2N2E1OWQyNGY2OWE0NjcwODNlMTJmODlhMjk0MGJkNiIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTY2NTczNjEzMiwiZXhwIjoxNjY2OTQ1NzMyfQ.CFQYfzK8isDAwC61iE8oV-do-g8M6U1qvDTseGNTRz8",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });

    // $.ajax({
    //   url: "http://localhost:3000/user/account/register/",
    //   type: "POST",
    //   data: {
    //     username : "yxcc",
    //     password : "123",
    //     confirmedPassword : "123dfs",
    //   },
    //   success(resp) {
    //     console.log(resp);
    //   },
    //   error(resp) {
    //     console.log(resp);
    //   }
    // });
  }
}
</script>

<style>
body {
  background-image: url("@/assets/background.png");
  background-size: cover;
}
</style>