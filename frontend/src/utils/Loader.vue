<template lang="pug">
.loader
  v-overlay.position-relative(v-model="overlay" close-on-content-click="false" scroll-strategy="none" persistent)
    v-progress-circular.loader-circle(size="70" width="6" indeterminate color="primary")
</template>
<script>
import {ref} from "vue";

export default {
  name: "Loader",
  setup(){
    const overlay = ref(true)
    return {
      overlay
    }
  }
}
</script>
<style lang="sass">
.loader-circle
  margin: auto
  position: absolute
  top: -500px
  left: 928px
</style>