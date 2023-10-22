<template>
<v-snackbar
    v-model="snackbar.active"
    :color="snackbar.color"
    :right="true"
    :bottom="true"
    :timeout="snackbar.timer"
>
  {{ snackbar.message }}
  <template #action="{ attrs }">
    <v-btn
        color="white"
        text
        v-bind="attrs"
        @click="snackbar.active = false"
    >
      Close
    </v-btn>
  </template>
</v-snackbar>
</template>
<script>
import {mapState} from "vuex";
export default {
  name : 'SnackBar',
  data: () => ({
    drawer: false
  }),
  computed: {
    ...mapState(["snackbar"])
  }
}
</script>