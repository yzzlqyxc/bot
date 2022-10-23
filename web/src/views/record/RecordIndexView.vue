<template>
  <ContentField>
    <h2>最近对局</h2>
    <div class="table-responsive">
      <table class="table table-striped table-sm">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">获胜者</th>
            <th scope="col">失败者</th>
            <th scope="col">对战时间</th>
          </tr>
        </thead>
        <tbody>

          <tr v-for="(temp, idx) in $store.state.combat.combatRecords" :key="idx" >
            <td>{{idx + 1}}</td>
            <td>{{temp.winner}}</td>
            <td>{{temp.loser}}</td>
            <td>{{temp.time}}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </ContentField>
</template>

<script>
import ContentField from '@/components/ontentField.vue'
import store from '@/store';

export default{
  components: { ContentField },

  setup() {
    store.dispatch("getCombat");
  }

}
</script>

<style scoped>
</style>