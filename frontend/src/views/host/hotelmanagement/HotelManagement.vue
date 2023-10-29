<template lang="pug">
navigation
v-container(style="margin-top: 65px")
  .tabs.d-flex.justify-lg-space-between.flex-column
    v-tabs(v-model="tabs" style="width: 100%" align-tabs="center" )
      v-tab.v-col-6
        p Danh sách khách sạn của bạn
      v-tab.v-col-6()
        p Đăng một khách sạn mới
    v-window(v-model="tabs")
      v-window-item
      v-window-item
        add-hotel

</template>
<script>
import Navigation from "@/utils/Navigation.vue";
import {useRoute} from "vue-router";
import {onMounted, ref} from "vue";
import AddHotel from "@/views/host/hotelmanagement/AddHotel.vue";

export default {
  name: "Hotel Management",
  components: {AddHotel, Navigation},
  setup(){
    const route = useRoute()
    const hostId = ref(null)
    const tabs = ref(null)
    const init = () => {
      hostId.value = route.query.hostId
    }
    onMounted(init)
    return {
      tabs
    }
  }
}
</script>
<style lang="sass">
@import "/src/sass/common"
</style>