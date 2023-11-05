<template lang="pug">
v-dialog(width="500" v-model="dialogStatus")
  template(v-slot:default="{isActive}")
    v-card(title="")
      v-card-text {{message}}
      v-card-actions
        v-btn(@click="cancel" text="Hủy")
        v-btn(@click="confirm" text="Xác nhận")
</template>
<script>
import {ref} from "vue";

export default {
  name: "ConfirmDialog",
  props: {
    message: {
      type: String,
      require: true
    },
    onDialog: {
      type: Boolean,
      default: true
    }
  },
  setup(props, {emit}){
    const dialogStatus = ref(props.onDialog);
    const cancel = () => {
      emit('cancel')
    }
    const confirm = () => {
      emit('confirm')
    }
    return {
      dialogStatus,
      cancel,
      confirm
    }
  }
}
</script>
<style lang="sass">

</style>