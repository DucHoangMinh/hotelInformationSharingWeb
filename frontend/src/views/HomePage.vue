<template lang="pug">
#header
  .header-block
      .header-filter.d-flex.pa-4
        v-select(label="Province" variant="outlined" density="compact" :items="provinceList").mr-2
        v-select(label="District" variant="outlined" density="compact" :items="['One', 'Two', 'Three']").mr-2
        v-select(label="Ward" variant="outlined" density="compact" :items="['One', 'Two', 'Three']")
#body
  .body-wrapper
    v-container

</template>
<script>
import {ref,onMounted} from "vue";
import axios from "axios";
export default {
  name: "HomePage",
  setup(){
    const totalProvinceData = ref([])
    const provinceList = ref([])
    const districtList = ref([])
    const testItem = ref(['Nam', 'Na', 'N'])
    const getProviceList = async () => {
      let getData = await axios.get('https://provinces.open-api.vn/api/?depth=3')
      return getData.data;
    }
    const provinceDataDivision = () => {
      totalProvinceData.value.forEach((item) => {
        provinceList.value.push(item.name)
        districtList.value.push(item.districts)
      })
    }
    const setUpData = async () => {
      totalProvinceData.value = await getProviceList()
      provinceDataDivision()
    }
    onMounted(() => {
      setUpData()
    }
    )
    return {
      totalProvinceData,
      provinceList,
      districtList,
      testItem
    }
  }
}
</script>
<style scoped lang="sass">
@import "src/sass/common"
.header-block
  background-color: $matisse-color
  width: 100%
  height: 350px
  position: relative
.header-filter
  width: 60%
  position: absolute
  left: 50%
  transform: translateX(-50%)
  top: 325px
  background-color: #ffffff
  border: 1px solid $matisse-color
  border-radius: 10px
v-select
  z-index: 2
</style>