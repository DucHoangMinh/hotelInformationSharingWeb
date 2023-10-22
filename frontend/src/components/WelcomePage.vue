<template lang="pug">
.background
  img.background-img(src="../assets/homepage_cover.jpg")
.homepage-task.position-absolute.text-center
  v-label(style="opacity: 1").text-white.text-h1.font-italic.mb-12 Hotel Information Sharing Web
  .d-flex.justify-center
    .login-button.mr-12.pa-3
        router-link(to="/login").white-color.text-h5 Đăng nhập
    .register-button.pa-3
        router-link(to="/register").white-color.text-h5 Đăng ký
</template>

<script>
import storage from "@/services/storage";
import {onMounted} from "vue";

export default {
  name: 'HelloWorld',
  setup(){
    const checkLoginOrNot = () => {
      let token = storage.getItem("accesstoken");
      if(token){
        location.href = "/home";
      }
    }
    onMounted(
        checkLoginOrNot
    )
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="sass" scoped>
@import "src/sass/common"
.background
  width: 100%
  height: 100%
  position: relative
.background-img
  width: 100%
.homepage-task
  width: 100%
  top: 300px
.login-button,
.register-button
  background-color: $matisse-color
  border: 2px solid white
  border-radius: 20px

.login-button:hover,
.register-button:hover
  opacity: 0.7
</style>
