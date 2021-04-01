import request from '@/utils/request'
const api_name = '/cmsservice/crmbanner'
export default {
      getAllBanner() {
        return request({
          url: `${api_name}/getAllBanner`,
          method: 'get'
        })
      }
    }