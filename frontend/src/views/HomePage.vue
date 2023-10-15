<template lang="pug">

</template>
<script>
import {ref,onMounted} from "vue";
import axios from "axios";
export default {
  name: "HomePage",
  setup(){
    const totalProvinceData = ref({})
    const getProviceList = async () => {
      let getData = await axios.get('https://provinces.open-api.vn/api/?depth=3')
      return getData.data;
    }
    const provinceDataDivision = () => {

    }
    const setUpData = async () => {
      totalProvinceData.value = await getProviceList()
      provinceDataDivision()
    }
    onMounted(
      setUpData()
    )
    return {
      totalProvinceData
    }
  }
}
</script>
<style scoped lang="sass">

</style>