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
        hotel-list(
          v-if="!tabs"
        )
      v-window-item
        add-hotel(
          v-if="tabs"
          @save-hotel-success="toHotelList"
        )

</template>
<script>
import Navigation from "@/utils/Navigation.vue";
import {useRoute} from "vue-router";
import {onMounted, ref} from "vue";
import AddHotel from "@/views/host/hotelmanagement/AddHotel.vue";
import HotelList from "@/views/host/hotelmanagement/HotelList.vue";

export default {
  name: "Hotel Management",
  components: {HotelList, AddHotel, Navigation},
  setup(){
    const route = useRoute()
    const hostId = ref(null)
    const tabs = ref(null)
    const toHotelList = async () => {
      tabs.value = 0
    }
    const init = () => {
      hostId.value = route.query.hostId
    }
    onMounted(init)
    return {
      tabs,
      toHotelList
    }
  }
}
</script>
<style lang="sass">
@import "/src/sass/common"
</style>