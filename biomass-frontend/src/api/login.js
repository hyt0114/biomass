import http from '@/common/http'

export function doLogin(formState) {
  return http.post('/login', formState)
}
