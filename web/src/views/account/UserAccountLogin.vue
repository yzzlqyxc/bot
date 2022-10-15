<template>
  <ContentField>
    <div class="row justify-content-md-center">
      <div class="col-3">

        <form @submit.prevent="login">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password"  class="form-label">密码</label>
            <input v-model="password" type="passowrd" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <text style="color: red;"> {{response}} </text>
          <button type="submit" class="btn btn-primary">提交</button>
        </form>


      </div>
    </div>
  </ContentField>
</template>

<script>
  import ContentField from '@/components/ontentField.vue'
  import { useStore } from 'vuex'
  import { ref } from 'vue'

  export default{
    components: { 
      ContentField 
    },
    setup() {
      const store = useStore();
      let username = ref('');
      let password = ref('');
      let response = ref('');

      const login = () => {
        console.log(username.value);
        console.log(store);
        store.dispatch("login", {
          username: username.value,
          password: password.value,
          sueccess(resp) {
            console.log(resp);
          },
          error(resp) {
            console.log(resp);
          }

        })
      }

      return { 
        username,
        password,
        response,
        login,
      } 
    }
}
</script>

<style scoped>
button{
  width: 100%;
}
</style>