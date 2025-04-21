import { ElMessage } from 'element-plus'
export const useMessage = (message, type = 'info') => {
  ElMessage({
    type,
    message,
  })
}
export const useMessageError = (message) => {
  ElMessage({
    type: 'error',
    message,
  })
}
export const useMessageSuccess = (message) => {
  ElMessage({
    type: 'success',
    message,
  })
}
export const useMessageInfo = (message) => {
  ElMessage({
    type: 'info',
    message,
  })
}
