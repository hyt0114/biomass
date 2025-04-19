<template>
  <el-drawer v-model="visible" direction="rtl" title="计算结果" size="1000px">
    <CommonBlockTitle title="红树林碳库增量" :clear-top-margin="true"></CommonBlockTitle>
    <div class="diff-block">
      <div class="form-item-block">
        <div class="label">T1监测日期</div>
        <div class="content">{{ calcResult.firstMonitorDate }}</div>
      </div>
      <div class="form-item-block">
        <div class="label">T1监测碳总量</div>
        <div class="content">
          <span class="text-primary">{{ calcResult.firstTotal.toFixed(2) }}</span
          ><span class="storage-unit">kgC</span>
        </div>
      </div>
      <div class="form-item-block">
        <div class="label">T2监测日期</div>
        <div class="content">{{ calcResult.secondMonitorDate }}</div>
      </div>
      <div class="form-item-block">
        <div class="label">T2监测碳总量</div>
        <div class="content">
          <span class="text-primary">{{ calcResult.secondTotal.toFixed(2) }}</span
          ><span class="storage-unit">kgC</span>
        </div>
      </div>
      <div class="form-item-block">
        <div class="label">碳库增量</div>
        <div class="content">
          <span class="text-success text-bold">{{ calcResult.difference.toFixed(2) }}</span
          ><span class="storage-unit">kgC</span>
        </div>
      </div>
    </div>
    <CommonBlockTitle title="T1监测结果明细"></CommonBlockTitle>
    <div class="grid-view">
      <el-card v-for="(sampleResult, index) in firstSampleResults" :key="index">
        <template #header>
          <div>
            <span>样方{{ index + 1 }}</span>
          </div>
        </template>
        <div class="form-item-block">
          <div class="label">样方面积</div>
          <div class="content">{{ sampleResult.sampleArea.toFixed(2) }}</div>
        </div>
        <div class="form-item-block">
          <div class="label">植株数量</div>
          <div class="content">{{ sampleResult.count }}</div>
        </div>
        <div class="form-item-block">
          <div class="label">植株类型</div>
          <div class="content tags-wrapper">
            <el-tag type="primary" v-for="(item, index) in sampleResult.types" :key="index">{{
              item
            }}</el-tag>
          </div>
        </div>
        <div class="form-item-block">
          <div class="label">总碳储量</div>
          <div class="content">
            {{ sampleResult.storage.toFixed(2) }}<span class="storage-unit">kgC</span>
          </div>
        </div>
        <div class="form-item-block">
          <div class="label">平均碳储量</div>
          <div class="content">
            {{ sampleResult.averageStorage.toFixed(2) }}<span class="storage-unit">kgC</span>
          </div>
        </div>
      </el-card>
    </div>

    <CommonBlockTitle title="T2监测结果明细"></CommonBlockTitle>
    <div class="grid-view">
      <el-card v-for="(sampleResult, index) in secondSampleResults" :key="index">
        <template #header>
          <div>
            <span>样方{{ index + 1 }}</span>
          </div>
        </template>
        <div class="form-item-block">
          <div class="label">样方面积</div>
          <div class="content">{{ sampleResult.sampleArea.toFixed(2) }}</div>
        </div>
        <div class="form-item-block">
          <div class="label">植株数量</div>
          <div class="content">{{ sampleResult.count }}</div>
        </div>
        <div class="form-item-block">
          <div class="label">植株类型</div>
          <div class="content tags-wrapper">
            <el-tag type="primary" v-for="(item, index) in sampleResult.types" :key="index">{{
              item
            }}</el-tag>
          </div>
        </div>
        <div class="form-item-block">
          <div class="label">总碳储量</div>
          <div class="content">
            {{ sampleResult.storage.toFixed(2) }}<span class="storage-unit">kgC</span>
          </div>
        </div>
        <div class="form-item-block">
          <div class="label">平均碳储量</div>
          <div class="content">
            {{ sampleResult.averageStorage.toFixed(2) }}<span class="storage-unit">kgC</span>
          </div>
        </div>
      </el-card>
    </div>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="visible = false">关闭</el-button>
      </div>
    </template>
  </el-drawer>
</template>
<script setup>
import CommonBlockTitle from '@/components/common/CommonBlockTitle.vue'
import { computed } from 'vue'
const visible = defineModel('visible', { default: false })

const props = defineProps({
  data: {
    type: Object,
    default() {
      return {}
    },
  },
})

const calcResult = computed(() => {
  return props.data
})
const firstSampleResults = computed(() => {
  return calcResult.value.firstSampleResults
})
const secondSampleResults = computed(() => {
  return calcResult.value.secondSampleResults
})
</script>
<style lang="scss" scoped>
.grid-view {
  display: grid;
  grid-template-columns: repeat(3, minmax(310px, 1fr));
  gap: 16px;
}
.form-item-block {
  display: flex;
  margin: 4px 0;
  .label {
    color: #666;
    width: 100px;
    text-align: right;
    flex-shrink: 0;
    &::after {
      content: '：';
    }
  }
  .content {
    padding-left: 8px;
  }
}
.tags-wrapper {
  display: flex;
  gap: 4px;
  flex-wrap: wrap;
}
.diff-block {
  .form-item-block {
    font-size: 18px;
    .label {
      width: 140px;
    }
  }
}
.storage-unit {
  padding: 0 6px;
  font-size: 12px;
  height: 24px;
  line-height: 24px;
  border-radius: 4px;
  background-color: #f2f2f2;
  margin-left: 4px;
  color: #999;
}
</style>
