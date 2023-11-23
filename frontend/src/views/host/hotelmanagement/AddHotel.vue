<template lang="pug">
.v-container
  loader(v-if="loading")
  #input-form(style="width: 60%; margin: auto")
    .form-title.text-center.py-8
      v-label.text-h5 Nhập thông tin khách sạn của bạn
    .d-flex.align-center
      v-label.v-col-2 Tên khách sạn
      v-text-field(variant="outlined" density="compact" v-model="hotel.hotelname")
    .d-flex.align-center.choose-location
      v-label.v-col-2 Địa chỉ
      choose-location(
        @choose-location="chooseHotelLocation"
      )
    .d-flex.align-center.pb-4
      v-label.v-col-2 Địa chỉ cụ thể
      v-text-field(variant="outlined" density="compact" v-model="hotel.detailLocation")
    .d-flex.pb-4
      .d-flex.align-center.v-col-6.no-padding
        v-label.v-col-4 Giờ mở cửa
        v-text-field(variant="outlined" density="compact" type="time" v-model="hotel.opentime")
      .d-flex.align-center.v-col-6.no-padding
        v-label.v-col-4 Giờ đóng cửa
        v-text-field(variant="outlined" density="compact" type="time" v-model="hotel.closetime")
    .d-flex.align-center.pb-4
      v-label.v-col-2  Giá theo giờ
      v-text-field(variant="outlined" density="compact" type="number" v-model="hotel.priceByHour")
    .d-flex.align-center.pb-4
      v-label.v-col-2  Giá theo ngày
      v-text-field(variant="outlined" density="compact" type="number" v-model="hotel.priceByDay")
    .d-flex.align-center.pb-4
      v-label.v-col-2  Giá qua đêm
      v-text-field(variant="outlined" density="compact" type="number" v-model="hotel.priceOverNight")
    .d-flex.align-center.pb-4
      v-label.v-col-2  Mã số kinh doanh
      v-text-field(variant="outlined" density="compact" type="text" v-model="hotel.businessCode")
    .d-flex.align-start.pb-4
      v-label.v-col-2  Các tiện nghi khác
      v-textarea(variant="outlined" density="compact" rows="2" v-model="hotel.convenience")
    .d-flex.pb-4.align-center
      v-label.v-col-3 Tải lên 3 ảnh của khách sạn
      input(id="image-upload" type="file" accept="image/jpeg" @change="handleUploadImage")
      div.image-show-wrapper.d-flex
        div(v-for="(item,index) in imageUploadLinkList").pa-2.position-relative
          img(:src="item" style="width: 200px")
          font-awesome-icon.remove-img-button(@click="() => handleRemoveImage(index)" icon="fa-regular fa-circle-xmark")
    .post-button.text-center
      v-btn.post-btn(@click="confirmAddHotel")
        span Đăng thông tin
  confirm-dialog(
    v-if="confirmDialog"
    :message="confirmDialogMessage"
    @cancel="handleCancelDialog"
    @confirm="handleConfirmDialog"
  )
</template>
<script>
import {ref} from "vue";
import ChooseLocation from "@/utils/ChooseLocation.vue";
import ConfirmDialog from "@/utils/ConfirmDialog.vue";
import store from "@/store";
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import firebaseService from "@/services/firebase";
import api from "@/services/api";
import Loader from "@/utils/Loader.vue";
export default {
  name :"AddHotel",
  components: {Loader, FontAwesomeIcon, ConfirmDialog, ChooseLocation},
  setup(props,{emit}){
    const loading = ref(false)
    const confirmDialogMessage = ref('');
    const confirmDialog = ref(false)
    let hotel = ref({
      hotelname : '',
      province: '',
      district: '',
      ward: '',
      detailLocation: '',
      opentime: '08:00',
      closetime: '22:00',
      priceByHour: 0,
      priceByDay: 0,
      priceOverNight: 0,
      businessCode: 0,
      convenience: '',
      imageLinks: '_'
    })
    const imageUploadLinkList = ref([])
    const imageUpLoadList = ref([])
    const handleUploadImage = (e) => {
      let selected = e.target.files[0]
      let objectUrl = URL.createObjectURL(selected)
      imageUploadLinkList.value.push(objectUrl)
      imageUpLoadList.value.push(selected)
    }
    const handleRemoveImage = (index) => {
      imageUploadLinkList.value.splice(index,1);
      imageUpLoadList.value.splice(index,1);
    }
    let validateTotalForm = () => {
      for(let key in hotel.value){
        if(hotel.value[key] === 0 || hotel.value[key] === ''){
          return false
        }
      }
      return true
    }
    let validateEnoughImage = () => {
      if(imageUploadLinkList.value.length !== 3){
        store.dispatch('showSnack',{
          message: 'Vui lòng tải lên đủ 3 ảnh',
          color: 'red'
        })
        return false;
      }
      return true;
    }
    const uploadImageToFirebase = async () => {
      if(validateEnoughImage()){
        imageUploadLinkList.value =[]
        for(let i = 0; i < imageUpLoadList.value.length; i++){
          let data = await firebaseService.upload(imageUpLoadList.value[i])
          imageUploadLinkList.value.push(data)
        }
      }
      console.log(imageUploadLinkList.value)
    }
    const chooseHotelLocation = (location) => {
      hotel.value.province = location.province
      hotel.value.district = location.distric
      hotel.value.ward = location.ward
    }
    const handleCancelDialog = () => {
      confirmDialog.value = false
    }
    const confirmAddHotel = () => {
      if(validateTotalForm()){
        confirmDialogMessage.value = `Bạn có chắc chắn muốn đăng thông tin về khách sạn ${hotel.value.hotelname}?`
        confirmDialog.value = true
      }else {
        store.dispatch('showSnack',{
          message: 'Vui lòng nhập đầy đủ các trường thông tin',
          color: 'red'
        })
      }
    }
    const reformatHotelValue = () => {
      if(hotel.value.opentime.length < 6){
        hotel.value.opentime += ':00'
        hotel.value.closetime += ':00'
      }
      hotel.value.priceOverNight = parseInt(hotel.value.priceOverNight)
      hotel.value.priceByHour = parseInt(hotel.value.priceByHour)
      hotel.value.priceByDay = parseInt(hotel.value.priceByDay)
      hotel.value.businessCode = parseInt(hotel.value.businessCode)
      hotel.value.imageLinks = JSON.stringify(imageUploadLinkList.value)
    }
    const handleConfirmDialog = async () => {
      loading.value = true
      await uploadImageToFirebase()
      reformatHotelValue()
      await api.post('http://localhost:8080/api/v1/hotel/', hotel.value);
      confirmDialog.value = false
      loading.value = false
      hotel.value = {
        hotelname : '',
        province: '',
        district: '',
        ward: '',
        detailLocation: '',
        opentime: '08:00',
        closetime: '22:00',
        priceByHour: 0,
        priceByDay: 0,
        priceOverNight: 0,
        businessCode: 0,
        convenience: '',
        imageLinks: '_'
      }
      emit('save-hotel-success')
    }
    return {
      hotel,
      handleCancelDialog,
      handleConfirmDialog,
      chooseHotelLocation,
      confirmAddHotel,
      confirmDialogMessage,
      confirmDialog,
      handleUploadImage,
      imageUploadLinkList,
      handleRemoveImage,
      imageUpLoadList,
      loading
    }
  }

}
</script>
<style lang="sass" scoped>
@import "/src/sass/common"
.post-btn
  background-color: $matisse-color
  color: white
.remove-img-button
  position: absolute
  top: 0px
  right: -4px
  font-size: 24px
  color: white
  font-weight: bold
  background-color: red
  border-radius: 50%
  cursor: pointer
.remove-img-button:hover
  opacity: 0.7
::v-deep .choose-location .v-container .v-input
  margin: 0px !important
</style>