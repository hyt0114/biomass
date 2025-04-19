<template>
  <el-dialog
    v-model="visible"
    title="导入数据"
    width="680px"
    :show-close="!loading"
    :close-on-click-modal="!loading"
  >
    <div>
      <div><el-button type="primary" link @click="onDownload"> 下载导入模板</el-button></div>
      <el-upload
        class="mt-12"
        drag
        :auto-upload="false"
        :limit="1"
        :on-exceed="handleExceed"
        v-model:file-list="uploadFiles"
        accept=".xlsx"
        ref="uploadRef"
      >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">拖动文件到此处或者 <em>点击上传</em></div>
      </el-upload>
    </div>
    <template #footer>
      <el-button type="default" @click="visible = false" v-if="!loading">取消</el-button>
      <el-button type="primary" :loading="loading" @click="onImport">导入</el-button>
    </template>
  </el-dialog>
</template>
<script setup>
import { ref } from 'vue'
import { downloadTemplate, importData } from '@/api/calculator'
import { ElMessage, genFileId } from 'element-plus'

const visible = defineModel('visible', { default: false })

const emits = defineEmits(['on-done'])

const loading = ref(false)
const uploadRef = ref()
const onDownload = async () => {
  await downloadTemplate()
}
const onImport = async () => {
  if (uploadFiles.value?.length) {
    loading.value = true
    const formData = new FormData()
    formData.set('file', uploadFiles.value[0].raw)
    try {
      const importResult = await importData(formData)
      emits('on-done', importResult)
      visible.value = false
      // eslint-disable-next-line no-unused-vars
    } catch (_) {
      // do noting
    }
    loading.value = false
  } else {
    ElMessage({
      type: 'error',
      message: '请选择文件',
    })
  }
}
const uploadFiles = ref([])
const handleExceed = (files) => {
  uploadRef.value.clearFiles()
  const file = files[0]
  file.uid = genFileId()
  uploadRef.value.handleStart(file)
}
</script>
