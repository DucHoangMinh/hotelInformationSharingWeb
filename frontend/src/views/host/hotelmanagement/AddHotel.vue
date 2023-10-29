<template lang="pug">
.v-container
  #input-form
    .d-flex
      v-label Tên khách sạn
      v-text-field(v-model="hotel.hotelname")
    .d-flex
      v-label Địa chỉ
      choose-location(
        @choose-location="chooseHotelLocation"
      )
    .d-flex
      v-label Giờ mở cửa
      v-text-field(type="time" v-model="hotel.opentime")
    .d-flex
      v-label Giờ đóng cửa
      v-text-field(type="time" v-model="hotel.closetime")
    .d-flex
      v-label Giá theo giờ
      v-text-field(type="time" v-model="hotel.pricebyhour")
    .d-flex
      v-label Giá theo ngày
      v-text-field(type="time" v-model="hotel.pricebyday")
    .d-flex
      v-label Giá qua đêm
      v-text-field(type="time" v-model="hotel.priceovernight")
    .d-flex
      v-label Các tiện nghi khác
      v-textarea(v-model="hotel.convenience")

</template>
<script>
import {ref} from "vue";
import ChooseLocation from "@/utils/ChooseLocation.vue";

export default {
  name :"AddHotel",
  components: {ChooseLocation},
  setup(){
    const hotel = ref({
      hotelname : '',
      province: '',
      district: '',
      ward: '',
      opentime: new Date().setHours(8,0,0),
      closetime: new Date().setHours(22,0,0),
      pricebyhour: 0,
      pricebyday: 0,
      priceovernight: 0,
      businesscode: 0,
      convenience: ''
    })
    const chooseHotelLocation = (location) => {
      hotel.value.province = location.province
      hotel.value.district = location.distric
      hotel.value.ward = location.ward
    }
    return {
      hotel,
      chooseHotelLocation
    }
  }

}
</script>
<style lang="sass">

</style>