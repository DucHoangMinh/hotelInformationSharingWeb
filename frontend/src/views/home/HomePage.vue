<template lang="pug">
#header
  navigation
  .header-block
    .header-block-content.pt-16
      v-label.text-white.text-h2.mb-6 Tìm kiếm phòng nghỉ dễ dàng
      br
      v-label.text-white.text-h4 Du lịch tự tin, không còn lo về nơi ở...
    .header-filter.d-flex.pa-4.align-center
      v-select.provinceSelect(label="Tỉnh/Thành phố" v-model="areaSelection.province" variant="outlined" density="compact" :items="provinceList").mr-2
      v-select.provinceSelect(label="Quận/Huyện" v-model="areaSelection.distric" variant="outlined" density="compact" :items="districtList" no-data-text="Vui lòng chọn Tỉnh/Thành phố trước").mr-2
      v-select.provinceSelect(label="Phường/Xã" v-model="areaSelection.ward" variant="outlined" density="compact" :items="wardList" no-data-text="Vui lòng chọn Quận/Huyện trước").mr-2
      v-btn.mr-2(variant="text" @click="resetAreaSelection")
        font-awesome-icon.text-h5(icon="fa-solid fa-xmark" )
      v-btn(variant="outlined" size="large" @click="handleSearchClick").filter-search-button Tìm kiếm
#body
  .body-wrapper
    v-container.pt-16
    .hotel-search-result(v-if="searchedHotel.length > 0" )
      sum-hotel-list(
        :list="searchedHotel"
      )
    trending-destination

</template>
<script>
import {ref,onMounted,watch} from "vue";
import axios from "axios";
import Navigation from "@/utils/Navigation.vue";
import api from "@/services/api";
import HotelList from "@/views/host/hotelmanagement/HotelList.vue";
import SumHotelList from "@/utils/SumHotelList.vue";
import mixin from "@/services/mixin";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import TrendingDestination from "@/views/home/TrendingDestination.vue";
export default {
  name: "HomePage",
  components: {TrendingDestination, FontAwesomeIcon, SumHotelList, HotelList, Navigation},
  setup(){
    const searchedHotel = ref([])
    const totalProvinceData = ref([])
    const provinceList = ref([])
    const districtList = ref([])
    const wardList = ref([])
    //Ghi lại giá trị các lựa chọn tỉnh/huyện/xã trong cái select
    const areaSelection = ref({
      province: "",
      distric: "",
      ward: ""
    })
    //Ghi lại index của các tỉnh, huyện, xã đã chọn trong cái mảng tổng
    const areaIndexSelection = ref({
      provinceIndex: null,
      districtIndex: null,
      wardIndex: null
    })
    const resetAreaSelection = () => {
      areaSelection.value = {
        province: "",
        distric: "",
        ward: ""
      }
      areaIndexSelection.value = {
        provinceIndex: null,
        districtIndex: null,
        wardIndex: null
      }
      searchedHotel.value = []
    }
    const getProviceList = async () => {
      let getData = await axios.get('https://provinces.open-api.vn/api/?depth=3')
      return getData.data;
    }
    const provinceDataDivision = () => {
      totalProvinceData.value.forEach((item) => {
        provinceList.value.push(item.name)
      })
    }
    const testFunctions = () => {
      console.log("Test okay")
    }
    watch(() => areaSelection.value.province, ()=> {
      districtList.value = []
      wardList.value = []
      areaSelection.value.distric = ""
      areaSelection.value.ward = ""
      totalProvinceData.value.forEach((item,index) => {
        if(item.name == areaSelection.value.province){
          areaIndexSelection.value.provinceIndex = index
          item.districts.forEach(district => {
            districtList.value.push(district.name);
          })
        }
      })
    })
    watch(() => areaSelection.value.distric, () =>{
      wardList.value = []
      areaSelection.value.ward = ""
      let currentDistrictList = totalProvinceData.value[areaIndexSelection.value.provinceIndex].districts
      currentDistrictList.forEach(item => {
        if(item.name == areaSelection.value.distric){
          item.wards.forEach(item => {
            wardList.value.push(item.name)
          })
        }
      })
    })
    const handleSearchClick = async () => {
      try{
        const { data }= await api.get(`api/v1/hotel?province=${areaSelection.value.province}&district=${areaSelection.value.distric}&ward=${areaSelection.value.ward}`)
        searchedHotel.value = data
        searchedHotel.value.forEach((item, index) => {
          searchedHotel.value[index].imageLinks = mixin.formatLinkLists(item.imageLinks)
        })
      }
      catch (error){
        console.log(error)
      }
    }
    const setUpData = async () => {
      totalProvinceData.value = await getProviceList()
      provinceDataDivision()
    }
    onMounted(() => {
      setUpData()
    },
    )
    return {
      testFunctions,
      totalProvinceData,
      provinceList,
      districtList,
      areaSelection,
      wardList,
      handleSearchClick,
      searchedHotel,
      resetAreaSelection
    }
  }
}
</script>
<style scoped lang="sass">
@import "../../sass/common"
.header-block
  background-color: $matisse-color
  width: 100%
  height: 300px
  position: relative
  margin-top: $navigation-height
.header-filter
  width: 80%
  position: absolute
  left: 50%
  transform: translateX(-50%)
  top: 275px
  background-color: #ffffff
  border: 1px solid $matisse-color
  border-radius: 10px
v-select
  z-index: 2
.filter-search-button
  background-color: $matisse-color
  color: #fff
.provinceSelect
  max-width:30%
.header-block-content
  width: 80%
  margin: auto
  height: 100%
  border-top: 1px solid #fff
.hotel-search-result
  width: 80%
  margin: auto
  max-height: 400px
  overflow-y: scroll
  border: 1px solid $matisse-color
  border-radius: 4px
::v-deep .destination-wrapper
  width: 81%
  margin: auto
  margin-bottom: 40px
</style>