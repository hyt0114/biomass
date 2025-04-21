<template>
  <el-dialog v-model="visible" title="添加" width="580px">
    <el-form
      ref="formRef"
      :model="formState"
      :rules="rules"
      label-width="120px"
      class="common-form"
    >
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="formState.username"
          show-word-limit
          :maxlength="50"
          placeholder="请输入用户名，只能是英文字母"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input
          v-model="formState.mobile"
          show-word-limit
          :maxlength="11"
          placeholder="请输入手机号"
        />
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input
          v-model="formState.nickname"
          show-word-limit
          :maxlength="50"
          placeholder="请输入昵称"
        />
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName">
        <el-input
          v-model="formState.realName"
          show-word-limit
          :maxlength="50"
          placeholder="请输入真实姓名"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="formState.email"
          show-word-limit
          :maxlength="50"
          placeholder="请输入邮箱"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button type="default" @click="visible = false">取消</el-button>
      <el-button type="primary" @click="onSubmit">确定</el-button>
    </template>
  </el-dialog>
</template>
<script setup>
import { reactive, ref } from 'vue'
import { addUser, updateUser, loadUserInfo } from '@/api/user'
import { useMessageSuccess } from '@/hooks/useMessage'

const visible = defineModel('visible', { default: false })

const props = defineProps({
  id: {
    type: Number,
    default: null,
  },
})

const emits = defineEmits(['on-done'])

const formRef = ref()
const formState = reactive({
  username: null,
  mobile: null,
  nickname: null,
  realName: null,
  email: null,
})
const rules = reactive({
  username: [
    {
      required: true,
      validator: (rule, value, cb) => {
        const pattern = /^[a-zA-Z]+$/
        if (!value) {
          cb(new Error('请输入用户名'))
        } else if (!pattern.test(value)) {
          cb(new Error('用户名只能是英文字母'))
        } else {
          cb()
        }
      },
    },
  ],
  mobile: [
    {
      required: true,
      validator: (rule, value, cb) => {
        const pattern = /^(?:(?:\+|00)86)?1[3-9]\d{9}$/
        if (!value || !pattern.test(value)) {
          cb(new Error('请输入有效手机号码'))
        } else {
          cb()
        }
      },
      trigger: 'blur',
    },
  ],
  nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
})

const onSubmit = () => {
  formRef.value.validate(async (isValid) => {
    if (isValid) {
      if (props.id) {
        await updateUser({ ...formState })
      } else {
        await addUser({ ...formState })
      }
      useMessageSuccess('操作成功')
      visible.value = false
      emits('on-done')
    }
  })
}
const initData = async () => {
  if (props.id) {
    const data = await loadUserInfo(props.id)
    Object.assign(formState, data)
  }
}
initData()
</script>
