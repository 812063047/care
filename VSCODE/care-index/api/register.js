import request from '@/utils/request'
const api_name_sms = '/smsservice/smsapi'
const api_name_ucen = '/ucenterservice/ucentermember'
export default {
    //根据手机号码发送短信
    getMobile(mobile) {
    return request({
        url: `${api_name_sms}/send/${mobile}`,
        method: 'get'
    })
    },
    //用户注册
    submitRegister(formItem) {
    return request({
        url: `${api_name_ucen}/register`,
        method: 'post',
        data: formItem
    })
    }
  }