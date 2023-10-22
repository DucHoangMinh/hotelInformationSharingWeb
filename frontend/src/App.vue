<template>
  <v-app>
    <SnackBar></SnackBar>
    <router-view></router-view>
  </v-app>
</template>

<script>
import {mapState} from "vuex";
import SnackBar from "@/utils/SnackBar.vue";

export default {
  name: 'App',
  components: {SnackBar},
  computed: {
    ...mapState(["snackbar"])
  }
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
