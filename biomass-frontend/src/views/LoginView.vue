<template>
  <div class="login-container">
    <el-form ref="formRef" :model="formState" :rules="rules" label-width="0" class="login-form">
      <div class="login-title">欢迎登录</div>
      <el-form-item label="" prop="username">
        <el-input v-model="formState.username" placeholder="用户名" />
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input v-model="formState.password" placeholder="密码" />
      </el-form-item>
      <div class="login-btn-wrapper">
        <el-button
          type="primary"
          block
          @click="handleLogin"
          style="width: 100%; height: 40px; font-size: 18px"
          >登录</el-button
        >
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
  background: url('@/assets/img/bg.jpg');
  background-size: 100% 100%;
  .login-form {
    background-color: #fff;
    position: absolute;
    top: 50%;
    right: 20%;
    transform: translateY(-50%);
    border: 1px solid #ccc;
    width: 400px;
    padding: 32px;
    border-radius: 8px;
    .login-title {
      font-size: 24px;
      text-align: center;
      margin-bottom: 24px;
      color: #111;
    }
    .login-btn-wrapper {
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>
