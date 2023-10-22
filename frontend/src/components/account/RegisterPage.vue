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
  .role-select.d-flex.justify-space-between
    v-label Đăng ký với tư cách là
    p Chủ phòng nghỉ
    input(type="radio" value="ROLE_HOST" name="role" v-model="registerRequest.role" )
    p Người tìm kiếm
    input(type="radio" value="ROLE_CLIENT" name="role" v-model="registerRequest.role" )
  v-label() Số điện thoại
  v-text-field(v-model="registerRequest.phone" type="number" density="compact" variant="outlined" @input="validatePhone()")
  v-label.invalid-message(v-if="!validatePhoneValue") {{invalidMessage.phoneNotNull}}
  v-label Mật khẩu
  v-text-field(v-model="registerRequest.password" type="password" density="compact" variant="outlined")
  v-label.invalid-message(v-if="!validatePasswordValue") {{invalidMessage.passwordNotValid}}
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
import mixin from "@/services/mixin";
import api from "@/services/api";
import store from "@/store";

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
      password: '',
      role: 'ROLE_CLIENT'
    })
    const validateEmailValue = ref(true);
    const validatePhoneValue = ref(true)
    const validatePasswordValue = ref(true)
    const invalidMessage = ref({
      passwordNotMatch: 'Mật khẩu không trùng khớp',
      emailNotValid: 'Vui lòng nhập một email hợp lệ',
      phoneNotNull: 'Số điện thoại không hợp lệ hoặc còn thiếu',
      passwordNotValid: 'Mật khẩu phải có 8 ký tự, bao gồm ít nhất 1 chữ cái và 1 số'
    })
    const rePassword = ref('')
    function validateEmail(){
      let email = registerRequest.value.email.toLowerCase()
      validateEmailValue.value = email.match( /^[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/)
    }
    function validatePhone(){
      let phone = registerRequest.value.phone
      validatePhoneValue.value = phone.match(/\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/)
    }
    function validatePassword(){
      let password = registerRequest.value.password
      validatePasswordValue.value = password.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"/)
    }
    async function handleClickRegister(){
      registerRequest.value.dob = mixin.formatDateToYYYYMMDD(registerRequest.value.dob);
      const registerResponse = await api.post('register', registerRequest.value)
      if(registerResponse.status === 'ok'){
        store.dispatch('showSnack', {
          message: 'Đăng ký thành công, bạn sẽ được chuyển về trang đăng nhập sau 5 giây',
          color: "blue",
          timeout: 5000
        })
        setTimeout(() => {
          location.href = "/login"
        }, 5000)
      }
      else {
        store.dispatch('showSnack', {
          message: registerResponse.message,
          color: "red",
          timeout: 5000
        })
      }
      console.log(registerResponse);
    }
    return {
      registerRequest,
      rePassword,
      handleClickRegister,
      invalidMessage,
      validateEmail,
      validatePhone,
      validatePassword,
      validateEmailValue,
      validatePhoneValue,
      validatePasswordValue
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