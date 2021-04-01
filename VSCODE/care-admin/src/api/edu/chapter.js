import request from '@/utils/request'
const api_name = '/eduservice/educhapter'
export default{
    getChapterVideById(courseId){
        return request({
            url:`${api_name}/getChapterVideo/${courseId}`,
            method: 'get'
        })
    },
    addChapter(chapter){
        return request({
            url:`${api_name}`,
            method: 'post',
            data:chapter
        })
    },
    getChapterById(chapterId){
        return request({
            url:`${api_name}/${chapterId}`,
            method: 'get'
        })
    },
    updateChapter(chapter) {
        return request({
            url:`${api_name}/updateChapter`,
            method:'post',
            data:chapter
        })
    },
    deleteChapterById(chapterId) {
        return request({
            url:`${api_name}/${chapterId}`,
            method:'delete'
        })
    }
}