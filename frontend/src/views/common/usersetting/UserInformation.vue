<template lang="pug">
v-container
  .d-flex.mb-4
    label.text-h5.text-center Thông tin tài khoản
    v-btn Thay đổi thông tin
    v-btn Đổi mật khẩu
  .d-flex
    label Họ và tên
    v-text-field
  .d-flex
    label Email
    v-text-field
  .d-flex
    v-label() Ngày sinh
    vue-date-picker(:enable-time-picker="false")
  .d-flex
    v-label Giới tính
    v-label Nam
    input(type="radio" value="true" name="sex")
    v-label Nữ
    input(type="radio" value="false" name="sex")
  .d-flex
    v-label() Số điện thoại
    v-text-field(type="number" density="compact" variant="outlined")
  v-btn Lưu
</template>
<script>
import VueDatePicker from '@vuepic/vue-datepicker';
export default {
  name: "UserInformation",
  components: {
    VueDatePicker
  },
  setup(){

  }
}
</script>
<style scoped lang="sass">

</style>