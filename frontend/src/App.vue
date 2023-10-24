<template>
  <v-app>
    <SnackBar></SnackBar>
    <router-view></router-view>
  </v-app>
</template>

<script>
import {mapState} from "vuex";
import SnackBar from "@/utils/SnackBar.vue";
import storage from "@/services/storage";
import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";

export default {
  name: 'App',
  components: {SnackBar},
  computed: {
    ...mapState(["snackbar"])
  },
  setup(){
    const router = useRouter()
    const noNeedCheckPath = ref(['/', '/login', '/register'])
    const checkLoginOrNot = () => {
      const currentPath = router.currentRoute.value.path;
      console.log(currentPath)
      let needCheckOrNot = noNeedCheckPath.value.find(item => {
        item == currentPath
      })
      if(!needCheckOrNot){
        let token = storage.getItem("accesstoken");
        if(token){
          router.push('/home')
        }
        else{
          router.push('/')
        }
      }
    }
    onMounted(
        checkLoginOrNot
    )
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
