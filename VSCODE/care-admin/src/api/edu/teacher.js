import request from '@/utils/request'
const api_name = '/eduservice/eduteacher'
export default{
    getTeacherListPage(current,limit,eduTeacherQuery) {
        return request({
          //url: '/eduteacher/pageQueryTeacher/'+current+"/"+limit,
          url:`${api_name}/${current}/${limit}`,
          method: 'post',
          //eduTeacherQuery条件的对象，后端是RequestBody获取对象
          //data表示把你这个对象转换成json进行传递到接口里面
          data:eduTeacherQuery
        })
      },
    deletedTeacherById(teacherId){
        return request({
          url:`${api_name}/${teacherId}`,
          method:'delete'

        })
    },
    saveTecher(eduTeacher){
      return request({
        url:`${api_name}/AddEduTeacher`,
        method:'post',
        data: eduTeacher
      })
    },
    getTecherById(id){
      return request({
        url:`${api_name}/${id}`,
        method:'get'
      })
    },
    updateTeacherByid(eduTeacher){
      return request({
        url:`${api_name}/updateEduTeacher`,
        method:'post',
        data:eduTeacher
      })
    },
    getAllTeacher(){
      return request({
        url:`${api_name}/findAll`,
        method:'get'
      })
    },
    deleteBatch(ids){
      return request({
        url:`${api_name}/deleteBatch/${ids}`,
        method:'delete'
      })
    }   

} 