<template lang="pug">
v-container
  .warning-line.d-flex.align-center.justify-center.mt-12
    font-awesome-icon(icon="fa-solid fa-triangle-exclamation" color="red").mr-4.text-h4
    v-label.text-h5 Xin lỗi, bạn không có quyền xem trang này
  .warning-line.d-flex.align-center.justify-center.mt-5
    v-label.text-center Vui lòng&nbsp;
      router-link(to="/login") đăng nhập
      | &nbsp;hoặc kiểm tra lại
</template>
<script>

import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

export default {
  name: "NotPermitted",
  components : {
    FontAwesomeIcon
  }

}
</script>
<style scoped lang="sass">

</style>