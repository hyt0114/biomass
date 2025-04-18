<template>
  <div class="login-container">
    <el-form ref="formRef" :model="formState" :rules="rules" label-width="120px" class="login-form">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="formState.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="formState.password" />
      </el-form-item>
      <div class="login-btn-wrapper">
        <el-button type="primary" block @click="handleLogin">登录</el-button>
      </div>
    </el-form>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue'
import { doLogin } from '@/api/login'
import { setToken } from '@/common/access-util'
import { useRouter } from 'vue-router'

const router = useRouter()

const formRef = ref()
const formState = reactive({
  username: 'admin',
  password: 'admin',
})
const rules = reactive({
  username: [{ required: true, message: '请输入用户名' }],
  password: [{ required: true, message: '请输入密码' }],
})
const handleLogin = () => {
  formRef.value.validate(async (isValid) => {
    if (isValid) {
      const token = await doLogin(formState)
      setToken(token)
      router.push({ name: 'home' })
    }
  })
}
</script>
<style lang="scss" scoped>
.login-container {
  width: 100vw;
  height: 100vh;
  position: relative;
  .login-form {
    position: absolute;
    top: 50%;
    right: 20%;
    transform: translateY(-50%);
    border: 1px solid #ccc;
    width: 520px;
    height: 220px;
    padding: 24px;
    border-radius: 8px;
    .login-btn-wrapper {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>
