<template lang="pug">
v-container
  .d-flex.mb-4.align-center.justify-lg-space-between
    label.text-h5.text-center Thông tin tài khoản
    .d-flex.justify-center
      v-btn.ma-2(variant="outlined" @click="changeInforStatus = true") Chỉnh sửa thông tin
      v-btn.ma-2(variant="outlined" @click="changePasswordDialog = true") Đổi mật khẩu
  .d-flex
    label.v-col-2 Họ và tên
    v-text-field(v-model="userInfor.fullname" :disabled="!changeInforStatus" density="compact" variant="outlined")
  .d-flex
    label.v-col-2 Email
    v-text-field(v-model="userInfor.email" :disabled="!changeInforStatus" density="compact" variant="outlined")
  .d-flex
    v-label.v-col-2() Ngày sinh
    vue-date-picker(v-model="userInfor.dob" :disabled="!changeInforStatus" :enable-time-picker="false")
  .d-flex
    v-label.v-col-2 Giới tính
    v-label.pa-2 Nam
    input(type="radio" value="true" name="sex" v-model="userInfor.sex" :disabled="!changeInforStatus")
    v-label.pa-2 Nữ
    input(type="radio" value="false" name="sex" v-model="userInfor.sex" :disabled="!changeInforStatus")
  .d-flex
    v-label.v-col-2() Số điện thoại
    v-text-field(v-model="userInfor.phone" :disabled="!changeInforStatus" type="number" density="compact" variant="outlined")
  .form-action.d-flex.justify-center(v-if="changeInforStatus")
    v-btn.ma-2(variant="outlined" @click="putUserInfor") Lưu
    v-btn.ma-2(@click="cancelEdit" variant="outlined" ) Hủy
v-dialog(width="500" v-model="changePasswordDialog")
  template(v-slot:default="{ isActive }")
    v-card(title="Thay đổi mật khẩu của bạn")
      .d-flex.flex-column.px-6.py-2
        label.py-2 Nhập mật khẩu hiện tại
        v-text-field(v-model="changePasswordData.oldPassword" :type="oldPasswordTextType ? 'password' : 'text'"  variant="outlined" density="compact")
          font-awesome-icon.password-icon(@click="oldPasswordTextType = !oldPasswordTextType" v-if="!oldPasswordTextType" icon="fa-solid fa-eye")
          font-awesome-icon.password-icon(@click="oldPasswordTextType = !oldPasswordTextType" v-if="oldPasswordTextType" icon="fa-solid fa-eye-slash")
      .d-flex.flex-column.px-6.py-2
        label.py-2 Nhập mật khẩu mới
        label.text-red(v-if="!passwordAvailable" @input="validatePassword") Mật khẩu phải có 8 ký tự, bao gồm ít nhất 1 chữ cái và 1 số
        v-text-field(v-model="changePasswordData.newPassword" :type="passwordTextType ? 'password' : 'text'" variant="outlined" density="compact")
          font-awesome-icon.password-icon(@click="passwordTextType = !passwordTextType" v-if="!passwordTextType" icon="fa-solid fa-eye")
          font-awesome-icon.password-icon(@click="passwordTextType = !passwordTextType" v-if="passwordTextType" icon="fa-solid fa-eye-slash")
      .d-flex.flex-column.px-6.py-2
        label.py-2 Xác nhận mật khẩu mới
        v-text-field(v-model="changePasswordData.reNewPassword" :type="rePasswordTextType ? 'password' : 'text'" variant="outlined" density="compact")
          font-awesome-icon.password-icon(@click="rePasswordTextType = !rePasswordTextType" v-if="!rePasswordTextType" icon="fa-solid fa-eye")
          font-awesome-icon.password-icon(@click="rePasswordTextType = !rePasswordTextType" v-if="rePasswordTextType" icon="fa-solid fa-eye-slash")
      v-card-actions
        v-btn Lưu
        v-btn(@click="changePasswordDialog = false") Hủy
</template>
<script>
import VueDatePicker from '@vuepic/vue-datepicker';
import {onMounted, ref} from "vue";
import api from "@/services/api";
import mixin from "@/services/mixin";
import storage from "@/services/storage";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
export default {
  name: "UserInformation",
  components: {
    FontAwesomeIcon,
    VueDatePicker
  },
  setup(){
    const changeInforStatus = ref(false)
    const changePasswordDialog = ref(false)
    const userInfor = ref({})
    const userId =ref(JSON.parse(storage.getItem('user_info')).id)
    const oldPasswordTextType = ref(true)
    const passwordAvailable = ref(false)
    const passwordTextType = ref(true)
    const rePasswordTextType = ref(true)
    const changePasswordData = ref({
      oldPassword: '',
      newPassword: '',
      reNewPassword: ''
    })
    const getUserInfor = async () => {
      try {
        const { data } = await api.get(`api/v1/user/${userId.value}`)
        userInfor.value = data
      } catch (error){
        mixin.showErrorMessage(error)
      }
    }

    const putUserInfor = async () => {
      try{
        const data = await api.put(`api/v1/user/${userId.value}`, userInfor.value)
        console.log(data)
        mixin.showSuccessMessage(data.message)
        await cancelEdit()
      }catch (error){
        mixin.showErrorMessage(error)
      }
    }

    const validatePassword = () => {
      console.log('hehe')
      passwordAvailable.value =  mixin.password.validate(changePasswordData.value.newPassword)
    }

    const handleChangePasswordRequest = async () => {
      const { data } = api.put(`/v1/user/password/${userId.value}`)
      console.log(data)
    }

    const init = async () => {
      await getUserInfor()
    }

    const cancelEdit = async () => {
      await getUserInfor()
      changeInforStatus.value = false
    }
    onMounted(init)

    return {
      changeInforStatus,
      userInfor,
      userId,
      cancelEdit,
      putUserInfor,
      changePasswordDialog,
      oldPasswordTextType,
      passwordTextType,
      rePasswordTextType,
      handleChangePasswordRequest,
      changePasswordData,
      validatePassword,
      passwordAvailable
    }
  }
}
</script>
<style scoped lang="sass">
::v-deep .v-input__details
  display: none
.password-icon
  cursor: pointer
  position: absolute
  color: #16558f
  right: 8px
  font-size: 18px
  top: 25%
</style>