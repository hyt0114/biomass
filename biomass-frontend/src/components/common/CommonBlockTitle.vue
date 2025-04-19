<template>
  <div class="common-block-title" :class="{ 'clear-top-margin': props.clearTopMargin }">
    <div>{{ props.title }}</div>
    <slot name="append"></slot>
  </div>
</template>
<script setup>
const props = defineProps({
  title: {
    type: String,
    default: '',
  },
  clearTopMargin: {
    type: Boolean,
    default: false,
  },
})
</script>
<style lang="scss" scoped>
.common-block-title {
  height: 30px;
  display: flex;
  align-items: center;
  position: relative;
  color: #111;
  font-size: 18px;
  padding-left: 16px;
  margin: 24px 0;
  &.clear-top-margin {
    margin-top: 0 !important;
  }
  &::before {
    content: '';
    width: 4px;
    height: 60%;
    background-color: #409eff;
    position: absolute;
    top: 50%;
    left: 0;
    transform: translateY(-50%);
  }
}
</style>
