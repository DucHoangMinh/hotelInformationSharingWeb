<template lang="pug">
.navigation.text-center
  .navigation-content.d-flex.align-center.justify-lg-space-between
    .navigation-web-logo.mt-2
      router-link(to="/home")
        img.logo-image(src="../assets/logo.png")
    .navigation-user.d-flex.align-center.position-relative
      .user-avatar.mr-2
        img.user-avatar-image(:src="logourl")
      .user-name
        v-label.white-color.text-h6(:text="username")
      .user-option(v-if="userOptionState").black-color.d-flex.flex-column
        v-label().pa-3.user-option-item
          font-awesome-icon(icon="fa-solid fa-user").mr-2
          |Quản lý tài khoản
        v-label().pa-3.user-option-item
          font-awesome-icon(icon="fa-solid fa-right-from-bracket").mr-2
          |Đăng xuất

</template>
<script>
import {ref} from "vue";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

export default {
  name: "Navigation",
  components: {FontAwesomeIcon},
  props: {
    logourl : {
      type: String,
      default: "../assets/userdefault.png"
    },
    username : {
      type: String,
      default: "Tài khoản người dùng"
    }
  },
  setup() {
    const userOptionState = ref(true);
    return{
      userOptionState
    }
  }
}
</script>
<style lang="sass" scoped>
@import "/src/sass/common"
.navigation
  position: fixed
  top: 0
  left: 0
  right: 0
  height: $navigation-height
  z-index: 1
  background-color: $matisse-color
.navigation-content
  width: 80%
  height: 100%
  margin: auto
.logo-image
  height: 40px
.navigation-user
  cursor: pointer
.username
  cursor: pointer
.user-option
  position: absolute
  top: 40px
  background-color: #fff
  border-radius: 4px
.user-option-item
  cursor: pointer
  border-radius: 4px
.user-option-item:hover
  background-color: #ccc
</style>