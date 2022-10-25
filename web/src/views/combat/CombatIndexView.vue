<template>
  <Home v-if="$store.state.pk.status === 'home'"></Home>
  <Fighting v-if="$store.state.pk.status === 'fighting'"></Fighting>
</template>

<script>
import Fighting from '@/views/combat/CombatFighting.vue';
import Home from "@/views/combat/CombatHome.vue"

import store from '@/store';
import { onMounted, onUnmounted } from 'vue';
export default{
  components: {
    Fighting,
    Home,
  },
  setup() {
    const socketUrl = `ws://localhost:3000/websocket/${store.state.user.token}`;

    let socket = null;
    onMounted(()=>{
      socket = new WebSocket(socketUrl);

      socket.onopen = () => {
        console.log("connected!");
        store.commit("updateSocket", socket);
      }

      socket.onmessage = msg => {
        const data = JSON.parse(msg.data);
        console.log(data);
      }

      socket.onclose = () => {
        console.log("disconnected!");
      }
    })

    onUnmounted(() => {
      socket.close();
    })

  }
  
}

</script>

<style scoped>
</style>