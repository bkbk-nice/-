<template>


  <el-card class="box-card" shadow="hover" body-style=""  header="群组信息" style=" padding: 18px 0; width: 1050px;">

    <div style="padding: 10px 0">
      <el-input style="width: 200px"   maxlength="10"  placeholder="请输入id" suffix-icon="el-icon-search"
                v-model="groupId"></el-input>
      <el-input class="ml-5" style="width: 200px;"    maxlength="10"   placeholder="请输入用户群名称" suffix-icon="el-icon-search"
                v-model="groupName"></el-input>

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

        <el-button type="danger" disabled slot="reference" >批量删除<i class="el-icon-delete"></i></el-button>
      </el-popconfirm>



      <el-button type="primary" class="ml-15">导入<i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">>


      <el-table-column
          type="selection"
          width="55">
      </el-table-column>

      <el-table-column prop="groupId" label="id" width="140">
      </el-table-column>
      <el-table-column prop="groupName" label="群名称" width="120">
      </el-table-column>
      <el-table-column prop="groupLeader" label="群主" width="220">
      </el-table-column>
      <el-table-column prop="groupAnswernum" label="答者数量" width="50">
      </el-table-column>

      <!--      <el-table-column prop="fee" label="费用" width="100">-->
      <!--      </el-table-column>-->


      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>


          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="handleDelete(scope.row.groupId)"
          >
            <el-button type="danger" slot="reference" >删除<i class="el-icon-remove-outline"></i></el-button>

          </el-popconfirm>
        </template>

      </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope2">
            <el-button type="success" @click="goto(scope2.row)">查看详情<i class="el-icon-edit"></i></el-button>
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
        <el-form-item label="群名称" >
          <el-input v-model="form.groupName"   maxlength="10"  autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="群主"  >

<!--          v-model="this.man.name"-->
          <el-input v-model="this.man.name" disabled autocomplete="off"  ></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="addSave">确 定</el-button>
      </div>
    </el-dialog>


  </el-card>
<!--  <div>-->
<!--    <h1>userview</h1>-->
<!--  </div>-->
</template>

<script>
export default {
  name: "UserView",
  data(){
    return{

      man:localStorage.getItem("man") ?JSON.parse( localStorage.getItem("man")):{},
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      form: {groupId:'',groupLeader:'',groupName: '',groupAnswernum:''},

      // form: {id:'',leader:'',name: '',password: '',phone: '',up:''},

      dialogFormVisible:false,
      multipleSelection:[],
      groupId: "",
      groupName: "",
      headerBg: 'headerBg',
      xx:0
    }
  },
  created() {

    this.load()
    this.form.groupLeader=this.man.id
   // alert(this.form.leader )

    document.title = '信息'
  },

  methods :{

    goto(row){
      this.form=row
        //JSON.stringify(res.data)
      localStorage.setItem("groupId", JSON.stringify(this.form)   )
      // alert()
      this.$router.push("/user/GroupView");
         //this.$message.success("退出成功")
    },

    load(){
    //  alert(localStorage.getItem("man"))
      this.request.get("/group/page",{
        params: {
          groupLeader : this.man.id,
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          groupId: this.groupId,
          groupName: this.groupName,
          //userUp: this.userUp,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },


    cancel(){

      this.form.groupId=""
      this.form.groupName=""
      this.form.groupAnswernum=""
      this.dialogFormVisible=false;
      this.load();

    },
    clearSearch() {
      this.groupId = ""
      this.groupName = ""
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
      this.form.groupId=""
      this.form.groupName=""
      this.form.groupAnswernum=""
     // alert(this.form.leader )

    },


    handleDelete(groupId){
      this.xx=groupId
    //  alert(this.xx)
     // alert(this.man.id)
      // this.request.get("/group/de" ,{
      //   param:{
      //     groupId : String(this.xx),
      //     groupLeader : this.man.id,
      //   }
      // }).then(res=>{
      //   if(res.code === '200'){
      //     this.$message.success("删除成功")
      //     this.load()
      //   }else{
      //     this.$message.warning("删除失败,有关联答者")
      //   }
      // })
      this.request.get("/group/de", {
        params: {
          groupId: this.xx,
          groupLeader : this.man.id,
        }
      }).then(res => {
        console.log(res)
        if(res.code === '200'){
              this.$message.success("删除成功")
              this.load()
            }else{
              this.$message.warning("删除失败,有关联答者")
            }
      })

    },

    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },


    addSave(){
      if(this.form.groupName==null ||this.form.groupName==="" ){
        this.$message.warning("用户名不能为空")
        return
      }


    //  alert( JSON.stringify(this.form)    )
     // this.form.leader=this.man.id
      this.request.post("/group",this.form).then(res=>{
        if(res){
          this.$message.success("保存成功")
          this.dialogFormVisible=false;
          this.groupId=""
          this.groupName= ""
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