<template lang="pug">
v-container
  .hotel-list.mt-4
    .v-col.d-flex.align-center.pa-4.hotel-list-item.mb-4.position-relative(v-for="(item,index) in props.list")
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
export default {
  name: "SumHotelList",
  props: {
    list: {
      type: Array,
      require: true
    }
  },
  setup(props){
    return{
      props
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