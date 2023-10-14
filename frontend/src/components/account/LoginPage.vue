<script>
import {ref} from "vue";

export default {
  name: "LoginPage",
  setup(){
    const loginRequest = ref({
      email: "",
      password: ""
    })

    function handleLogin(){
      alert(loginRequest.value)
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