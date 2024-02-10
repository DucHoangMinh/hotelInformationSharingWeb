<template lang="pug">
v-container.px-0(:class="{ 'd-flex' : isFlex}").position-relative
  v-select.v-col-4.provinceSelect(label="Tỉnh/Thành phố"
    v-model="areaSelection.province"
    variant="outlined"
    density="compact"
    :items="provinceList"
    :disabled="loading.province"
    :loading="loading.province"
    item-title="name").mr-2
  v-select.v-col-4.provinceSelect(label="Quận/Huyện"
    v-model="areaSelection.distric"
    variant="outlined"
    density="compact"
    :items="districtList"
    :disabled="loading.district"
    :loading="loading.district"
    item-title="name"
    no-data-text="Vui lòng chọn Tỉnh/Thành phố trước").mr-2
  v-select.v-col-4.provinceSelect(
    label="Phường/Xã"
    v-model="areaSelection.ward"
    variant="outlined"
    density="compact"
    :disabled="loading.ward"
    :loading="loading.ward"
    :items="wardList"
    item-title="name"
    no-data-text="Vui lòng chọn Quận/Huyện trước").mr-2
</template>
<script>
import {getCurrentInstance, onMounted, ref, watch} from "vue";
import axios from "axios";
import {PROVINCES_API_URL} from "@/constant";

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
    const loading = ref({
      province : false,
      district : false,
      ward : false
    })
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
      loading.value.province = true
      try{
        let getData = await axios.get(`${PROVINCES_API_URL}provinces`)
        loading.value.province = false
        return getData.data
      }catch (e){
        console.log(e)
      }
    }
    const getDistrictList = async (provinceName) => {
      loading.value.district = true
      try {
        let getData = await axios.get(`${PROVINCES_API_URL}districts?province_name=${provinceName}`)
        loading.value.district = false
        return getData.data
      }catch (e){
        console.log(e)
      }
      loading.value.district = false
    }

    const getWardList = async (districtName) => {
      loading.value.ward = true
      try{
        let getData = await axios.get(`${PROVINCES_API_URL}wards?district_name=${districtName}`)
        loading.value.ward = false
        return getData.data
      }catch (e){
        console.log(e)
      }
      loading.value.ward = false
    }

    const provinceDataDivision = () => {
      // totalProvinceData.value.forEach((item) => {
      //   provinceList.value.push(item.name)
      // })
    }
    watch(() => areaSelection.value.province, async ()=> {
      districtList.value = []
      wardList.value = []
      areaSelection.value.distric = null
      areaSelection.value.ward = null

      districtList.value = await getDistrictList(areaSelection.value.province)

      context.emit('choose-location', areaSelection.value)
    }).bind(this)
    watch(() => areaSelection.value.distric, async () =>{
      wardList.value = []
      areaSelection.value.ward = null

      wardList.value = await getWardList(areaSelection.value.distric)

      context.emit('choose-location', areaSelection.value)
    }).bind(this)
    watch(() => areaSelection.value.ward, () => {
      context.emit('choose-location', areaSelection.value)
    }).bind(this)
    const setUpData = async () => {
      provinceList.value = await getProviceList()
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
      wardList,
      areaIndexSelection,
      loading
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