<template>
  <div class="homepage">
    <div class="row justify-content-md-center">
      <div class="col-3">
        <div class="username">{{$store.state.user.username}}</div>
        <div class="photo"><img :src="$store.state.user.photo" alt=""></div>
        <div class="photo"><button type="button" @click="change_button" class="btn btn-primary">{{button_text}}</button></div>
      </div>
    </div>
  </div>
</template>

<script>
import store from '@/store';
import { ref } from 'vue';

export default {

  setup() {
    let button_text = ref('开始匹配');
    let change_button = () => {
      if(button_text.value === "开始匹配") {
        button_text.value = "取消匹配";
        store.state.pk.socket.send(JSON.stringify({
          event:"start_matching",
        }));
      }
      else {
        button_text.value = "开始匹配";
        store.state.pk.socket.send(JSON.stringify({
          event:"end_matching",
        }));
      }
    }

    return {
      button_text,
      change_button
    } 
  }
}

</script>

<style>
.homepage{
  margin: auto;
  height: 70vh;
  width: 60vw;
  background-color:slategray;
}

.username{
  margin-top: 10vh;
  text-align : center;
  font-size:40px;
}
.photo{
  
  margin-top: 10vh;
  text-align: center;
}
img{
  border-radius: 50%;
  align-items: center;
  vertical-align:middle;
}
button{
  align-items: center;
}
</style>