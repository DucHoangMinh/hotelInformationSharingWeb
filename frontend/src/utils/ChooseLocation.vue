<template lang="pug">
v-container.px-0(:class="{ 'd-flex' : isFlex}").position-relative
  v-select.v-col-4.provinceSelect(label="Tỉnh/Thành phố" v-model="areaSelection.province" variant="outlined" density="compact" :items="provinceList").mr-2
  v-select.v-col-4.provinceSelect(label="Quận/Huyện" v-model="areaSelection.distric" variant="outlined" density="compact" :items="districtList" no-data-text="Vui lòng chọn Tỉnh/Thành phố trước").mr-2
  v-select.v-col-4.provinceSelect(label="Phường/Xã" v-model="areaSelection.ward" variant="outlined" density="compact" :items="wardList" no-data-text="Vui lòng chọn Quận/Huyện trước").mr-2
</template>
<script>
import {getCurrentInstance, onMounted, ref, watch} from "vue";
import axios from "axios";

export default {
  name: "ChooseLocation",
  props: {
    isFlex: {
      type: Boolean,
      default: true
    }
  },
  setup(){
    const context = getCurrentInstance();
    const totalProvinceData = ref([])
    const provinceList = ref([])
    const districtList = ref([])
    const wardList = ref([])
    const areaSelection = ref({
      province: null,
      distric: null,
      ward: null
    })
    const areaIndexSelection = ref({
      provinceIndex: null,
      districtIndex: null,
      wardIndex: null
    })
    const getProviceList = async () => {
      let getData = await axios.get('https://provinces.open-api.vn/api/?depth=3')
      return getData.data;
    }
    const provinceDataDivision = () => {
      totalProvinceData.value.forEach((item) => {
        provinceList.value.push(item.name)
      })
    }
    watch(() => areaSelection.value.province, ()=> {
      districtList.value = []
      wardList.value = []
      areaSelection.value.distric = null
      areaSelection.value.ward = null
      totalProvinceData.value.forEach((item,index) => {
        if(item.name == areaSelection.value.province){
          areaIndexSelection.value.provinceIndex = index
          item.districts.forEach(district => {
            districtList.value.push(district.name);
          })
        }
      })
      context.emit('choose-location', areaSelection.value)
    }).bind(this)
    watch(() => areaSelection.value.distric, () =>{
      wardList.value = []
      areaSelection.value.ward = null
      let currentDistrictList = totalProvinceData.value[areaIndexSelection.value.provinceIndex].districts
      currentDistrictList.forEach(item => {
        if(item.name == areaSelection.value.distric){
          item.wards.forEach(item => {
            wardList.value.push(item.name)
          })
        }
      })
      context.emit('choose-location', areaSelection.value)
    }).bind(this)
    watch(() => areaSelection.value.ward, () => {
      context.emit('choose-location', areaSelection.value)
    }).bind(this)
    const setUpData = async () => {
      totalProvinceData.value = await getProviceList()
      provinceDataDivision()
    }
    onMounted(() => {
      setUpData()
    })
    return{
      totalProvinceData,
      provinceList,
      districtList,
      areaSelection,
      wardList
    }
  }
}
</script>
<style lang="sass" scoped>
@import "src/sass/common"
.provinceSelect
  padding: 0
  margin: 0
</style>