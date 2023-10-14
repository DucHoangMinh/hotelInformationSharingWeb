<template lang="pug">
.login-form.d-flex.flex-column.pa-8
  v-label.text-center.text-h5 Đăng ký
  v-label() Họ và tên
  v-text-field(v-model="registerRequest.fullname" density="compact"  variant="outlined" )
  v-label() Email
  v-text-field(v-model="registerRequest.email" type="email" density="compact"  variant="outlined" @input="validateEmail()")
  v-label.invalid-message(v-if="!validateEmailValue") {{invalidMessage.emailNotValid}}
  v-label() Ngày sinh
  vue-date-picker.mb-4(v-model="registerRequest.dob" :enable-time-picker="false")
  .sex-select.d-flex.justify-space-between
    v-label Giới tính
    p Nam
    input(type="radio" value="true" name="sex" v-model="registerRequest.sex" )
    p Nữ
    input(type="radio" value="false" name="sex" v-model="registerRequest.sex" )
  v-label() Số điện thoại
  v-text-field(v-model="registerRequest.phone" type="number" density="compact" variant="outlined" )
  v-label Mật khẩu
  v-text-field(v-model="registerRequest.password" type="password" density="compact" variant="outlined")
  v-label Nhập lại mật khẩu
  v-text-field(v-model="rePassword" type="password" density="compact" variant="outlined")
  v-label.invalid-message(v-if="registerRequest.password !== rePassword" ) {{invalidMessage.passwordNotMatch}}
  .d-flex.justify-center
    v-btn(style="width: 50%" @click="handleClickRegister").register-button Đăng ký
  p.text-center.mt-4 Đã có tài khoản?
    router-link(to="/login") Đăng nhập
</template>
<script>
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import {ref} from "vue";

export default {
  name:"RegisterPage",
  components: {
    VueDatePicker
  },
  setup(){
    const registerRequest = ref({
      fullname: '',
      email: '',
      dob: '',
      sex: true,
      phone: 0,
      password: ''
    })
    const validateEmailValue = ref(true);
    const invalidMessage = ref({
      passwordNotMatch: 'Mật khẩu không trùng khớp',
      emailNotValid: 'Vui lòng nhập một email hợp lệ'
    })
    const rePassword = ref('')
    function validateEmail(){
      let email = registerRequest.value.email.toLowerCase()
      validateEmailValue.value = email.match( /^[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/)
    }
    const handleClickRegister = async () => {
      console.log(registerRequest.value)
    }
    return {
      registerRequest,
      rePassword,
      handleClickRegister,
      invalidMessage,
      validateEmail,
      validateEmailValue
    }
  }
}
</script>
<style lang="sass" scoped>
@import "src/sass/common"
v-label
  opacity: 1
.login-form
  width: 30%
  margin: auto
  margin-top: 100px
  border: 1px solid $matisse-color
  border-radius: 5px
.register-button
  background-color: $lochmara-color
.invalid-message
  color: $danger-color
  opacity: 1
.v-text-field :deep() .v-input__details
  display: none !important
</style>