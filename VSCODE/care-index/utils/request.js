import axios from 'axios'
import cookie from 'js-cookie'
import {Message, MessageBox} from 'element-ui'
// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:9000', // api的base_url
  timeout: 20000 // 请求超时时间
})
// request拦截器
service.interceptors.request.use(
  config => {
    if (cookie.get('care_token')) {
      config.headers['token'] = cookie.get('care_token') // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// // response 拦截器
// service.interceptors.response.use(
//   response => {
//     /**
//      * code为非20000是抛错 可结合自己业务进行修改
//      */
//     const res = response.data
//     // debugger
//     if (res.code !== 20000) {
//       Message({
//         message: res.message,
//         type: 'error',
//         duration: 5 * 1000
//       })
//       return Promise.reject('error')
//     } else {
//       return response.data
//     }
//   },
//   error => {
//     console.log('err' + error) // for debug
//     Message({
//       message: error.message,
//       type: 'error',
//       duration: 5 * 1000
//     })
//     return Promise.reject(error)
//   }
// )

export default service