<template>
  <div>
    <CommonGrid ref="gridRef" :columns="columns" :data-loader="loadUserPage">
      <template #buttons>
        <el-button type="primary" @click="onAddUser">新增</el-button>
      </template>
      <template #action="{ row }">
        <el-button type="primary" link @click="onEditUser(row.id)">编辑</el-button>
        <el-button type="danger" link @click="onDelete(row)">删除</el-button>
      </template>
    </CommonGrid>
    <ModifyUserDialog
      v-model:visible="modifyUserDialogVisible"
      :id="currentEditId"
      @on-done="refresh"
      v-if="modifyUserDialogVisible"
    />
  </div>
</template>
<script setup>
import { nextTick, ref } from 'vue'
import CommonGrid from '@/components/form/CommonGrid.vue'
import { loadUserPage, deleteUser } from '@/api/user'
import ModifyUserDialog from './components/ModifyUserDialog.vue'
import useConfirm from '@/hooks/useConfirm'
import { useMessageSuccess } from '@/hooks/useMessage'
const columns = ref([
  {
    prop: 'username',
    label: '登录名',
    width: 160,
  },
  {
    prop: 'nickname',
    label: '昵称',
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
  {
    slot: 'action',
    label: '操作',
    width: 140,
    fixed: 'right',
  },
])
const gridRef = ref()
const refresh = () => {
  gridRef.value?.loadData(true)
}
const modifyUserDialogVisible = ref(false)
const currentEditId = ref(null)
const onAddUser = () => {
  currentEditId.value = null
  modifyUserDialogVisible.value = true
}
const onEditUser = (id) => {
  currentEditId.value = id
  modifyUserDialogVisible.value = true
}
const onDelete = (data) => {
  useConfirm(`确定要删除用户[${data.username}(${data.nickname})]吗？`).then(async () => {
    await deleteUser(data.id)
    useMessageSuccess('删除成功')
    nextTick(() => {
      refresh()
    })
  })
}
</script>
