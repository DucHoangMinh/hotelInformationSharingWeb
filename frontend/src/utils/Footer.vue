<template lang="pug">
.footer(style="background-color: #ccc; height: 40px").d-flex.justify-center.align-center.mt-8
  font-awesome-icon.text-h5(icon="fa-regular fa-copyright")
  v-label &nbsp Copyright belongs to Hoang Minh Duc aka Duck2k3 &nbsp
  font-awesome-icon(icon="fa-regular fa-face-smile" )
</template>
<script>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

export default {
  name: "Footer",
  components: {FontAwesomeIcon}
}
</script>