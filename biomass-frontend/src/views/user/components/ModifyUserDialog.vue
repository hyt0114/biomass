<template>
  <el-dialog
    v-model="visible"
    title="添加"
    width="680px"
    :show-close="!loading"
    :close-on-click-modal="!loading"
  >
    <el-form
      ref="formRef"
      :model="formState"
      :rules="rules"
      label-width="120px"
      class="common-form"
    >
      <el-form-item label="用户名" prop="name">
        <el-input
          v-model="formState.name"
          show-word-limit
          :maxlength="50"
          placeholder="请输入用户名"
        />
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script setup>
import { reactive, ref } from 'vue'

const visible = defineModel('visible', { default: false })

const emits = defineEmits(['on-done'])

const formState = reactive({
  name: null,
})
</script>
