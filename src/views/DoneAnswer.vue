

<template>

  <div>

    <div>
      <h1>
         问卷回答结果
      </h1>
    </div>

    <el-card class="box-card" style="background-color: bisque">


      <h1 slot="header" class="clearfix" style="height: 50px">
        <span>{{ '问卷名称:  ' + this.qnInfo.qnName }}</span>
        <!--             <el-button type="primary" style="float: right;  padding:20px " @click="submit">提交</el-button>-->
      </h1>


      <h1>
        <span>{{ '创建者:  ' + this.userName }}</span>
        <span  style="margin-left: 100px">{{ '答者id:  ' + this.answer.answerId }}</span>
        <span   style="margin-left: 100px" >{{ '答者名:  ' + this.answer.answerName }}</span>
        <el-divider></el-divider>
      </h1>


      <h1>
        <span style="height: 50px">{{ '问卷回顾:  ' }}</span>
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
            {{'答者回答：'+ item.answerOut }}
          </h3>

          <h3 v-else >
            <div id="app" style="height: 260px ;width: 400px">
              <br>
              <label   >{{'A: ' + item.qnchoiceA }}</label><br>

              <label    >{{ 'B:' + item.qnchoiceB }}</label><br>

              <label  >{{ 'C: ' + item.qnchoiceC }}</label><br>

              <p>答者选择：{{item.answerOut}}</p>
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
  name: "DoneAnswer",
  data() {
    return {
      userName:'',
      // qnchoiceWord:'',
      // qnchoiceA:'',
      // qnchoiceB:'',
      // qnchoiceC:'',
      qnInfo: localStorage.getItem("qnId") ? JSON.parse(localStorage.getItem("qnId")) : {},
      answer: localStorage.getItem("answer") ? JSON.parse(localStorage.getItem("answer")) : {},
      info: [],
      //dialogFormVisible: false,
      //xx: {qnId:'',Id:'',answerId:'',qnchoiceId:[],answerOut:[]},
      //total:0,

      json : [{
        qn_id: '',
        id: '',
        answer_id: '',
        qnchoice_id:'',
        answerout:'',
        qnchoice_word:'',
        qnchoice_a:'',
        qnchoice_b:'',
        qnchoice_c:''}
      ]
    }
  },
  created(){

    this.load()
    this.loaduser()
    // alert(info)
    document.title = '问卷'
    // this.xx.answerId =this.man.answerId
    // this.xx.qnId = this.qnInfo.qnId
    // this.xx.Id = this.qnInfo.qnUser

  },
  methods: {


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
      this.request.get("/qn/loadDoneQn", {
        params: {
          qnId: this.qnInfo.qnId,
          id:this.qnInfo.qnUser,
          answerId: this.answer.answerId
        }
      }).then(res => {
        console.log(res.data)
        this.info = res.data
        //  this.info = JSON.parse(res.data)
        // this.total = res.length


      })

    },

  }
}
</script>

<style scoped>

</style>