import request from '@/utils/request'
const api_name = '/eduservice/index'
export default {
      getIndexData() {
        return request({
          url: `${api_name}/getIndexData`,
          method: 'get'
        })
      }
    }