import request from '@/utils/request'
const api_name = '/eduservice/eduvideo'
const api_name_vod = 'vodservice/uploadvideo'
export default{
    addVideo(videoInfoVo){
        return request({
            url:`${api_name}`,
            method: 'post',
            data:videoInfoVo
        })
    },
    getVideoInfoById(id){
        return request({
            url:`${api_name}/${id}`,
            method: 'get'
        })
    },
    updateVideoInfo(videoInfoVo) {
        return request({
            url:`${api_name}/updateVideoInfo`,
            method:'post',
            data:videoInfoVo
        })
    },
    deleteVideoInfoById(id) {
        return request({
            url:`${api_name}/${id}`,
            method:'delete'
        })
    },
    removeVideoVodByVideoId(videoId){
        return request({
            url:`${api_name_vod}/${videoId}`,
            method:'delete'
        })
    }
}