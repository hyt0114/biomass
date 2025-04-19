<template>
  <div class="calclate-view">
    <el-form
      ref="formRef"
      :model="formState"
      :rules="rules"
      label-width="120px"
      class="form-container height-full"
    >
      <div class="calclate-container">
        <div class="calc-zone border-right">
          <CommonBlockTitle title="T1监测数据" :clear-top-margin="true">
            <template #append>
              <el-button type="primary" plain class="ml-auto">
                <template #icon>
                  <el-icon :size="18"><UploadFilled /></el-icon>
                </template>
                从excel导入
              </el-button>
            </template>
          </CommonBlockTitle>
          <el-form-item prop="firstMonitorDate" label="T1监测日期">
            <el-date-picker
              v-model="formState.firstMonitorDate"
              type="date"
              value-format="YYYY-MM-DD"
              placeholder="选择监测日期"
              class="w-220"
          /></el-form-item>
          <el-form-item prop="firstMonitorArea" label="监测面积">
            <el-input-number
              :min="0"
              :max="999999"
              :precision="2"
              v-model="formState.firstMonitorArea"
              class="w-220"
            >
              <template #suffix><IconSquareMeter class="square-20" /></template> </el-input-number
          ></el-form-item>
          <div
            class="sample-data-zone"
            v-for="(sample, sampleIndex) in formState.firstSamples"
            :key="sampleIndex"
          >
            <div class="sample-title">样方{{ sampleIndex + 1 }}</div>
            <el-form-item
              label="样方面积"
              :prop="`firstSamples[${sampleIndex}].sampleArea`"
              :rules="{ required: true, message: '请输入样方面积', trigger: 'blur' }"
            >
              <el-input-number
                :min="0"
                :max="999999"
                :precision="2"
                class="w-220"
                v-model="sample.sampleArea"
              >
                <template #suffix><IconSquareMeter class="square-20" /></template> </el-input-number
            ></el-form-item>
            <el-form-item label="植株数据">
              <div v-if="sample.plants?.length">
                <div
                  v-for="(plant, plantIndex) in sample.plants"
                  :key="plantIndex"
                  class="flex align-center"
                  :class="{ 'mt-18': plantIndex > 0 }"
                >
                  <el-form-item
                    label=""
                    :prop="`firstSamples[${sampleIndex}].plants[${plantIndex}].id`"
                    :rules="{ required: true, message: '请选择', trigger: 'change' }"
                  >
                    <el-select v-model="plant.id" placeholder="植株物种" class="w-120 mr-12">
                      <el-option
                        v-for="plant in treeList"
                        :key="plant.id"
                        :label="plant.name"
                        :value="plant.id"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item
                    label=""
                    :prop="`firstSamples[${sampleIndex}].plants[${plantIndex}].dbh`"
                    :rules="{ required: true, message: '请输入', trigger: 'blur' }"
                  >
                    <el-input-number
                      :min="0"
                      :max="999999"
                      :precision="2"
                      class="w-170 mr-12"
                      v-model="plant.dbh"
                      placeholder="胸径/基径"
                    >
                      <template #suffix>cm</template>
                    </el-input-number>
                  </el-form-item>
                  <el-form-item
                    label=""
                    :prop="`firstSamples[${sampleIndex}].plants[${plantIndex}].height`"
                    :rules="{ required: true, message: '请输入', trigger: 'blur' }"
                  >
                    <el-input-number
                      :min="0"
                      :max="999999"
                      :precision="2"
                      class="w-170 mr-12"
                      v-model="plant.height"
                      placeholder="树高"
                    >
                      <template #suffix>m</template>
                    </el-input-number>
                  </el-form-item>
                  <el-icon :size="20" @click="addPlant(sample.plants)" class="cursor" title="添加"
                    ><CirclePlus
                  /></el-icon>
                  <el-icon
                    :size="20"
                    @click="removeItem(sample.plants, sampleIndex)"
                    class="cursor ml-12"
                    title="移除"
                    ><Remove
                  /></el-icon>
                </div>
              </div>
              <div v-else class="flex align-center">
                <el-icon :size="20" @click="addPlant(sample.plants)" class="cursor" title="添加"
                  ><CirclePlus
                /></el-icon>
              </div>
            </el-form-item>
            <el-icon
              class="remove-btn cursor"
              :size="20"
              @click="removeItem(formState.firstSamples, sampleIndex)"
              ><CircleCloseFilled
            /></el-icon>
          </div>
          <el-button
            ref="addSampleBtn1stRef"
            type="primary"
            plain
            class="add-sample-btn"
            @click="onAddSampleClick(formState.firstSamples)"
            >添加样方数据</el-button
          >
        </div>
        <div class="calc-zone">
          <CommonBlockTitle title="T2监测数据" :clear-top-margin="true">
            <template #append>
              <el-button type="primary" plain class="ml-auto">
                <template #icon>
                  <el-icon :size="18"><UploadFilled /></el-icon>
                </template>
                从excel导入
              </el-button>
            </template>
          </CommonBlockTitle>
          <el-form-item prop="secondMonitorDate" label="T2监测日期">
            <el-date-picker
              v-model="formState.secondMonitorDate"
              type="date"
              value-format="YYYY-MM-DD"
              placeholder="选择监测日期"
          /></el-form-item>
          <el-form-item prop="secondMonitorArea" label="监测面积">
            <el-input-number
              :min="0"
              :max="999999"
              :precision="2"
              v-model="formState.secondMonitorArea"
              class="w-220"
            >
              <template #suffix><IconSquareMeter class="square-20" /></template> </el-input-number
          ></el-form-item>
          <div
            class="sample-data-zone"
            v-for="(sample, sampleIndex) in formState.secondSamples"
            :key="sampleIndex"
          >
            <div class="sample-title">样方{{ sampleIndex + 1 }}</div>
            <el-form-item
              label="样方面积"
              :prop="`secondSamples[${sampleIndex}].sampleArea`"
              :rules="{ required: true, message: '请输入样方面积', trigger: 'blur' }"
            >
              <el-input-number
                :min="0"
                :max="999999"
                :precision="2"
                class="w-220"
                v-model="sample.sampleArea"
              >
                <template #suffix><IconSquareMeter class="square-20" /></template> </el-input-number
            ></el-form-item>
            <el-form-item label="植株数据">
              <div v-if="sample.plants?.length">
                <div
                  v-for="(plant, plantIndex) in sample.plants"
                  :key="plantIndex"
                  class="flex align-center"
                  :class="{ 'mt-18': plantIndex > 0 }"
                >
                  <el-form-item
                    label=""
                    :prop="`secondSamples[${sampleIndex}].plants[${plantIndex}].id`"
                    :rules="{ required: true, message: '请选择', trigger: 'change' }"
                  >
                    <el-select v-model="plant.id" placeholder="植株物种" class="w-120 mr-12">
                      <el-option
                        v-for="plant in treeList"
                        :key="plant.id"
                        :label="plant.name"
                        :value="plant.id"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item
                    label=""
                    :prop="`secondSamples[${sampleIndex}].plants[${plantIndex}].dbh`"
                    :rules="{ required: true, message: '请输入', trigger: 'blur' }"
                  >
                    <el-input-number
                      :min="0"
                      :max="999999"
                      :precision="2"
                      class="w-170 mr-12"
                      v-model="plant.dbh"
                      placeholder="胸径/基径"
                    >
                      <template #suffix>cm</template>
                    </el-input-number>
                  </el-form-item>
                  <el-form-item
                    label=""
                    :prop="`secondSamples[${sampleIndex}].plants[${plantIndex}].height`"
                    :rules="{ required: true, message: '请输入', trigger: 'blur' }"
                  >
                    <el-input-number
                      :min="0"
                      :max="999999"
                      :precision="2"
                      class="w-170 mr-12"
                      v-model="plant.height"
                      placeholder="树高"
                    >
                      <template #suffix>m</template>
                    </el-input-number>
                  </el-form-item>
                  <el-icon :size="20" @click="addPlant(sample.plants)" class="cursor" title="添加"
                    ><CirclePlus
                  /></el-icon>
                  <el-icon
                    :size="20"
                    @click="removeItem(sample.plants, sampleIndex)"
                    class="cursor ml-12"
                    title="移除"
                    ><Remove
                  /></el-icon>
                </div>
              </div>
              <div v-else class="flex align-center">
                <el-icon :size="20" @click="addPlant(sample.plants)" class="cursor" title="添加"
                  ><CirclePlus
                /></el-icon>
              </div>
            </el-form-item>
            <el-icon
              class="remove-btn cursor"
              :size="20"
              @click="removeItem(formState.secondSamples, sampleIndex)"
              ><CircleCloseFilled
            /></el-icon>
          </div>
          <el-button
            ref="addSampleBtn2ndRef"
            type="primary"
            plain
            class="add-sample-btn"
            @click="onAddSampleClick(formState.secondSamples, true)"
            >添加样方数据</el-button
          >
        </div>
      </div>
    </el-form>
    <div class="btn-block">
      <el-button type="primary" @click="onSubmit" style="width: 50%; height: 40px; font-size: 20px"
        >开始计算</el-button
      >
    </div>
    <CalculateResult v-model:visible="calcResultVisible" :data="calcResult" />
  </div>
</template>
<script setup>
import { nextTick, reactive, ref } from 'vue'
import IconSquareMeter from '@/components/icons/IconSquareMeter.vue'
import { loadTreeCategories, loadTreeList, doCalc } from '@/api/calculator'
import { ElMessage } from 'element-plus'
import CalculateResult from './components/CalculateResult.vue'
import CommonBlockTitle from '@/components/common/CommonBlockTitle.vue'
const formState = reactive({
  firstMonitorDate: null,
  firstMonitorArea: null,
  firstSamples: [],
  secondMonitorDate: '',
  secondMonitorArea: null,
  secondSamples: [],
})
const rules = reactive({
  firstMonitorDate: [{ required: true, message: '请选择T1监测日期' }],
  firstMonitorArea: [{ required: true, message: '请输入监测面积' }],
  secondMonitorDate: [{ required: true, message: '请选择T2监测日期' }],
  secondMonitorArea: [{ required: true, message: '请输入监测面积' }],
})
const formRef = ref()
const addSampleBtn1stRef = ref()
const addSampleBtn2ndRef = ref()
const onAddSampleClick = (list, second) => {
  list.push({
    sampleArea: null,
    plants: [],
  })
  nextTick(() => {
    if (second) {
      addSampleBtn2ndRef.value.$el.scrollIntoView({ behavior: 'smooth' })
    } else {
      addSampleBtn1stRef.value.$el.scrollIntoView({ behavior: 'smooth' })
    }
  })
}

const addPlant = (list) => {
  list.push({
    id: null,
    dbh: null,
    height: null,
  })
}

const removeItem = (list, index) => {
  list.splice(index, 1)
}
const calcResultVisible = ref(false)
const calcResult = reactive({
  firstSampleResults: [],
  secondSampleResults: [],
  firstTotal: 0,
  secondTotal: 0,
  difference: 0,
  firstMonitorDate: '',
  secondMonitorDate: '',
})
const onSubmit = () => {
  formRef.value.validate(async (isValid) => {
    if (isValid) {
      const resp = await doCalc(formState)
      Object.assign(calcResult, resp)
      calcResultVisible.value = true
    } else {
      ElMessage({
        type: 'error',
        message: '数据不完整，请检查',
      })
    }
  })
}

const categoryOptions = ref([])
const treeList = ref([])
const initData = async () => {
  categoryOptions.value = await loadTreeCategories()
  treeList.value = await loadTreeList()
}
initData()
</script>
<style lang="scss" scoped>
$btn-block-height: 66px;
.calclate-view {
  height: 100%;
  .form-container {
    height: calc(100% - $btn-block-height);
  }
  .calclate-container {
    display: flex;
    box-sizing: border-box;
    height: 100%;
    gap: 20px;
    .calc-zone {
      flex: 1;
      padding: 12px;
      overflow-y: auto;
      border-radius: 4px;
      box-shadow: 0 0 8px 0 #e0e0e0;
      .title {
        font-size: 18px;
        font-weight: bold;
        margin-bottom: 24px;
      }
      .add-sample-btn {
        width: 100%;
        margin-top: 12px;
      }
      .sample-data-zone {
        border: 1px solid #ccc;
        border-radius: 8px;
        padding: 12px;
        position: relative;
        &:hover {
          background-color: #f9f9f9;
          .remove-btn {
            display: block;
          }
        }
        .remove-btn {
          position: absolute;
          top: -8px;
          right: -8px;
          color: #f56c6c;
          display: none;
          cursor: pointer;
        }
        .sample-title {
          color: #666;
        }
        & + .sample-data-zone {
          margin-top: 12px;
        }
      }
    }
  }
}
.btn-block {
  height: $btn-block-height;
  display: flex;
  justify-content: center;
  align-items: center;
}
.mt-18 {
  margin-top: 18px;
}
.w-120 {
  width: 120px;
}
.w-170 {
  width: 170px;
}
.w-220 {
  width: 220px;
}
</style>
