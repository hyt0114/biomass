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
        <el-input
          v-model="formState.name"
          show-word-limit
          :maxlength="50"
          placeholder="请输入植物名称"
        />
      </el-form-item>
      <el-form-item label="学名" prop="scientificName">
        <el-input
          v-model="formState.scientificName"
          show-word-limit
          :maxlength="50"
          placeholder="请输入植物名称"
        />
      </el-form-item>
      <el-form-item label="计算公式" prop="formula">
        <el-select v-model="formState.formula" placeholder="请选择计算公式">
          <el-option :key="item.key" :value="item.key" v-for="item in calculatorOptions">
            <span>{{ item.key }}</span
            ><span class="text-info ml-4">{{ item.value }}</span>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="木材密度" prop="density">
        <el-input-number
          v-model="formState.density"
          :min="0"
          :max="999999"
          :precision="2"
          placeholder="请输入木材密度"
          style="width: 100%"
        />
      </el-form-item>
      <el-form-item label="含碳率" prop="carbonContentRatio">
        <el-input-number
          :min="0"
          :max="999999"
          :precision="2"
          v-model="formState.carbonContentRatio"
          placeholder="请输入含碳率"
          style="width: 100%"
        />
      </el-form-item>
      <el-form-item label="描述" prop="description" placeholder="请输入描述">
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
import { getMangrove, addMangrove, updateMangrove, getPresetCalculators } from '@/api/mangrove'
const visible = defineModel('visible', { default: false })

const props = defineProps({
  id: {
    type: Number,
    default: null,
  },
})

const emits = defineEmits(['done'])

const formState = reactive({
  id: null,
  name: null,
  formula: null,
  scientificName: null,
  density: null,
  carbonContentRatio: null,
  description: null,
  img: null,
})
const rules = reactive({
  name: [{ required: true, message: '请输入植物名称' }],
  formula: [{ required: true, message: '请选择计算公式' }],
  density: [{ required: true, message: '请输入木材密度' }],
  carbonContentRatio: [{ required: true, message: '请输入含碳率' }],
})
const formRef = ref()
const calculatorOptions = ref([])
const onSubmit = () => {
  formRef.value.validate(async (isValid) => {
    if (isValid) {
      if (!formState.id) {
        await addMangrove({
          ...formState,
        })
      } else {
        await updateMangrove({ ...formState })
      }
      visible.value = false
      emits('done')
    }
  })
}
const initData = async () => {
  if (props.id) {
    const persistData = await getMangrove(props.id)
    Object.assign(formState, persistData)
  }
  calculatorOptions.value = await getPresetCalculators()
}
initData()
</script>
<style lang="scss" scoped>
.ml-4 {
  margin-left: 4px;
}
</style>
