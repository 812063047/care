import request from '@/utils/request'
const api_name = '/eduservice/teacherfront'
export default{
    getTeacherListPage(current,limit) {
        return request({
          //url: '/eduteacher/pageQueryTeacher/'+current+"/"+limit,
          url:`${api_name}/getTeacherPageList/${current}/${limit}`,
          method: 'post',
        })
      },
      getTeacherInfo(id){
      return request({
        url:`${api_name}/${id}`,
        method:'get'
      })
    },

} 