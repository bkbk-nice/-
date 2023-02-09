<template>


  <el-card class="box-card" shadow="hover" body-style=""  header="答者名单" style=" padding: 18px 0; width: 1050px;">

    <div style="padding: 10px 0">
<!--      <el-input style="width: 200px"   maxlength="10"  placeholder="请输入id" suffix-icon="el-icon-search"-->
<!--                v-model="this.answerId"></el-input>-->
      <el-input class="ml-5" style="width: 200px;"    maxlength="10"   placeholder="请输入答者名" suffix-icon="el-icon-search"
                v-model="answerName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="clearSearch">重置</el-button>
    </div>


    <div style="padding: 10px 0">
      <el-button type="primary" class="ml-15">导入<i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" >


      <el-table-column
          type="selection"
          width="55">
      </el-table-column>

      <el-table-column prop="qnId" label="问卷id" width="140">
      </el-table-column>
      <el-table-column prop="answerId" label="答者id" width="120">
      </el-table-column>
      <el-table-column prop="answerName" label="答者名" width="220">
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope2">
          <el-button type="success" @click="goto(scope2.row)">查看答卷<i class="el-icon-edit"></i></el-button>

        </template>
      </el-table-column>

    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10,15,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

  </el-card>
</template>

<script>
export default {
  name: "QnAnswer",
  data(){
    return{

      qnInfo: localStorage.getItem("qnId") ? JSON.parse(localStorage.getItem("qnId")) : {},
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      form: {qnId:'',answerId:'',answerName: '' },

     // answerId: "",
      answerName: "",
      headerBg: 'headerBg',

    }
  },
  created() {
    this.load()
    document.title = '信息'
  },

  methods :{

    goto(row){
      this.form=row
      //JSON.stringify(res.data)
      localStorage.setItem("answer", JSON.stringify(this.form)   )
      // alert()
      this.$router.push("/user/doneAnswer");
      //this.$message.success("退出成功")
    },

    load(){
      //  alert(localStorage.getItem("man"))
      this.request.get("/qn/answer",{
        params: {
          qnId:this.qnInfo.qnId,
          answerName:this.answerName,
        }
      }).then(res => {
        console.log(res.data)
        this.tableData = res.data
        this.total = res.data.length
      })
    },



    clearSearch() {
      this.answerId = ""
      this.answerName = ""
      this.load()
    },

    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },


    delBatch(){

    },

    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
  }
}
</script>

<style scoped>

</style>