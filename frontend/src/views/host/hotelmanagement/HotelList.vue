<template lang="pug">
v-container(style="width: 60%")
  .hotel-list.mt-4
    .v-col.d-flex.align-center.pa-4.hotel-list-item.mb-4.position-relative(v-for="(item,index) in hotelList")
      .hotel-image-wrapper.mr-4
        img.hotel-image(:src="item.imageLinks[0]")
      .d-flex.flex-column
        v-label.text-h6.font-weight-bold {{item.hotelname}}
        v-label {{item.ward}} - {{item.district}} - {{item.province}}
        v-label {{item.detailLocation}}
        v-label Giờ mở cửa: {{item.opentime}} Giờ đóng cửa: {{item.closetime}}
        v-label Giá theo giờ: {{item.priceByHour}} Qua đêm: {{item.priceOverNight}} Theo ngày:{{item.priceByDay}}
</template>
<script>
import {onMounted, ref} from "vue";
import api from "@/services/api";
import storage from "@/services/storage"
import mixin from "@/services/mixin";
export default {
  name : "HotelList",
  setup(){
    const hotelList = ref([])
    const userId = JSON.parse(storage.getItem("user_info")).id
    const getHotelList = async () => {
      try {
        let data = await api.get(`/api/v1/hotel/host/${userId}`)
        hotelList.value = data.data
        hotelList.value.forEach((item, index) => {
          hotelList.value[index].imageLinks = mixin.formatLinkLists(item.imageLinks)
        })
      }catch (e){
        console.log(e)
      }
    }
    const init = async () => {
      await getHotelList()
    }
    onMounted(init)
    return {
      hotelList
    }
  }
}
</script>
<style lang="sass">
@import "/src/sass/common"
.hotel-list-item
  border: 2px solid $matisse-color
  border-radius: 4px
.hotel-image
  height: 120px
  border-radius: 4px
</style>