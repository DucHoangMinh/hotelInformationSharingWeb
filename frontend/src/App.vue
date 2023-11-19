<template>
  <v-app>
    <SnackBar></SnackBar>
    <router-view></router-view>
    <Footer></Footer>
  </v-app>
</template>

<script>
import {mapState} from "vuex";
import SnackBar from "@/utils/SnackBar.vue";
import storage from "@/services/storage";
import {onMounted, ref} from "vue";
import {useRouter} from "vue-router";
import Footer from "@/utils/Footer.vue";

export default {
  name: 'App',
  components: {Footer, SnackBar},
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
::v-deep .footer {
  position: absolute;
  bottom: 0;
  right: 0;
  left: 0;
}
</style>
