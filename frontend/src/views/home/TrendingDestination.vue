<template lang="pug">
div
  .destination-wrapper
    label.text-h5.font-weight-bold.pa-3 Những địa điểm được tìm kiếm nhiều nhất
    .destination-body.d-flex.flex-wrap
      .destination.v-col-4.position-relative(v-for="item in HOME_DESTINATION")
        img.destination-image(:src="item.img")
        label.text-h5.destination-name.position-absolute {{item.name}}
</template>
<script>
import {HOME_DESTINATION} from "@/constant";
export default {
  name: "TrendingDestination",
  setup(){
    return{
      HOME_DESTINATION
    }
  }
}
</script>
<style lang="sass" scoped>
.destination
  cursor: pointer
.destination:hover
  transform: translateY(-8px)
  opacity: 0.9
.destination-image
  width: 100%
  border-radius: 4px
.destination-name
  left: 24px
  bottom: 24px
  color: white
  font-weight: bold
</style>