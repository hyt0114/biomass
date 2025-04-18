<template>
  <el-drawer v-model="visible" direction="rtl" title="计算结果" size="1000px">
    <CommonBlockTitle title="红树林碳库增量" :clear-top-margin="true"></CommonBlockTitle>
    <el-table :data="dataList" border class="common-grid-table">
      <el-table-column
        :prop="column.prop"
        :label="column.label"
        :width="column.width"
        :min-width="column.minWidth"
        :fixed="column.fixed"
        v-for="(column, index) in columns"
        :key="index"
        :class-name="column.extraCss"
      />
    </el-table>
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
          <div class="content">
            <el-tag type="primary" v-for="(item, index) in sampleResult.types" :key="index">{{
              item
            }}</el-tag>
          </div>
        </div>
        <div class="form-item-block">
          <div class="label">总碳储量</div>
          <div class="content">{{ sampleResult.storage.toFixed(2) }}</div>
        </div>
        <div class="form-item-block">
          <div class="label">均碳储量</div>
          <div class="content">{{ sampleResult.averageStorage.toFixed(2) }}</div>
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
          <div class="content">
            <el-tag type="primary" v-for="(item, index) in sampleResult.types" :key="index">{{
              item
            }}</el-tag>
          </div>
        </div>
        <div class="form-item-block">
          <div class="label">总碳储量</div>
          <div class="content">{{ sampleResult.storage.toFixed(2) }}</div>
        </div>
        <div class="form-item-block">
          <div class="label">均碳储量</div>
          <div class="content">{{ sampleResult.averageStorage.toFixed(2) }}</div>
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
import { computed, ref } from 'vue'
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
const columns = ref([
  {
    prop: 'firstMonitorDate',
    label: 'T1监测日期',
    width: 140,
  },
  {
    prop: 'firstTotal',
    label: 'T1碳总量',
    width: 210,
  },
  {
    prop: 'secondMonitorDate',
    label: 'T2监测日期',
    width: 140,
  },
  {
    prop: 'secondTotal',
    label: 'T2碳总量',
    width: 210,
  },
  {
    prop: 'difference',
    label: '增量',
    extraCss: 'text-primary',
    minWidth: 210,
  },
])
const dataList = computed(() => {
  return [
    {
      firstTotal: calcResult.value.firstTotal.toFixed(2),
      secondTotal: calcResult.value.secondTotal.toFixed(2),
      difference: calcResult.value.difference.toFixed(2),
      firstMonitorDate: calcResult.value.firstMonitorDate,
      secondMonitorDate: calcResult.value.secondMonitorDate,
    },
  ]
})
const firstSampleResults = computed(() => {
  return calcResult.value.firstSampleResults
})
const secondSampleResults = computed(() => {
  return calcResult.value.secondSampleResults
})
</script>
<style lang="scss" scoped>
.common-grid-table {
  font-size: 18px;
  :deep(td.text-primary) {
    div.cell {
      color: #409eff;
      font-weight: bold;
    }
  }
}
.grid-view {
  display: grid;
  grid-template-columns: repeat(3, minmax(310px, 1fr));
  gap: 16px;
}
.form-item-block {
  display: flex;
  margin: 4px 0;
  .label {
    color: #999;
    width: 90px;
    text-align: right;
    &::after {
      content: '：';
    }
  }
  .content {
    padding-left: 8px;
  }
}
</style>
