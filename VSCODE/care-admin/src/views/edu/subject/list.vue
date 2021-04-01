<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />

    <el-tree
      ref="subjectTree"
      :data="subjectList"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />

  </div>
</template>

<script>
import subject from '@/api/edu/subject'
export default {

  data() {
    return {
      filterText: '',
      subjectList: [],
      defaultProps: {
        children: 'children',
        label: 'title'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.subjectTree.filter(val)
    }
  },
  created() {
      this.getAllSubject();
  },

  methods: {
    getAllSubject(){
        subject.getNestedTreeList()
        .then(Response =>{
            //debugger
            if(Response.success ===true){
                console.log(Response.data.subjectList)
                this.subjectList = Response.data.subjectList;
            }
        })
        .catch(Response=>{
            if(Response.success ===false){
                this.$message({
                type: 'error',
                message: response.message
                })
            }
        })
    },
    filterNode(value, data) {

      if (!value) return true
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1
    }
    
  }
}
</script>