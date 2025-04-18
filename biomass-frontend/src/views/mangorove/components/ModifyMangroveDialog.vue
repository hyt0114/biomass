<template>
  <el-dialog v-model="visible" title="新增" width="680px">
    <el-form
      ref="formRef"
      :model="formState"
      :rules="rules"
      label-width="120px"
      class="common-form"
    >
      <el-form-item label="植物名称" prop="name">
        <el-input v-model="formState.name" show-word-limit :maxlength="50" />
      </el-form-item>
      <el-form-item label="学名" prop="scientificName">
        <el-input v-model="formState.scientificName" show-word-limit :maxlength="50" />
      </el-form-item>
      <el-form-item label="索引" prop="calcKey">
        <el-input v-model="formState.calcKey" show-word-limit :maxlength="50" />
      </el-form-item>
      <el-form-item label="木材密度" prop="density">
        <el-input v-model="formState.density" />
      </el-form-item>
      <el-form-item label="含碳率" prop="carbonContentRatio">
        <el-input v-model="formState.carbonContentRatio" />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input
          v-model="formState.description"
          type="textarea"
          :rows="5"
          show-word-limit
          :maxlength="1500"
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
import { addMangrove } from '@/api/mangrove'
const visible = defineModel('visible', { default: false })

const emits = defineEmits(['done'])

const formState = reactive({
  id: null,
  name: null,
  calcKey: null,
  scientificName: null,
  density: null,
  carbonContentRatio: null,
  description: null,
  img: null,
})
const rules = reactive({
  name: [{ required: true, message: '请输入植物名称' }],
  calcKey: [{ required: true, message: '请输入索引' }],
})
const formRef = ref()
const onSubmit = () => {
  formRef.value.validate(async (isValid) => {
    if (isValid) {
      if (!formState.id) {
        addMangrove({
          ...formState,
        })
        visible.value = false
        emits('done')
      }
    }
  })
}
</script>
