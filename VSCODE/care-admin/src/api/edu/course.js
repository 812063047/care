import request from '@/utils/request'
const api_name = '/eduservice/educourse'
export default{
    addCourseInfo(courseInfo){
        return request({
            url:`${api_name}/addCourseInfo`,
            method: 'post',
            data:courseInfo
        })
    },
       //根据课程ID查询课程信息
    getCourseInfoById(id){
        return request({
            url:`${api_name}/${id}`,
            method: 'get'
        })
    },
    //修改课程信息
    updateCourseInfo(courseInfoVo){
        return request({
            url:`${api_name}/updateCourseInfo`,
            method:'post',
            data:courseInfoVo
        })
    },
    getCoursePublishVoById(courseId){
        return request({
            url:`${api_name}/coursePublishInfo/${courseId}`,
            method:'get'
        })
    },
    publishCourseById(courseId){
        return request({
            url:`${api_name}/publishCourse/${courseId}`,
            method:'post'
        })
    },
    getCoursePageList(page,limit,eduCourseObj){
        return request({
            url:`${api_name}/${page}/${limit}`,
            method:'post',
            data:eduCourseObj
        })
    },
    deleteByCourseId(id) {
        return request({
            url: `${api_name}/${id}`,
            method: 'delete'
        })
    },
    deleteBatch(ids) {
        return request({
            url: `${api_name}/deleteBatch/${ids}`,
            method: 'delete'
        })
    }

}