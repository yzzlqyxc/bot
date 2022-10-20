<template>
  <ContentField>
    <div class="row justify-content-md-center">
      <div class="col-3">

        <form @submit.prevent="register">
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password"  class="form-label">密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <div class="mb-3">
            <label for="confirmedPassword"  class="form-label">确认密码</label>
            <input v-model="confirmedPassword" type="password" class="form-control" id="confirmPassword" placeholder="请确认密码">
          </div>
          
          <div class = "mb-3"><text style="color: red;"> {{response}} </text></div>
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
    components: { ContentField },
    setup() {
      let username = ref('');
      let password = ref('');
      let confirmedPassword = ref('');
      let response = ref('');
      const store = useStore();

      const register = () => {
        store.dispatch("register", {
          username: username.value,
          password: password.value,
          confirmedPassword : confirmedPassword.value,
          success(resp) {
            console.log(resp);
            response.value = "";
          }, 
          error(resp) {
            console.log(resp.response);
            response.value = resp.response; 
          }
        })
      }
      return {
        username, 
        password, 
        confirmedPassword, 
        response,
        register
      }
    }
  }
</script>

<style scoped>
button {
  width: 100%;
}
</style>