<template>
  <div class="width-full">
    <el-upload
      drag
      :auto-upload="false"
      :limit="1"
      :on-exceed="handleExceed"
      v-model:file-list="uploadFiles"
      :accept="props.accept"
      ref="uploadRef"
      class="width-full"
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">拖动文件到此处或者 <em>点击上传</em></div>
    </el-upload>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import { genFileId } from 'element-plus'
const uploadFiles = defineModel('files', { default: [] })
const props = defineProps({
  accept: {
    type: String,
    default: null,
  },
})

const uploadRef = ref()
const handleExceed = (files) => {
  uploadRef.value.clearFiles()
  const file = files[0]
  file.uid = genFileId()
  uploadRef.value.handleStart(file)
}
</script>
