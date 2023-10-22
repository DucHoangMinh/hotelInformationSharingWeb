<script>
import {ref} from "vue";
import store from "@/store";
import auth from "@/services/auth";
export default {
  name: "LoginPage",
  setup(){
    const loginRequest = ref({
      email: "",
      password: ""
    })
    async function handleLogin(){
      if(loginRequest.value.email === "" || loginRequest.value.password === ""){
        store.dispatch('showSnack', {
          message: 'Vui lòng nhập đầy đủ email và mật khẩu',
          color: "red",
          timeout: 5000
        })
      }
      else {
        let loginResult = await auth.login(loginRequest.value);
        if(!loginResult){
          store.dispatch('showSnack', {
            message: 'Vui lòng kiểm tra lại thông tin tài khoản và mật khẩu',
            color: "red",
            timeout: 5000
          })
        }
      }
    }
    return {
      loginRequest,
      handleLogin
    }
  }
}
</script>

<template lang="pug">
v-container
  .login-form.d-flex.flex-column.pa-8
    v-label.text-center.text-h5 Đăng nhập
    v-label() Email
    v-text-field(v-model="loginRequest.email" type="email"  variant="outlined" )
    v-label Mật khẩu
    v-text-field(v-model="loginRequest.password" type="password" variant="outlined")
    v-btn(style="width: 50%" @click="handleLogin").login-button Đăng nhập
</template>

<style scoped lang="sass">
@import "src/sass/common"
v-label
  opacity: 1
.login-form
  width: 30%
  margin: auto
  margin-top: 100px
  border: 1px solid $matisse-color
  border-radius: 5px
.login-button
  background-color: $lochmara-color
</style>