<template lang="pug">
.navigation.text-center
  .navigation-content.d-flex.align-center.justify-lg-space-between
    .navigation-web-logo.mt-2
      router-link(to="/home")
        img.logo-image(src="../assets/logo.png")
    .navigation-user.d-flex.align-center.position-relative
      .user-avatar.mr-2
        img.user-avatar-image(src="https://www.tenforums.com/attachments/tutorials/146359d1501443008-change-default-account-picture-windows-10-a-user.png")
      .user-name
        v-label.white-color.text-h6(:text="userInfor.fullname")
      .user-option(v-if="userOptionState").black-color.d-flex.flex-column
        v-label(@click="toUserSetting").pa-3.user-option-item
          font-awesome-icon(icon="fa-solid fa-user").mr-2
          |Quản lý tài khoản
        v-label(@click="toHotelManagement()" v-if="userInfor.role == 'ROLE_HOST'" ).pa-3.user-option-item
          font-awesome-icon(icon="fa-solid fa-user").mr-2
          |Quản lý khách sạn
        v-label(@click="logout").pa-3.user-option-item
          font-awesome-icon(icon="fa-solid fa-right-from-bracket").mr-2
          |Đăng xuất

</template>
<script>
import {ref} from "vue";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import storage from "@/services/storage";
import router from "@/routes";
export default {
  name: "Navigation",
  components: {FontAwesomeIcon},
  props: {
    logourl : {
      type: String,
      default: "../assets/userdefault.png"
    }
  },
  setup() {
    const userInfor = ref(JSON.parse(storage.getItem("user_info")))
    const userOptionState = ref(true);
    const logout = async () => {
      await storage.removeItem("accesstoken");
      await storage.removeItem("user_info")
      location.href = "/"
    }
    const toHotelManagement = () => {
      const userId = JSON.parse(storage.getItem("user_info")).id
      console.log(userId)
      router.push(`/host/hotels?hostId=${userId}`)
    }
    const toUserSetting = () => {
      router.push(`/setting`)
    }
    return{
      userOptionState,
      toHotelManagement,
      logout,
      userInfor,
      toUserSetting
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
  cursor: pointer !important
  height: 100%
.navigation-user:hover .user-option
  display: block !important

.username
  cursor: pointer
.user-option
  position: absolute
  top: 60px
  background-color: #fff
  border-radius: 4px
  display: none !important
.user-option-item
  cursor: pointer
  border-radius: 4px
.user-option-item:hover
  background-color: #ccc
.user-avatar-image
  height: 46px
</style>