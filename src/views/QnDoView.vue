<template>
  <div>

<!--    v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }"-->
<!--    <el-card class="box-card" style="background-color: bisque">-->
      <el-card class="box-card" v-bind:style="{ background: value}">

      <h1 slot="header" class="clearfix" style="height: 50px">
        <span>{{ '问卷名称:  ' + this.qnInfo.qnName }}</span>


        <el-select style="margin-left: 100px" v-model="value" placeholder="答卷风格">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>


        <el-button type="primary" style="float: right;  padding:20px " @click="submit">提交</el-button>
      </h1>


      <h1>
        <span>{{ '创建者:  ' + this.userName }}</span>
        <el-divider></el-divider>
      </h1>


      <h1>
        <span style="height: 50px">{{ '问卷:  ' }}</span>
      </h1>

      <div style="overflow-y:scroll; height: 600px">




            <div  v-for="(item,index) in info" :key="index"
                  style="height: 250px;margin-left: 200px" class="text item">


              <i class="el-icon-caret-right">
                <el-tag effect="dark" style="height: 30px ;width: 200px"  text="font:20px" type="warning">
                 <h2 style="color: darkslategrey">{{ index+1 + ': ' + item.qnchoiceWord }}
                 </h2> </el-tag>
              </i>

              <h3  v-if="item.qnchoiceA == ''">
                    <h3>
                      <el-input
                          type="textarea"
                          :rows="2"
                          placeholder="请输入内容"
                           style="font-size:30px;margin-top: 30px;width: 400px;"
                          v-model="xx.answerOut[index]">
                      </el-input>
                    </h3>

              </h3>

              <h3 v-else >
                 <div id="app" style="height: 260px ;width: 400px">
                   <br>
                <input type="radio" v-model="xx.answerOut[index]"  value="A"  id="study" >
                <label for="study"  >{{'A: ' + item.qnchoiceA }}</label><br>

                <input type="radio" v-model="xx.answerOut[index]" value="B" id="read" >
                <label for="study"  >{{ 'B:' + item.qnchoiceB }}</label><br>

                <input type="radio" v-model="xx.answerOut[index]" value="C" id="play" >
                <label for="study">{{ 'C: ' + item.qnchoiceC }}</label><br>

                <p>选择：{{xx.answerOut[index]}}</p>
                 </div>
              </h3>

              <div>

              </div>


            </div>
      </div>


<!--      <div id="app">-->
<!--        <input type="radio" v-model="xx[0]" value="学习" id="study">-->
<!--        <label for="study">学习</label><br>-->
<!--        <input type="radio" v-model="xx[0]" value="读书" id="read">-->
<!--        <label for="study">读书</label><br>-->
<!--        <input type="radio" v-model="xx[0]" value="运动" id="play">-->
<!--        <label for="study">运动</label><br>-->
<!--        <p>选择的选项是：{{xx[0]}}</p>-->
<!--      </div>-->

<!--      <div id="app">-->
<!--        <input type="radio" v-model="xx[1]" value="学习" id="study">-->
<!--        <label for="study">学习</label><br>-->
<!--        <input type="radio" v-model="xx[1]" value="读书" id="read">-->
<!--        <label for="study">读书</label><br>-->
<!--        <input type="radio" v-model="xx[1]" value="运动" id="play">-->
<!--        <label for="study">运动</label><br>-->
<!--        <p>选择的选项是：{{xx[1]}}</p>-->
<!--      </div>-->

<!--      <div v-for="(item,index) in info" :key="index" style="height: 50px" class="text item">-->
<!--        <i class="el-icon-caret-right"></i>-->
<!--        <el-tag effect="dark" style="height: 30px ;width: 200px"  type="warning"> {{ index+1 + ': ' + item.qnchoiceWord }}</el-tag>-->

<!--&lt;!&ndash;        info[index].qnchoiceId&ndash;&gt;-->
<!--        <el-radio-group v-model="picked[index]"  style="margin-left: 50px">-->
<!--          <el-radio :label="1" style="margin-left: 5px;height: 30px;width: 20px" >A</el-radio>-->
<!--          <el-tag style="margin-left: 5px;height: 30px;width: 150px"    effect="dark" type="warning"> {{'A: ' + item.qnchoiceA }}</el-tag>-->
<!--          <el-radio :label="2" style="margin-left: 5px;height: 30px;width: 20px">B</el-radio>-->
<!--          <el-tag style="margin-left: 5px;height: 30px;width: 150px"    effect="dark" type="warning"> {{ ' B:' + item.qnchoiceB }}</el-tag>-->
<!--          <el-radio :label="3"  style="margin-left: 5px;height: 30px;width:20px">C</el-radio>-->
<!--          <el-tag style="margin-left: 5px;height: 30px;width: 150px"    effect="dark" type="warning"> {{ 'C: ' + item.qnchoiceC }}</el-tag>-->
<!--        </el-radio-group>-->

<!--      </div>-->

    </el-card>

  </div>
</template>

<script>
export default {
  name: "QnDoView",

  data() {
    return {
      userName:'',
      qnchoiceWord:'',
      qnchoiceA:'',
      qnchoiceB:'',
      qnchoiceC:'',
      qnInfo: localStorage.getItem("qnId") ? JSON.parse(localStorage.getItem("qnId")) : {},
      man: localStorage.getItem("man") ? JSON.parse(localStorage.getItem("man")) : {},
      info: [],
      dialogFormVisible: false,
      picked: {one:"",two:"",three:"",four:""},
      xx: {qnId:'',Id:'',answerId:'',qnchoiceId:[],answerOut:[]},
      total:0,
      ifdone:false,

      options: [{
        value: 'bisque',
        label: '默认'
      }, {
        value: 'blue',
        label: '冷锋蓝'
      }, {
        value: 'cyan',
        label: '山水青'
      }, {
        value: 'grey',
        label: '沉稳灰'
      }, {
        value: 'pink',
        label: '少女粉'
      }],
      value: 'bisque'
    }
  },
  created(){

    this.load()
    this.loaduser()
    // alert(info)
    document.title = '问卷'
    this.xx.answerId =this.man.answerId
    this.xx.qnId = this.qnInfo.qnId
    this.xx.Id = this.qnInfo.qnUser

  },
  methods: {

    submit(){
    //  alert("total="+this.total)
     // alert("now="+ this.xx.answer.length)

      for (var i =0; i <this.info.length; i++) {
        this.xx.qnchoiceId[i] = this.info[i].qnchoiceId
      }
      if(this.total !== this.xx.answerOut.length)
      {
        this.$message.error("您还有没有完成问卷")
        return
      }
      console.log(this.xx)
      //  alert(this.info[0].qnchoiceId)
      // alert(this.xx.qnchoiceId[0])
      //
      // alert(this.info[1].qnchoiceId)
      // alert(this.xx.qnchoiceId[1])
      //JSON.stringify(this.xx)
      // let pas = new URLSearchParams()
      // pas.append('qnchoiceId',this.xx.qnchoiceId)
      //
      // let pas2 = new URLSearchParams()
      // pas2.append('answerOut', this.xx.answerOut)


      // {
      //   params :{
      //     qnId : this.xx.qnId,
      //         id : this.xx.Id,
      //         answerId: this.xx.answerId,
      //         qnchoiceId :this.xx.qnchoiceId,
      //         answerOut :this.xx.answerOut
      //   }
      //JSON.stringify(this.xx)

      this.request.get("/qn/submitQn",
          {
            params :{
              out : JSON.stringify(this.xx)
            }
      }
      ).then(res => {
        console.log(res)
         this.$message.success("提交成功")
        this.$router.push("/answer/answerView")
        // for (var i = 0; i < this.info.length; i++) {
        //   this.xx.out.qnchoiceId[i] = this.info[i].qnchoiceId
        // }
      })



    },

    loaduser(){
      this.request.get("/qn/loadUserName", {
        params: {
          Id: this.qnInfo.qnUser,
        }
      }).then(res => {
        console.log(res)
        this.userName= res.data[0].name
        // for (var i = 0; i < this.info.length; i++) {
        //   this.xx.out.qnchoiceId[i] = this.info[i].qnchoiceId
        // }
      })
    },
    load() {
      this.request.get("/qn/loadQn", {
        params: {
          qnId: this.qnInfo.qnId,
        }
      }).then(res => {
        console.log(res)
        this.info = res
        this.total = res.length
        //alert(this.total)
        // alert(this.info)

      })

    },

    // cancel() {
    //   this.dialogFormVisible = false
    // },
    // addChoice() {
    //   this.dialogFormVisible = true
    // },

    // addSave() {
    //   // alert(this.groupInfo.groupId)
    //   //  alert(this.answerId)
    //   this.request.get("/qn/addChoice", {
    //     params: {
    //       qnId: this.qnInfo.qnId,
    //       qnchoiceWord:this.qnchoiceWord,
    //       qnchoiceA:this.qnchoiceA,
    //       qnchoiceB:this.qnchoiceB,
    //       qnchoiceC:this.qnchoiceC
    //     }
    //   }).then(res => {
    //     console.log(res)
    //     if (res.code == "200") {
    //       //this.$message.success("成功")
    //       this.dialogFormVisible = false
    //       this.load()
    //     } else if (res.code == "400") {
    //       // this.$message.error("不存在")
    //     } else if (res.code == "401") {
    //       //this.$message.success("已存在")
    //     }
    //
    //
    //   })
    //
    // }
  }

}
</script>

<style scoped>

</style>