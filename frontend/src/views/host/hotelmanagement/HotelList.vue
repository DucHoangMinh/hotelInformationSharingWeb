<template lang="pug">
v-container(style="width: 60%")
  sum-hotel-list(
    :list="hotelList"
  )
</template>
<script>
import {onMounted, ref} from "vue";
import api from "@/services/api";
import storage from "@/services/storage"
import mixin from "@/services/mixin";
import SumHotelList from "@/utils/SumHotelList.vue";
export default {
  name : "HotelList",
  components: {SumHotelList},
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
</style>