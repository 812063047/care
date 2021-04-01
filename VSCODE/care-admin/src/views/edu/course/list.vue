<template>
    <div class="app-container">
                <!--查询表单-->
        <el-form :inline="true" class="demo-form-inline">
        <!-- 所属分类：级联下拉列表 -->
        <!-- 一级分类 -->
        <el-form-item label="课程类别">
            <el-select
            v-model="eduCourseObj.subjectParentId"
            placeholder="请选择"
            @change="subjectLevelOneChanged">
            <el-option
                v-for="subject in subjectOneList"
                :key="subject.id"
                :label="subject.title"
                :value="subject.id"/>
            </el-select>
            <!-- 二级分类 -->
            <el-select v-model="eduCourseObj.subjectId" placeholder="请选择" @change="updateSubjectTwoListData">
            <el-option
                v-for="subject in subjectTwoList"
                :key="subject.id"
                :label="subject.title"
                :value="subject.id"/>
            </el-select>
        </el-form-item>
        <!-- 标题 -->
        <el-form-item>
            <el-input v-model="eduCourseObj.title" placeholder="课程标题"/>
        </el-form-item>
        <!-- 讲师 -->
        <el-form-item>
            <el-select
            v-model="eduCourseObj.teacherId"
            placeholder="请选择讲师">
            <el-option
                v-for="teacher in teacherList"
                :key="teacher.id"
                :label="teacher.name"
                :value="teacher.id"/>
            </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getCourseList()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
        </el-form>
        <!-- 表格 -->
        <el-table
        :data="list"
        element-loading-text="数据加载中"
        border
        fit
        highlight-current-row
        row-class-name="myClassList"
        @selection-change="handleSelectionChange">
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            label="序号"
            width="70"
            align="center">
            <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
            </template>
        </el-table-column>
        <el-table-column label="课程信息" width="470" align="center">
            <template slot-scope="scope">
            <div class="info">
                <div class="pic">
                <img :src="scope.row.cover" alt="scope.row.title" width="150px">
                </div>
                <div class="title">
                <a href="">{{ scope.row.title }}</a>
                <p>{{ scope.row.lessonNum }}课时</p>
                </div>
            </div>
            </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center">
            <template slot-scope="scope">
            {{ scope.row.gmtCreate.substr(0, 10) }}
            </template>
        </el-table-column>
        <el-table-column label="发布时间" align="center">
            <template slot-scope="scope">
            {{ scope.row.gmtModified.substr(0, 10) }}
            </template>
        </el-table-column>
        <el-table-column label="价格" width="100" align="center" >
            <template slot-scope="scope">
            {{ Number(scope.row.price) === 0 ? '免费' :
            '¥' + scope.row.price.toFixed(2) }}
            </template>
        </el-table-column>
        <el-table-column prop="buyCount" label="付费学员" width="100" align="center" >
            <template slot-scope="scope">
            {{ scope.row.buyCount }}人
            </template>
        </el-table-column>
        <el-table-column label="操作" width="150" align="center">
            <template slot-scope="scope">
            <router-link :to="'/educourse/info/'+scope.row.id">
                <el-button type="primary"  size="mini" icon="el-icon-edit">编辑课程信息</el-button>
            </router-link>
            <router-link :to="'/educourse/chapter/'+scope.row.id">
                <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程大纲</el-button>
            </router-link>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
            </template>
        </el-table-column>
        </el-table>


        <div slot="footer" class="dialog-footer"> 
        <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeBatchCourse">批量删除</el-button>
        <router-link :to="'/educourse/info/'">
        <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline">发布课程</el-button>
        </router-link>
        </div>
        <!-- 分页 -->
        <el-pagination
        :current-page="page"
        :page-size="limit"
        :total="total"
        style="padding: 30px 0; text-align: center;"
        layout="total, prev, pager, next, jumper"
        @current-change="getCourseList"
        />
    </div>
</template>
<script>
import course from '@/api/edu/course'
import teacher from '@/api/edu/teacher'
import subject from '@/api/edu/subject'
export default {
    data(){
        return{
            list: null, // 数据列表
            total: 0, // 总记录数
            page: 1, // 页码
            limit: 4, // 每页记录数
            eduCourseObj: {
                subjectParentId: '',
                subjectId: '',
                title: '',
                teacherId: ''
            }, // 查询条件
            teacherList: [], // 讲师列表
            subjectOneList: [], // 一级分类列表
            subjectTwoList: [] // 二级分类
        }
    },
    created() {
        this.getCourseList();
        this.initTeacherList();
        this.initSubjectList();
    },
    methods: {
        getCourseList(page = 1){
            this.page = page;
            course.getCoursePageList(this.page,this.limit,this.eduCourseObj)
            .then(Response =>{
                this.list = Response.data.map.records;
                this.total = Response.data.map.total;
            })
            .catch(Response =>{
                this.$message({
                        type: 'info',
                        message: Response.message
                    })
            })
        },
        initTeacherList(){
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
        subjectLevelOneChanged(value){
            for(let i =0;i<this.subjectOneList.length;i++){
                    if(this.subjectOneList[i].id===value){
                        this.subjectTwoList = this.subjectOneList[i].children;
                        //把二级分类的值清空
                        this.eduCourseObj.subjectId = '';
                    }
                }
        },
        updateSubjectTwoListData(){
            this.$forceUpdate();
        },
        resetData(){//清空条件查询的表单
                this.eduCourseObj = {};
                this.subjectTwoList = [];
                this.getCourseList();
        },
        removeDataById(id){
                    // debugger
            this.$confirm('此操作将永久删除该课程，以及该课程下的章节和视频，是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                return course.deleteByCourseId(id)
            }).then(() => {
                this.getCourseList()
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                })
            }).catch((response) => { // 失败
                if (response === 'cancel') {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                }
            })
        },
        handleSelectionChange(val){
          this.selectionObject = val;
        },
        removeBatchCourse(){
            if(this.selectionObject.length==0){
              this.$alert('请选择要删除的课程', '提示', {
              confirmButtonText: '确定',
              callback: action => {
                this.$message({
                  type: 'warning',
                  message: `请选择要删除的课程`
                });
              }
            });
          }else{
            let arrayIds = new Array()
            this.selectionObject.forEach(element => {
              let id =element.id;
              arrayIds.push(id);
            });
            this.$confirm('此操作将永久删除选中的课程及其章节,小节, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
              })
              .then(response =>{
                  //debugger
                  course.deleteBatch(arrayIds)
                  .then(response =>{
                      this.$message({
                      type: 'success',
                      message: '删除成功!'
                      })
                      this.getCourseList();
                  })
                  .catch(response =>{
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
          }
        }
    }
}
</script>
<style scoped>
.myClassList .info {
    width: 450px;
    overflow: hidden;
}
.myClassList .info .pic {
    width: 150px;
    height: 90px;
    overflow: hidden;
    float: left;
}
.myClassList .info .pic a {
    display: block;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}
.myClassList .info .pic img {
    display: block;
    width: 100%;
}
.myClassList td .info .title {
    width: 280px;
    float: right;
    height: 90px;
}
.myClassList td .info .title a {
    display: block;
    height: 48px;
    line-height: 24px;
    overflow: hidden;
    color: #00baf2;
    margin-bottom: 12px;
}
.myClassList td .info .title p {
    line-height: 20px;
    margin-top: 5px;
    color: #818181;
}
.dialog-footer{
  margin-top: 40px;
}

</style>