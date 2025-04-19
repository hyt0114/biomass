import { ElMessageBox } from 'element-plus'
const useConfirm = (
  message,
  title = '请确认',
  confirmButtonText = '确定',
  cancelButtonText = '取消',
) => {
  return ElMessageBox.confirm(message, title, {
    confirmButtonText,
    cancelButtonText,
    type: 'warning',
  })
}
export default useConfirm
