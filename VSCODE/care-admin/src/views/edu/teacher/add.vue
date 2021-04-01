<template>
    <div class="app-container">
    <el-form :model="eduTeacher" :rules="rules" ref="eduTeacher" label-width="100px" class="demo-ruleForm">
      <el-form-item label="讲师名称" prop="name">
        <el-input v-model="eduTeacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序" prop="sort">
        <el-input-number v-model="eduTeacher.sort" controls-position="right"/>
      </el-form-item>
      <el-form-item label="讲师头衔" prop="level">
        <el-select v-model="eduTeacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历" prop="career">
        <el-input v-model="eduTeacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介" prop="intro">
        <el-input v-model="eduTeacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

      <!-- 讲师头像：TODO -->
      <!-- 讲师头像 -->

<el-form-item label="讲师头像">

    <!-- 头衔缩略图 -->

    <pan-thumb :image="eduTeacher.avatar"/>

    <!-- 文件上传按钮 -->

    <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像

    </el-button>

    <!--

v-show：是否显示上传组件

:key：类似于id，如果一个页面多个图片上传控件，可以做区分

:url：后台上传的url地址

@close：关闭上传组件

@crop-upload-success：上传成功后的回调 -->

    <image-cropper
                   v-show="imagecropperShow"

                   :width="300"

                   :height="300"

                   :key="imagecropperKey"

                   :url="BASE_API+'/ossservice/uploadfile/uploadAvatar'"

                   field="file"

                   @close="close"

                   @crop-upload-success="cropSuccess"/>

</el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate('eduTeacher')">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import teacher from '@/api/edu/teacher'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
export default {
  components: {ImageCropper,PanThumb},
  data(){
    return {
      eduTeacher:{
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: process.env.OSS_PATH+"/avatar/default.png"
      },
      imagecropperShow:false,
      imagecropperKey:0,
      BASE_API:process.env.BASE_API,
        rules: {
          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' }
          ],        
          career: [
            { required: true, message: '请输入讲师资历', trigger: 'change' }
          ],
          intro: [
            { required: true, message: '输点讲师简介吧', trigger: 'change' }
          ]
      },
      saveBtnDisabled: false
    }
  },
  watch:{
    $route(to,from){
      this.init();
    }
  },
  created() {
    this.init();
    
  },
  methods: {
    close(){//关闭上传头像页面
      this.imagecropperShow=false;
      //重置页面
      this.imagecropperKey = this.imagecropperShow+1;
    },
    cropSuccess(data){//上传头像
        this.eduTeacher.avatar = data.url
        this.imagecropperShow=false;
        //重置页面
        this.imagecropperKey = this.imagecropperShow+1;
    },
    init(){
      if(this.$route.params && this.$route.params.id ){
      const id = this.$route.params.id;
      this.showTeacher(id);
      }else{
        this.eduTeacher={};
      }
    },
    saveOrUpdate(eduTeacher){
      this.saveBtnDisabled = true;
      if(!this.eduTeacher.id){
        this.saveEduTeacher(eduTeacher);
      }else{
        this.updateTeacher(eduTeacher);
      }
    },
    showTeacher(id){
      teacher.getTecherById(id)
      .then(Response =>{
        this.eduTeacher = Response.data.teacher;
      })
      .catch(Response =>{
        this.$message({
                  type: 'error',
                  message: '显示失败'
          })
      })
    },
    saveEduTeacher(eduTeacher){
      this.$refs[eduTeacher].validate((valid) => {
        if(valid){
          teacher.saveTecher(this.eduTeacher)
          .then(Response =>{
            this.$message({
                    type: 'success',
                    message: '添加成功!'
                })
            this.$router.push({path:'/eduteacher/list'});
          })
          .catch(Response =>{        
            this.$message({
                        type: 'error',
                        message: '添加失败'
                    })
          })
        }
      })
    },
    updateTeacher(eduTeacher){
      this.$refs[eduTeacher].validate((valid) => {
         if(valid){
           teacher.updateTeacherByid(this.eduTeacher)
      .then(Response =>{
        this.$message({
            type: 'success',
            message: '修改成功!'
          })
      this.$router.push({path:'/eduteacher/list'});
      })
      .catch(Response =>{
        this.$message({
          type:'error',
          message:'修改失败'
        })
      })
         }
      })
    }
  }
}
</script>