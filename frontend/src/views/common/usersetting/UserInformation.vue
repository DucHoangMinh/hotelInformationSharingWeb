<template lang="pug">
v-container
  .d-flex.mb-4.align-center.justify-lg-space-between
    label.text-h5.text-center Thông tin tài khoản
    .d-flex.justify-center
      v-btn.ma-2(variant="outlined" @click="changeInforStatus = true") Chỉnh sửa thông tin
      v-btn.ma-2(variant="outlined") Đổi mật khẩu
  .d-flex
    label.v-col-2 Họ và tên
    v-text-field(:disabled="!changeInforStatus" density="compact" variant="outlined")
  .d-flex
    label.v-col-2 Email
    v-text-field(:disabled="!changeInforStatus" density="compact" variant="outlined")
  .d-flex
    v-label.v-col-2() Ngày sinh
    vue-date-picker(:disabled="!changeInforStatus" :enable-time-picker="false")
  .d-flex
    v-label.v-col-2 Giới tính
    v-label.pa-2 Nam
    input(type="radio" value="true" name="sex")
    v-label.pa-2 Nữ
    input(type="radio" value="false" name="sex")
  .d-flex
    v-label.v-col-2() Số điện thoại
    v-text-field(:disabled="!changeInforStatus" type="number" density="compact" variant="outlined")
  .form-action.d-flex.justify-center(v-if="changeInforStatus")
    v-btn.ma-2(variant="outlined" ) Lưu
    v-btn.ma-2(@click="changeInforStatus = false" variant="outlined" ) Hủy
</template>
<script>
import VueDatePicker from '@vuepic/vue-datepicker';
import {ref} from "vue";
export default {
  name: "UserInformation",
  components: {
    VueDatePicker
  },
  setup(){
    const changeInforStatus = ref(true)
    return {
      changeInforStatus
    }
  }
}
</script>
<style scoped lang="sass">

</style>