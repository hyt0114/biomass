<template>
  <el-container class="app-layout">
    <el-header style="--el-header-padding: 0">
      <slot name="header"></slot>
    </el-header>
    <el-container class="main-conatiner">
      <el-aside :width="props.siderWidth">
        <slot name="sider"></slot>
      </el-aside>
      <el-main class="height-full">
        <slot name="main"></slot>
      </el-main>
    </el-container>
  </el-container>
</template>
<script setup lang="ts">
const props = defineProps({
  siderWidth: {
    type: String,
    default: '280px',
  },
})
</script>
<style lang="scss" scoped>
.app-layout {
  width: 100vw;
  height: 100vh;
  min-width: 1200px;
  min-height: 700px;
  .main-conatiner {
    height: calc(100% - 60px);
  }
}
</style>
