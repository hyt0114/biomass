import axios from 'axios'
import { getToken, clearToken, redirectToLogin } from './access-util'
import { ElMessage } from 'element-plus'

const instance = axios.create({
  baseURL: import.meta.env.VITE_APP_API_BASE_URL,
})
instance.interceptors.request.use(function (config) {
  config.headers['X-Token'] = getToken() || null
  return config
})
instance.interceptors.response.use(
  function (response) {
    if (response.status === 200) {
      return response.data?.data
    } else {
      handleError(response)
    }
  },
  function (error) {
    handleError(error)
  },
)
const handleError = (error) => {
  if (error.status === 401) {
    clearToken()
    redirectToLogin()
  } else {
    ElMessage({
      message: error?.response?.data?.message || '系统异常',
      type: 'error',
    })
  }
}
export default {
  get: (url, params) => {
    return instance.get(url, {
      params,
    })
  },
  post: (url, data) => {
    return instance.post(url, data)
  },
  put: (url, data) => {
    return instance.put(url, data)
  },
  delete: (url, data) => {
    return instance.delete(url, data)
  },
}
