
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



    </el-card>

  </div>
</template>

<script>
export default {
  name: "UserCos",

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
    //  dialogFormVisible: false,
   //   picked: {one:"",two:"",three:"",four:""},
      xx: {qnId:'',Id:'',answerId:'',qnchoiceId:[],answerOut:[]},
    //  total:0,
     // ifdone:false,

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
    document.title = '问卷'
    this.xx.answerId =this.man.answerId
    this.xx.qnId = this.qnInfo.qnId
     this.xx.Id = this.qnInfo.qnUser

  },
  methods: {
    submit(){
      this.$message.success("模拟提交完成")
    },
    loaduser(){
      this.request.get("/qn/loadUserName", {
        params: {
          Id: this.qnInfo.qnUser,
        }
      }).then(res => {
        console.log(res)
        this.userName= res.data[0].name

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
      })
    },
  }
}
</script>