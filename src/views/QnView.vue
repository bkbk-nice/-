<template>
  <el-card class="box-card" shadow="hover" body-style=""  header="问卷信息" style=" padding: 18px 0; width: 1250px;">

    <div style="padding: 10px 0">
      <el-input style="width: 200px"   maxlength="10"  placeholder="请输入id" suffix-icon="el-icon-search"
                v-model="qnId"></el-input>
      <el-input class="ml-5" style="width: 200px;"    maxlength="10"   placeholder="请输入问卷名称" suffix-icon="el-icon-search"
                v-model="qnName"></el-input>

      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="clearSearch">重置</el-button>
    </div>


    <div style="padding: 10px 0">
      <el-button type="primary" @click="addBtn">新增<i class="el-icon-circle-plus-outline"></i></el-button>



      <el-popconfirm
          class="ml-15"
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="确定删除吗？"
          @confirm="delBatch"
      >
        <!--            <el-button type="danger" slot="reference" >删除<i class="el-icon-remove-outline"></i></el-button>-->

        <el-button type="danger"  disabled slot="reference" >批量删除<i class="el-icon-delete"></i></el-button>
      </el-popconfirm>



      <el-button type="primary" class="ml-15">导入<i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">>


      <el-table-column
          type="selection"
          width="55">
      </el-table-column>

      <el-table-column prop="qnId" label="id" width="70">
      </el-table-column>
      <el-table-column prop="qnName" label="问卷名称" width="220">
      </el-table-column>
      <el-table-column prop="qnUser" label="创建者" width="120">
      </el-table-column>
      <el-table-column prop="qnState" label="状态" width="75">
      </el-table-column>

      <!--      <el-table-column prop="fee" label="费用" width="100">-->
      <!--      </el-table-column>-->


      <el-table-column label="操作"   width="275" >
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>


          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="handleDelete(scope.row)"
          >
            <el-button type="danger" slot="reference" >删除<i class="el-icon-remove-outline"></i></el-button>





            <el-button type="warning" slot="reference" @click="out(scope.row)">发布<i class="el-icon-circle-check"></i></el-button>

          </el-popconfirm>
        </template>

      </el-table-column>

      <el-table-column label="操作" width="300px">
        <template slot-scope="scope2">
          <el-button type="success" @click="goto(scope2.row)">查看详情<i class="el-icon-edit"></i></el-button>
          <el-button type="warning" @click="gotoAnswer(scope2.row)">答者名单<i class="el-icon-edit"></i></el-button>
          <el-button type="danger" @click="gotoShow(scope2.row)">统计<i class="el-icon-edit"></i></el-button>
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


    <el-dialog  :visible.sync="dialogFormVisible"  width="30%"  >
      <el-form label-width="80px">
        <el-form-item label="问卷名称" >
          <el-input v-model="form.qnName"   maxlength="10"  autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="创建者"  >

          <!--          v-model="this.man.name"-->
          <el-input v-model="this.man.name" disabled autocomplete="off"  ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="addSave">确 定</el-button>
      </div>
    </el-dialog>



    <el-dialog  :visible.sync="dialogFormVisible2"  width="30%"  >
      <el-form label-width="80px">
        <el-form-item label="群号码" >
          <el-input v-model="submitgroupid"   maxlength="10"  autocomplete="off" ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel2">取 消</el-button>
        <el-button type="primary" @click="submit2">确 定</el-button>
      </div>
    </el-dialog>


  </el-card>
</template>

<script>
export default {
  name: "QnView",
  data(){
    return{

      man:localStorage.getItem("man") ?JSON.parse( localStorage.getItem("man")):{},
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      form: {qnId:'',qnUser:'',qnName: '',qnState:''},

      // form: {id:'',leader:'',name: '',password: '',phone: '',up:''},

      dialogFormVisible:false,
      dialogFormVisible2:false,
      submitgroupid:'',
      multipleSelection:[],
      qnId: "",
      qnName: "",
      qnState: "",
      headerBg: 'headerBg',

    }
  },
  created() {

    this.load()
    this.form.qnUser=this.man.id
    // alert(this.form.leader )

    document.title = '信息'
  },

  methods :{
    gotoShow(row){
      this.form=row
      localStorage.setItem("qnId", JSON.stringify(this.form))
      this.$router.push("/user/userQnShow")
    },
    gotoAnswer(row){
      this.form=row
      localStorage.setItem("qnId", JSON.stringify(this.form))
      this.$router.push("/user/qnAnswer")
    },

    cancel2(){
      this.submitgroupid="",
      this.dialogFormVisible2=false;

    },
    submit2(){
      //release
      if(this.submitgroupid==null ||this.submitgroupid==="" ){
        this.$message.warning("群名称为空")
        return
      }
      this.request.get("/qn/release", {
        params: {
          qnId: this.form.qnId,
          groupId : this.submitgroupid,
          groupLeader:this.man.id,
        }
      }).then(res => {
        console.log(res)
        if(res.code === '200'){
          this.$message.success("发布成功")
          this.load()
          this.submitgroupid="", this.dialogFormVisible2=false;
        }else if(res.code === '400'){
          this.$message.warning("您没有这个群")
        }else if(res.code === '401'){
          this.$message.warning("问卷不能重复发布")
        }
      })
    },
    out(row){
      //发布问卷
      this.form=row
     // alert(this.form.qnId)
      this.dialogFormVisible2=true;

    },
    goto(row){
      this.form=row
      //JSON.stringify(res.data)
      localStorage.setItem("qnId", JSON.stringify(this.form)   )
      // alert()
      this.$router.push("/user/qnEditView");
      //this.$message.success("退出成功")
    },

    load(){
      //  alert(localStorage.getItem("man"))
      this.request.get("/qn/page",{
        params: {
          qnUser : this.man.id,
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          qnId: this.qnId,
          qnName: this.qnName,
          qnState: this.qnState,
          //userUp: this.userUp,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },


    cancel(){

      this.form.qnId=""
      this.form.qnName=""
      this.form.qnState=""
      this.dialogFormVisible=false;
      this.load();

    },
    clearSearch() {
      this.qnId = ""
      this.qnName = ""
      this.qnState=""
      this.load()
    },


    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },


     delBatch(){
    //   let ids = this.multipleSelection.map(v=>v.id)
    //   this.request.post("/group/del/batch",ids).then(res=>{
    //     if(res){
    //       this.$message.success("批量删除成功")
    //       this.load()
    //     }else{
    //       this.$message.warning("批量删除失败")
    //     }
    //   })
     },


    handleSelectionChange(val){
      this.multipleSelection = val;
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },


    addBtn(){
      this.dialogFormVisible=true
      this.form.qnId=""
      this.form.qnName=""
      this.form.qnState="未发布"
      // alert(this.form.leader )

    },


    handleDelete(row){
      if(row.qnState !== '未发布'){
        this.$message.error("已发布不能删除")
      }else{
        this.request.get("/qn/del", {
          params: {
            qnId: row.qnId,
          }
        }).then(res => {
          console.log(res)
          if(res.code === '200'){
            this.$message.success("删除成功")
            this.load()
          }else{
            this.$message.warning("删除失败")
          }
        })

      }


    },

    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },


    addSave(){
      if(this.form.qnName==null ||this.form.qnName==="" ){
        this.$message.warning("问卷名称不能为空")
        return
      }


      //  alert( JSON.stringify(this.form)    )
      // this.form.leader=this.man.id
      this.request.post("/qn",this.form).then(res=>{
        if(res){
          this.$message.success("保存成功")
          this.dialogFormVisible=false;
          this.qnId=""
          this.qnName= ""
          this.load()
        }else{
          this.$message.warning("保存失败")
        }
      })


    }
  }
}
</script>

<style scoped>

</style>