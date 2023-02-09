<template>
  <!--    <div>-->
  <!--      <h1>问卷编辑</h1>-->
  <!--    </div>-->
  <div>
    <el-card class="box-card">


      <div slot="header" class="clearfix" style="height: 50px">
        <span>{{ '问卷名称:  ' + this.qnInfo.qnName }}</span>
        <el-button type="primary" style=" margin-left: 50px; padding:20px " @click="cosplay">模拟答卷</el-button>
        <el-button type="primary" style="float: right;  padding:20px " @click="addChoice">添加问题</el-button>
      </div>


      <div>
        <span>{{ '创建者:  ' + this.qnInfo.qnUser }}</span>
        <el-divider></el-divider>
      </div>


      <div>
        <span style="height: 50px">{{ '问卷:  ' }}</span>

      </div>

      <div v-for="(item,index) in info" :key="index" style="height: 50px" class="text item">
        <i class="el-icon-caret-right"></i>
        <el-tag effect="dark" style="height: 30px"  type="warning"> {{ index+1 + ': ' + item.qnchoiceWord }}</el-tag>

        <span v-if="item.qnchoiceA == ''">
           <el-tag style="margin-left: 30px;height: 30px"    effect="dark" type="warning"> 填空：</el-tag>
        </span>
        <span v-else >

          <el-tag style="margin-left: 30px;height: 30px"    effect="dark" type="warning"> {{'A: ' + item.qnchoiceA }}</el-tag>
          <el-tag style="margin-left: 30px;height: 30px"    effect="dark" type="warning"> {{ ' B:' + item.qnchoiceB }}</el-tag>
           <el-tag style="margin-left: 30px;height: 30px"    effect="dark" type="warning"> {{ 'C: ' + item.qnchoiceC }}</el-tag>

        </span>




      </div>

    </el-card>

    <el-dialog :visible.sync="dialogFormVisible" width="30%">

      <el-select v-model="value" placeholder="请选择题型" style="height: 40px">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>

      <span  v-if="value =='选择'">

          <el-form label-width="80px">
        <el-form-item label="题目">
          <el-input v-model="qnchoiceWord" maxlength="30" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="A:">
          <el-input v-model="qnchoiceA" maxlength="30" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="B:">
          <el-input v-model="qnchoiceB" maxlength="30" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="C:">
          <el-input v-model="qnchoiceC" maxlength="30" autocomplete="off"></el-input>
        </el-form-item>

           </el-form>
      </span>

      <span v-else-if="value =='填空'">

          <el-form label-width="80px">
            <el-form-item label="题目">
           <el-input v-model="qnchoiceWord" maxlength="30" autocomplete="off"></el-input>
           </el-form-item>
           </el-form>

      </span>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="addSave">确 定</el-button>
      </div>

    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "QnEditView",

  data() {
    return {
      qnchoiceWord:'',
      qnchoiceA:'',
      qnchoiceWord2:'',
      qnchoiceA2:'',
      qnchoiceB:'',
      qnchoiceC:'',
      qnInfo: localStorage.getItem("qnId") ? JSON.parse(localStorage.getItem("qnId")) : {},
      info: [],
      dialogFormVisible: false,

      options: [{
        value: '选择',
        label: '选择'
      }, {
        value: '填空',
        label: '填空'
      }, ],
      value: ''



    }
  },
  created() {

    this.load()
    // alert(info)
    document.title = '信息'
  },
  methods: {
    cosplay(){
      this.$router.push("/user/userCos");
    },
    load() {
      this.request.get("/qn/loadQn", {
        params: {
          qnId: this.qnInfo.qnId,
        }
      }).then(res => {
        console.log(res)
        this.info = res
        // alert(this.info)

      })

    },

    cancel() {
      this.qnchoiceWord=''
      this.qnchoiceA=''
      this.qnchoiceWord2=''
      this.qnchoiceA2=''
      this.qnchoiceB=''
      this.qnchoiceC=''
      this.value = ''
      this.dialogFormVisible = false
    },
    addChoice() {
      if(this.qnInfo.qnState !== '未发布' ){
        this.$message.error("问卷已经发布，不能添加问题！")
        return
      }

      this.dialogFormVisible = true
      this.value = ''
      this.qnchoiceWord=''
      this.qnchoiceA=''
      this.qnchoiceWord2=''
      this.qnchoiceA2=''
      this.qnchoiceB=''
      this.qnchoiceC=''
    },

    addSave() {
      // alert(this.groupInfo.groupId)
      //  alert(this.answerId)
      if(this.qnchoiceWord==null ||this.qnchoiceWord==="" ){
        this.$message.warning("题目不能为空")
        return
      }
      if( this.qnchoiceA==="" && this.value==='选择' ){
        this.$message.warning("选项A不能为空")
        return
      }
      if( this.qnchoiceB==="" && this.value==='选择' ){
        this.$message.warning("选项B不能为空")
        return
      }
      if( this.qnchoiceC==="" && this.value==='选择' ){
        this.$message.warning("选项C不能为空")
        return
      }


      this.request.get("/qn/addChoice", {
        params: {
          qnId: this.qnInfo.qnId,
          qnchoiceWord:this.qnchoiceWord,
          qnchoiceA:this.qnchoiceA,
          qnchoiceB:this.qnchoiceB,
          qnchoiceC:this.qnchoiceC
        }
      }).then(res => {
        console.log(res)
        if (res.code == "200") {
          //this.$message.success("成功")
          this.dialogFormVisible = false
          this.load()
        } else if (res.code == "400") {
          // this.$message.error("不存在")
        } else if (res.code == "401") {
          //this.$message.success("已存在")
        }


      })

    }
  }

}

</script>

<style scoped>

</style>