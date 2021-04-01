
<template>

  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>


    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">

      <el-step title="填写课程基本信息"/>

      <el-step title="创建课程大纲"/>

      <el-step title="最终发布"/>

    </el-steps>
    <el-button type="text" @click="dialogChapterForm">添加章节</el-button>

        <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
        <el-form :model="chapter" label-width="120px">
            <el-form-item label="章节标题">
                <el-input v-model="chapter.title"/>
            </el-form-item>
            <el-form-item label="章节排序">
                <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
        </div>
    </el-dialog>

    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.free">
            <el-radio :label="true">免费</el-radio>
            <el-radio :label="false">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <!-- TODO -->          
          <el-upload
                  :on-success="handleVodUploadSuccess"
                  :on-remove="handleVodRemove"
                  :before-remove="beforeVodRemove"
                  :on-exceed="handleUploadExceed"
                  :file-list="fileList"
                  :action="BASE_API+'/vodservice/uploadvideo/upload'"
                  :limit="1"
                  class="upload-demo">
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
                <div slot="content">最大支持1G，<br>
                    支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                    GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                    MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                    SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
                <i class="el-icon-question"/>
            </el-tooltip>
            </el-upload>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 章节 -->
    <ul class="chanpterList">
        <li
            v-for="chapter in chapterVideoList"
            :key="chapter.id">
            <p>
                {{ chapter.title }}
                <span class="acts">
                    <el-button type="text" @click="dialogVideoForm(chapter.id)">添加课时</el-button>
                    <el-button type="text" @click="editChapter(chapter.id)">编辑</el-button>
                    <el-button type="text" @click="deleteChapter(chapter.id)">删除</el-button>
                </span>
            </p>
            <!-- 视频 -->
            <ul class="chanpterList videoList">
                <li
                    v-for="video in chapter.children"
                    :key="video.id">
                    <p>{{ video.title }}
                        <span class="acts">
                            <el-button type="text" @click="editVideo(video.id)">编辑</el-button>
                            <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
                        </span>
                    </p>
                </li>
            </ul>
        </li>
    </ul>
     <div slot="footer" class="dialog-footer"> 
      <el-button @click="previous">上一步</el-button>

      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
     </div>

  </div>

</template>

<script>
import chapter from '@/api/edu/chapter'
import video from '@/api/edu/video'
export default {
  inject: ['reload'],
  data() {
    return {

      saveBtnDisabled: false, // 下一步是否禁用
      chapterVideoList:[],
      courseId:'',
      dialogChapterFormVisible:false,
      chapter:{
        title:'',
        sort:''
      },
      saveVideoBtnDisabled: false, // 课时按钮是否禁用
      dialogVideoFormVisible: false, // 是否显示课时表单
      chapterId: '', // 课时所在的章节id
      video: {// 课时对象
        title: '',
        sort: 0,
        free: 0,
        videoSourceId: '',
        videoOriginalName:''//上传名称
      },
      BASE_API:process.env.BASE_API,
      fileList: [],//上传文件列表
    }

  },
  created() {

    this.init()

  },

  methods: {
    init(){
      if(this.$route.params && this.$route.params.id){
        this.courseId = this.$route.params.id;
        this.getChapterVideo();
      }
    },
    handleVodUploadSuccess(response,file,fileList){//上传成功
        this.video.videoSourceId = response.data.videoId;
        this.video.videoOriginalName = file.name;
    },
        //视图上传多于一个视频
    handleUploadExceed(files, fileList) {
      this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },
    beforeVodRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleVodRemove(){
      //调用接口
      video.removeVideoVodByVideoId(this.video.videoSourceId)
      .then(response=>{
        this.$message({
                    type: 'success',
                    message: response.message
                })
        this.video.title = ''// 重置章节标题
        this.video.sort = 0// 重置章节标题
        this.video.videoSourceId = ''
        this.video.videoOriginalName = ''
        this.fileList = []
      })
    },
    previous() {

      this.$router.push({ path: '/educourse/info/'+ this.courseId})

    },

    next() {


      this.$router.push({ path: '/educourse/publish/'+this.courseId })

    },
    dialogChapterForm(){
      this.dialogChapterFormVisible = true;
      this.chapter.title = "";
      this.chapter.sort = 0;
      this.chapter.id = "";
    },
    dialogVideoForm(chapterId){
      this.dialogVideoFormVisible =true;
      this.video.title = "";
      this.video.id = "";
      this.video.sort = 0;
      this.chapterId = chapterId
      this.fileList = [];
    },
    //根据课程id查询章节和小节
    getChapterVideo(){
      //debugger
      chapter.getChapterVideById(this.courseId)
      .then(Response =>{
        this.chapterVideoList = Response.data.chapterVoList;
      })
    },
    saveOrUpdate(){
      debugger
      this.saveBtnDisabled= true;
      if(!this.chapter.id){
       this.saveChapter();
      }else{
        this.updateChapter();
      }
    },
    saveChapter(){
      this.chapter.courseId = this.courseId;
      chapter.addChapter(this.chapter)
      .then(Response =>{
        this.$message({
                    type: 'success',
                    message: '添加章节成功!'
                })
        this.clearData();
        this.reload();
      })
      .catch(Response =>{
        this.$message({
                    type: 'error',
                    message: Response.message
                })
      })
    },
    updateChapter(){
      chapter.updateChapter(this.chapter)
      .then(Response =>{
        this.$message({
                    type: 'success',
                    message: '修改章节成功!'
                })
                //debugger
        this.clearData();
        //this.reload();
      })
      .catch(Response =>{
        this.$message({
                    type: 'error',
                    message: Response.message
                })
      })
    },
    clearData(){
      this.dialogChapterFormVisible = false;//隐藏对话框
      this.getChapterVideo();//刷新列表
      this.chapter.title = "";
      this.chapter.sort = 0;
      this.saveBtnDisabled = false;
    },
    editChapter(id){//编辑课时
      this.dialogChapterFormVisible =true;
      chapter.getChapterById(id)
      .then(Response =>{
        this.chapter = Response.data.eduChapter;
      })
    },
    deleteChapter(id){
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            })
            .then(response =>{
                //debugger
                chapter.deleteChapterById(id)
                .then(response =>{
                    this.$message({
                    type: 'success',
                    message: '删除成功!'
                    })
                    this.getChapterVideo();
                    //this.reload()


                })
                .catch(response =>{
                  debugger
                    if (response === 'cancel') {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                    } else {
                    this.$message({
                        type: 'error',
                        message: '删除失败'
                    })
                 }
             })
          })
    },
    saveOrUpdateVideo() {
      if(!this.video.id){
        this.saveVideo();
      }else{
        this.updateVideo();
      }
    },
    saveVideo(){
      this.video.courseId = this.courseId;
      this.video.chapterId = this.chapterId;
      video.addVideo(this.video)
      .then(Response =>{
        this.$message({
                    type: 'success',
                    message: '添加小节成功!'
                })
        this.clearVideoData();
        //this.reload();
      })
      .catch(Response =>{
        this.$message({
                    type: 'error',
                    message: Response.message
                })
      })
    },
    updateVideo(){
      video.updateVideoInfo(this.video)
      .then(Response =>{
        this.$message({
                    type: 'success',
                    message: '修改小节成功!'
                })        
        this.clearVideoData();
        //this.reload();
      })
      .catch(Response =>{
        this.$message({
                    type: 'error',
                    message: Response.message
                })
      })
    },
    editVideo(id){
      this.dialogVideoFormVisible = true;//打卡编辑对话框
      //回显数据
      video.getVideoInfoById(id)
      .then(Response =>{
        this.video = Response.data.videoInfoVo;
        this.fileList = [{'name': this.video.videoOriginalName}] 
      })
    },
    removeVideo(id){
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            })
            .then(response =>{
                //debugger
                video.deleteVideoInfoById(id)
                .then(response =>{
                    this.$message({
                    type: 'success',
                    message: '删除成功!'
                    })
                    this.getChapterVideo();
                    //this.reload()


                })
                .catch(response =>{
                  debugger
                    if (response === 'cancel') {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                    } else {
                    this.$message({
                        type: 'error',
                        message: '删除失败'
                    })
                 }
             })
          })
    },
    clearVideoData() {
      this.dialogVideoFormVisible = false// 如果保存成功则关闭对话框
      this.getChapterVideo();// 刷新列表
      this.video.title = ''// 重置章节标题
      this.video.sort = 0// 重置章节标题
      this.video.videoSourceId = ''// 重置视频资源id
      this.video.videoOriginalName = ''
      this.saveVideoBtnDisabled = false
      this.fileList = []
    }

  }
}
</script>
<style scoped>
.chanpterList{
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
    float: right;
    font-size: 14px;
}

.videoList{
  padding-left: 50px;
}

.videoList p{

  float: left;

  font-size: 14px;

  margin: 10px 0;

  padding: 10px;

  height: 50px;

  line-height: 30px;

  width: 100%;

  border: 1px dotted #DDD;
}

</style>