<template>
  <div class="system-header">
    <div class="system-name">红树林生态系统植物群落碳汇测量软件</div>
    <el-icon :size="28" class="quit-button cursor" @click="logout" title="退出登录"
      ><SwitchButton
    /></el-icon>
  </div>
</template>
<script setup>
import { clearToken } from '@/common/access-util'
import useConfirm from '@/hooks/useConfirm'
import { useRouter } from 'vue-router'
const router = useRouter()
const logout = () => {
  useConfirm('确定要退出登录吗？', '请确认').then(() => {
    clearToken()
    router.replace({ name: 'login' })
  })
}
</script>
<style lang="scss" scoped>
.system-header {
  background-color: #545c64;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  padding: 0 24px;
  .system-name {
    font-size: 30px;
    height: 100%;
    display: flex;
    align-items: center;
    color: #fff;
    font-weight: bold;
  }
  .quit-button {
    color: #fff;
    margin-left: auto;
    &:hover {
      color: #f56c6c;
    }
  }
}
</style>
