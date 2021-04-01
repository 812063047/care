import request from '@/utils/request'
const api_name_course = '/eduservice/coursefront'
const api_name_edusub = '/eduservice/edusubject'
export default{
    getCourseListPage(current,limit,seachObj) {
        return request({
          url:`${api_name_course}/${current}/${limit}`,
          method: 'post',
          data:seachObj
        })
      },
        // 获取课程二级分类
    getAllSubject() {
        return request({
            url: `${api_name_edusub}`,
            method: 'get'
        })
    },
    getCourseInfo(courseId) {
            return request({
                url: `${api_name_course}/${courseId}`,
                method: 'get'
            })
        }

} 