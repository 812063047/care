<template>
    <div class="app-container">
        
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">

      <el-step title="填写课程基本信息"/>

      <el-step title="创建课程大纲"/>

      <el-step title="最终发布"/>

    </el-steps>

    <el-form label-width="120px">
    <el-form-item label="课程标题">

    <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>

  </el-form-item>

  <!-- 所属分类 TODO -->

  <!-- 所属分类：级联下拉列表 -->

    <!-- 一级分类 -->
    <el-form-item label="课程类别">

    <el-select

        v-model="courseInfo.subjectParentId"

        placeholder="请选择"
        @change="subjectLevelOneChanged">

        <el-option

        v-for="subject in subjectOneList"

        :key="subject.id"

        :label="subject.title"

        :value="subject.id"/>
    </el-select>
  <!-- 二级分类 -->

    <el-select v-model="courseInfo.subjectId" placeholder="请选择" @change="updateSubjectTwoListData">

    <el-option

    v-for="subject in subjectTwoList"

    :key="subject.id"

    :label="subject.title"

    :value="subject.id"/>

</el-select>
</el-form-item>

  <!-- 课程讲师 TODO -->


<el-form-item label="课程讲师">

  <el-select
    v-model="courseInfo.teacherId"

    placeholder="请选择">

    <el-option

      v-for="teacher in teacherList"

      :key="teacher.id"

      :label="teacher.name"

      :value="teacher.id"/>
  </el-select>

</el-form-item>

  <el-form-item label="总课时">

    <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>

  </el-form-item>

        <!-- 课程简介 TODO -->
    <el-form-item label="课程简介">

    <tinymce :height="300" v-model="courseInfo.description"/>

    </el-form-item>

        <!-- 课程封面 TODO -->
        <el-form-item label="课程封面">

        <el-upload
        class="avatar-uploader"
        :action="BASE_API+'/ossservice/uploadfile/uploadCover'"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload" 
        accept=".gif,.jpg,.jpeg,.png,.GIF,.JPG,.PNG"
        :on-change="updateCover">
        <img v-if="courseInfo.cover" :src="courseInfo.cover" class="avatar">
 
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

        </el-form-item>

    <el-form-item label="课程价格">

        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元

    </el-form-item>

    <el-form-item>

        <el-button :disabled="saveBtnDisabled" type="primary" @click="next()">保存并下一步</el-button>

    </el-form-item>

    </el-form>
    </div>
</template>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .tinymce-container {
    line-height: 29px;
  }
</style>
<script>
import course from '@/api/edu/course'
import teacher from '@/api/edu/teacher'
import subject from '@/api/edu/subject'
import Tinymce from '@/components/Tinymce'
export default {
    components: { Tinymce },
    data(){
        return {
            saveBtnDisabled:false,// 保存按钮是否禁用
            courseInfo:{
                title: '',
                subjectId: '',
                teacherId: '',
                lessonNum: 0,
                description: '',
                cover: process.env.OSS_PATH + '/cover/default.png',
                price: 0,
                subjectParentId:''
            },
            BASE_API:process.env.BASE_API,
            teacherList:[],
            subjectOneList:[],//一级分类
            subjectTwoList:[],//二级分类
        }
    },
    created() {
        this.init();
    },
    watch:{
        $route(to,from){
            this.init();
        }
    },
    methods: {
        init(){
        if (this.$route.params && this.$route.params.id) {
             const id = this.$route.params.id
             //根据id获取课程基本信息
            this.getCourseInfoById(id)
            } else {

                this.courseInfo = {}
            }
            //初始化所有讲师
            this.getAllTeacher();
            //初始化课程分类
            this.initSubjectList()
        },
        getCourseInfoById(id){
            course.getCourseInfoById(id)
            .then(Response =>{
                this.courseInfo = Response.data.courseInfoVo;
                //初始化课程分类列表
                subject.getNestedTreeList()
                .then(resSubjectoneList =>{
                    this.subjectOneList = resSubjectoneList.data.subjectList
                    //取出所有的一级分类id 与二级分类的父级id比较相等
                    for (let i = 0; i < this.subjectOneList.length; i++) {
                        if(this.subjectOneList[i].id === this.courseInfo.subjectParentId){
                            this.subjectTwoList = this.subjectOneList[i].children
                        }
                    }
                })
                this.getAllTeacher();
            })
            .catch(Response =>{
                this.$message({
                type: 'error',
                message: Response.message
                })
            })
        },
        next(){
            //debugger
            this.saveBtnDisabled =true;
            if(!this.courseInfo.id){
                this.saveData();
            }else{
                this.updateData();
            }
        },
        saveData(){
            //debugger
            course.addCourseInfo(this.courseInfo)
            .then(Response =>{
                this.$message({
                    type: 'success',
                    message: '添加课程信息成功!'
                })
                this.$router.push({path:'/educourse/chapter/'+Response.data.id})
            })
            .catch(Response=>{
                this.$message({
                    type: 'success',
                    message: '添加课程信息失败!'
                })
            })
        },
        updateData(){
            this.saveBtnDisabled = true
            course.updateCourseInfo(this.courseInfo)
            .then(Response=>{
                this.$message({
                    type: 'success',
                    message: '修改课程信息成功!'
                })
                this.$router.push({path:'/educourse/chapter/'+Response.data.courseId})
            })
            .catch(Response=>{
                 this.$message({
                type: 'error',
                message: "保存失败"
                })
            })
        },
        getAllTeacher(){
            teacher.getAllTeacher()
            .then(Response =>{
                this.teacherList = Response.data.item;
            })
            .catch(Response =>{
                this.$message({
                    type: 'success',
                    message: '获取老师列表失败'
                })
            })
        },
        initSubjectList(){
            subject.getNestedTreeList()
            .then(Response =>{
                this.subjectOneList = Response.data.subjectList;
            })
            .catch(Response =>{
                this.$message({
                    type: 'success',
                    message: '获取课程分类失败'
                })
            })
        },
        subjectLevelOneChanged(value){//一级分类改变二级分类显示的
            for(let i =0;i<this.subjectOneList.length;i++){
                if(this.subjectOneList[i].id===value){
                    this.subjectTwoList = this.subjectOneList[i].children;
                    //把二级分类的值清空
                    this.courseInfo.subjectId = '';
                }
            }
        },
        updateSubjectTwoListData(){//清空之后显示第二次选择的值
            this.$forceUpdate();
        },
        handleAvatarSuccess(res,file){//上传成功的方法
            this.courseInfo.cover = res.data.url;
            //console.log(this.courseInfo.cover);
        },
        beforeAvatarUpload(file){//上传之前的方法限制文件类型和大小
            const isJPG = file.type === 'image/jpeg' || file.type === 'image/jpg' || file.type === 'image/png'|| file.type === 'image/PNG'|| file.type === 'image/JPG';

            const isLt2M = file.size / 1024 / 1024 < 2
            if (!isJPG ) {
                this.$message.error('上传头像图片只能是 JPG格式!')
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!')
            }
                return isJPG && isLt2M
        },
        updateCover(){//更新图片显示
              this.$forceUpdate();
        }
    }
}
</script>