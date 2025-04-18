import { post } from '@/common/http'

export function loadUserPage() {
  return post('/sys/user/page')
}
