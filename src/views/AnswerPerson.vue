<template>
  <div>

    <el-card class="box-card" shadow="hover" body-style=""  header="个人信息" style=" padding: 18px 0; width: 450px;">
      <el-form ref="form" :model="form"  style="width:600px" label-width="80px">

        <el-form-item label="答者id" label-width="200">
          <el-input v-model="form.answerId"  disabled style="width: 300px"></el-input>
        </el-form-item>

        <el-form-item label="答者名称" label-width="200">
          <el-input v-model="form.answerName"  maxlength="10"  style="width: 300px"></el-input>
        </el-form-item>

        <el-form-item label="答者密码" label-width="200">
          <el-input show-password  maxlength="10"  v-model="form.answerPassword"  style="width: 300px"></el-input>
        </el-form-item>


        <el-form-item label="答者手机" label-width="200">
          <el-input v-model="form.answerPhone"  maxlength="10"   style="width: 300px"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定修改</el-button>
          <el-button type="danger" @click="logout">退出登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>

<!--    <el-form ref="form" :model="form"  style="width: 400px" label-width="80px">-->

<!--      <el-form-item label="用户名">-->
<!--        <el-input v-model="form.answerName"></el-input>-->
<!--      </el-form-item>-->

<!--      <el-form-item label="密码">-->
<!--        <el-input show-password v-model="form.answerPassword"></el-input>-->
<!--      </el-form-item>-->


<!--      <el-form-item label="手机">-->
<!--        <el-input v-model="form.answerPhone"></el-input>-->
<!--      </el-form-item>-->

<!--      <el-form-item>-->
<!--        <el-button type="primary" @click="onSubmit">确定修改</el-button>-->

<!--      </el-form-item>-->


<!--    </el-form>-->
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "AnswerPerson",
  data() {
    return {
      man:localStorage.getItem("man") ?JSON.parse( localStorage.getItem("man")):{},

      form: {
        answerId:'',
        answerName:'',
        answerPassword: '',
        answerPhone:'',
      }
    }
  },
  created() {
    this.form.answerId = this.man.answerId;
    this.form.answerName = this.man.answerName;
    this.form.answerPassword = this.man.answerPassword;
    this.form.answerPhone = this.man.answerPhone;

    //this.loadself()
    document.title = '个人信息'
  },

  methods: {
    onSubmit() {
   //   alert(this.form.answerId)

      if(this.form.answerName==null ||this.form.answerName==="" ){
        this.$message.warning("用户名不能为空")
        return
      }

      if(this.form.answerPassword==null ||this.form.answerPassword==="" ){
        this.$message.warning("密码不能为空")
        return
      }

      if(this.form.answerPhone==null ||this.form.answerPhone==="" ){
        this.$message.warning("手机不能为空")
        return
      }
      this.request.post("/answer",this.form).then(res=>{
        if(res){
          this.$message.success("保存成功")
          this.$router.push("/");
          localStorage.removeItem("man")
          this.$message.success("请重新登录")

          // this.load()
        }else{
          this.$message.warning("保存失败")
        }
      })

      console.log('submit!');
    },
    logout() {
      this.$router.push("/");
      localStorage.removeItem("man")
      this.$message.success("退出成功")
    },

  }
}
</script>

<style scoped>

</style>