import http from '@/common/http'

export function loadUserPage() {
  return http.post('/sys/user/page')
}
