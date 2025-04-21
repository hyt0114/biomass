<template>
  <div>
    <CommonGrid :columns="columns" :data-loader="loadUserPage">
      <template #buttons>
        <el-button type="primary" @click="onAddUser">新增</el-button>
      </template>
    </CommonGrid>
    <ModifyUserDialog
      v-model:visible="modifyUserDialogVisible"
      :id="currentEditId"
      v-if="modifyUserDialogVisible"
    />
  </div>
</template>
<script setup>
import { ref } from 'vue'
import CommonGrid from '@/components/form/CommonGrid.vue'
import { loadUserPage } from '@/api/user'
import ModifyUserDialog from './components/ModifyUserDialog.vue'
const columns = ref([
  {
    prop: 'id',
    label: 'id',
    width: 100,
  },
  {
    prop: 'nickname',
    label: '昵称',
    width: 160,
  },
  {
    prop: 'username',
    label: '登录名',
    width: 160,
  },
  {
    prop: 'mobile',
    label: '手机号',
    width: 180,
  },
  {
    prop: 'email',
    label: '邮箱',
    minWidth: 180,
  },
])
const modifyUserDialogVisible = ref(false)
const currentEditId = ref(null)
const onAddUser = () => {
  modifyUserDialogVisible.value = true
}
</script>
