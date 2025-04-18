<template>
  <AppLayout>
    <template #header>
      <SystemHeader />
    </template>
    <template #sider>
      <el-scrollbar>
        <SiderbarMenu />
      </el-scrollbar>
    </template>
    <template #main>
      <RouterView />
    </template>
  </AppLayout>
</template>
<script setup>
import AppLayout from '@/components/layout/AppLayout.vue'
import SiderbarMenu from '@/components/common/SiderbarMenu.vue'
import SystemHeader from '@/components/common/SystemHeader.vue'
</script>
