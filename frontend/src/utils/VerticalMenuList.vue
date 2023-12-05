<template lang="pug">
.vertical-menu-list.d-flex.flex-column
  .vertical-menu-item.px-8.py-2(@click="toOtherScreen(item.code)" v-for="item in props.items")
    label.vertical-menu-name {{item.name}}
</template>
<script>
export default {
  name: "VerticalMenuList",
  props: {
    items: {
      type: Array,
      required: true
    }
  },
  setup(props, {emit}){
    const toOtherScreen = (screen) => {
      emit('to-other-sale', screen)
    }
    return {
      props,
      toOtherScreen
    }
  }
}
</script>
<style scoped lang="sass">
@import "/src/sass/common"
.vertical-menu-list
  background-color: #ccc
  max-width: 240px
  border-radius: 4px
.vertical-menu-item:hover .vertical-menu-name
  transform: translateX(-5px)
  cursor: pointer
.vertical-menu-name
  color: $matisse-color
</style>