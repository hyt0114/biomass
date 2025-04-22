<template>
  <el-menu :default-active="defaultActive" unique-opened class="siderbar-menu">
    <template v-for="menuItem in menus" :key="menuItem.id">
      <el-sub-menu :index="menuItem.id" v-if="menuItem.children?.length">
        <template #title>
          <el-icon><component :is="menuItem.icon" /></el-icon>
          <span>{{ menuItem.title }}</span>
        </template>
        <el-menu-item
          :index="childMenuItem.id"
          v-for="childMenuItem in menuItem.children"
          :key="childMenuItem.id"
          @click="navigateTo(childMenuItem.router)"
        >
          <el-icon><component :is="childMenuItem.icon" /></el-icon>
          <span>{{ childMenuItem.title }}</span>
        </el-menu-item>
      </el-sub-menu>
      <el-menu-item :index="menuItem.id" @click="navigateTo(menuItem?.router)" v-else>
        <el-icon><component :is="menuItem.icon" /></el-icon>
        <span>{{ menuItem.title }}</span>
      </el-menu-item>
    </template>
  </el-menu>
</template>
<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { menus as menusData } from '@/common/menu'
const defaultActive = ref('')

const router = useRouter()
const route = useRoute()
const menus = ref(menusData)

const navigateTo = (routerName) => {
  router.push({ name: routerName })
}

const findActiveMenuId = (name, menus) => {
  for (const menu of menus) {
    let activeId = null
    if (menu.router === name) {
      activeId = menu.id
      return activeId
    } else if (menu.children?.length) {
      activeId = findActiveMenuId(name, menu.children)
    }
    if (activeId) {
      return activeId
    }
  }
  return null
}

onMounted(() => {
  if (route.name === 'home') {
    router.push({ name: menus.value[0].children[0].router })
    defaultActive.value = findActiveMenuId(menus.value[0].children[0].router, menus.value)
  } else {
    defaultActive.value = findActiveMenuId(route.name, menus.value)
  }
})
</script>
<style lang="scss" scoped>
.siderbar-menu {
  --el-menu-text-color: #fff;
  --el-menu-hover-text-color: #fff;
  --el-menu-bg-color: #545c64;
  --el-menu-hover-bg-color: rgb(67, 74, 80);
  --el-menu-active-color: #ffd04b;
  min-height: calc(100vh - 60px); // 头部高度=60px
}
</style>
