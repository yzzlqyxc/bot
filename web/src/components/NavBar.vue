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
      <ul class="navbar-nav">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          yzzlqyxc
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">我的bot</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">退出</a>
        </div>
      </li>
      </ul>
    </div>
  </div>
</nav>
</template>


<script>

</script>


<style scoped>

</style>