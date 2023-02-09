<template>
    <div>
      <el-card class="box-card">


        <div slot="header" class="clearfix" style="height: 50px">
          <span>{{'群名:  '+this.groupInfo.groupName}}</span>
          <el-button type="primary" style="float: right;  padding:20px "    @click="addAnswer">邀请</el-button>
        </div>


        <div>
          <span>{{'群主:  '+this.groupInfo.groupLeader}}</span>
          <el-divider></el-divider>
        </div>


        <div>
          <span  style="height: 50px">{{'用户:  ' }}</span>

        </div>

        <div v-for="(item,index) in info" :key="index" style="height: 50px" class="text item">
          <i class="el-icon-user"></i>
          <el-tag   effect="dark" type="warning"> {{'' + item.answerName +'('+item.answerId+')'}}</el-tag>

        </div>

      </el-card>

      <el-dialog  :visible.sync="dialogFormVisible"  width="30%"  >
        <el-form label-width="80px">
          <el-form-item label="答者id" >
            <el-input v-model="answerId"   maxlength="10"  autocomplete="off" ></el-input>
          </el-form-item>

        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="addSave">确 定</el-button>
        </div>
      </el-dialog>

    </div>
</template>

<script>
export default {
  name: "GroupView",

  data(){
    return{
      answerId:'',
      groupInfo:localStorage.getItem("groupId") ?JSON.parse( localStorage.getItem("groupId")):{},
      info:[],
      dialogFormVisible:false,

    }
  },
  created() {

    this.load()
   // alert(info)
    document.title = '信息'
  },
  methods:{

    load(){
      this.request.get("/group/groupX",{
        params: {
          groupId: this.groupInfo.groupId,
        }
      }).then(res => {
        console.log(res)
        this.info = res
       // alert(this.info)

      })

    },

    cancel(){
      this.dialogFormVisible=false
    },
    addAnswer(){
      this.dialogFormVisible=true
    },

    addSave(){
     // alert(this.groupInfo.groupId)
    //  alert(this.answerId)
      this.request.get("/group/inviteAnswer",{
        params: {
          groupId: this.groupInfo.groupId,
          answerId:this.answerId,
          groupLeader:this.groupInfo.groupLeader
        }
      }).then(res => {
        console.log(res)
        if(res.code == "200"){
          this.$message.success("邀请成功")
          this.dialogFormVisible=false
          this.load()
        }else if(res.code == "400"){
          this.$message.error("不存在")
        }else if(res.code == "401"){
          this.$message.success("已存在")
        }


      })

    }
  }




}
</script>

<style scoped>

</style>