import request from '@/utils/request'
const api_name_ucen = '/ucenterservice/ucentermember'
export default {
     //登录
    submitLogin(userInfo) {
    return request({
        url: `${api_name_ucen}/login`,
        method: 'post',
        data: userInfo
    })
    },
    //根据token获取用户信息
    getLoginInfo() {
    return request({
        url: `${api_name_ucen}/getLoginInfo`,
        method: 'get',
    // headers: {'token': cookie.get('guli_token')}
    })
    //headers: {'token': cookie.get('guli_token')} 
    },
  }