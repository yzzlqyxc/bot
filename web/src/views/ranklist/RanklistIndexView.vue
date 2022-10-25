<template>
  <ContentField>
    <h2 >排行榜</h2>
    <div class="table-responsive">
      <table class="table table-striped table-sm">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">大名</th>
            <th scope="col">排行分</th>
          </tr>
        </thead>
        <tbody>

          <tr v-for="(temp, idx) in $store.state.ranklist.ranklist" :key="idx" >
            <td>{{idx + 1}}</td>
            <td>{{temp.username}}</td>
            <td>{{temp.rankscore}}</td>
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
    store.dispatch("getRankList");
  }
}

</script>

<style scoped>
h2 {
  text-align: center;
}
th {
  text-align: center;
}
td {
  text-align: center;
}
</style>