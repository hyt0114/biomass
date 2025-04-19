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
      if (response.config.responseType === 'blob') {
        // 下载文件
        const contentDisposition = response.headers['content-disposition']
        const pattern = /^attachment; filename=(.*)$/
        const filename = pattern.exec(contentDisposition)[1]
        let link = document.createElement('a')
        link.download = filename
        link.style.display = 'none'
        let href = window.URL.createObjectURL(response.data)
        link.href = href
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
        window.URL.revokeObjectURL(link)
      } else {
        return response.data?.data
      }
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
  download: (url, data) => {
    return instance({
      method: 'post',
      url,
      data,
      responseType: 'blob',
    })
  },
}
