<template>
    <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="eduTeacherQuery.name" placeholder="讲师名"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="eduTeacherQuery.level" clearable placeholder="讲师头衔">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="eduTeacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="eduTeacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
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

      <el-table-column prop="name" label="名称" width="100" />

      <el-table-column label="头衔" width="100">
        <template slot-scope="scope">
          {{ scope.row.level===1?'高级讲师':'首席讲师' }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="简历" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/eduteacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div slot="footer" class="dialog-footer"> 
      <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeAllTeacher">批量删除</el-button>
      <router-link :to="'/eduteacher/save/'">
      <el-button type="primary" size="mini" icon="el-icon-circle-plus-outline">新增讲师</el-button>
      </router-link>
    </div>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>
</template>
<script>
import teacher from '@/api/edu/teacher'
export default {
    data(){
        return{
            list:null,//查询之后接口返回的值
            page:1,//当前页
            limit:8,//每页记录数
            eduTeacherQuery:{},//条件封装对象
            total:0,
            selectionObject:[]
        }
    },
    created(){//页面渲染之前执行的方法
        this.getList()
    },
    methods: {//创建具体的方法，调用teacher.js定义的方法
        getList(page=1){
            this.page = page;
            teacher.getTeacherListPage(this.page,this.limit,this.eduTeacherQuery)
            .then(response =>{
                this.list= response.data.map.rows;
                this.total = response.data.map.total;
                // console.log(this.list);
                // console.log(this.total);              
            })
            .catch(error =>{
             console.log(error);
            })
        },
        resetData(){//清空条件查询的表单
            this.eduTeacherQuery = {};
            this.getList();
        },
        removeDataById(id){
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            })
            .then(response =>{
                //debugger
                teacher.deletedTeacherById(id)
                .then(response =>{
                    this.$message({
                    type: 'success',
                    message: '删除成功!'
                    })
                    this.getList();
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
                
        },
        handleSelectionChange(val){
          this.selectionObject = val;
        },
        removeAllTeacher(){
          if(this.selectionObject.length==0){
              this.$alert('请选择要删除的讲师', '提示', {
              confirmButtonText: '确定',
              callback: action => {
                this.$message({
                  type: 'warning',
                  message: `请选择要删除的讲师`
                });
              }
            });
          }else{
            let arrayIds = new Array()
            this.selectionObject.forEach(element => {
              let id =element.id;
              arrayIds.push(id);
            });
            this.$confirm('此操作将永久删除选中的讲师, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
              })
              .then(response =>{
                  //debugger
                  teacher.deleteBatch(arrayIds)
                  .then(response =>{
                      this.$message({
                      type: 'success',
                      message: '删除成功!'
                      })
                      this.getList();
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
.dialog-footer{
  margin-top: 40px;
}
</style>
