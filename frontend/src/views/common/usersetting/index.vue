<template lang="pug">
Navigation
v-container.user-setting.d-flex
  vertical-menu-list.mr-8(
    :items="menuList"
    @to-other-sale="toOtherScreen"
  )
  .main-screen
    userinformation(v-if="currScreen === 'userinformation'")
    favorite-list(v-if="currScreen === 'favoritelist'")

</template>
<script>
import Navigation from "@/utils/Navigation.vue";
import {ref} from "vue";
import VerticalMenuList from "@/utils/VerticalMenuList.vue";
import Userinformation from "@/views/common/usersetting/UserInformation.vue";
import FavoriteList from "@/views/common/usersetting/FavoriteList.vue";

export default {
  name: 'UserSetting',
  components: {FavoriteList, Userinformation, VerticalMenuList, Navigation},
  setup(){
    const currScreen = ref('userinformation')
    const menuList = ref([
      {
        name: 'Thông tin tài khoản',
        code: 'userinformation'
      },{
        name: 'Danh sách yêu thích',
        code: 'favoritelist'
      }
    ])
    const toOtherScreen = (screen) => {
      currScreen.value = screen
      console.log(screen)
    }
    return{
      menuList,
      currScreen,
      toOtherScreen
    }
  }
}
</script>
<style scoped lang="sass">
.user-setting
  padding-top: 125px
  width: 80%
.main-screen
  flex: 1
  border: 1px solid #ccc
  border-radius: 4px
userinformation
  width: 100%
</style>