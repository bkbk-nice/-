<template>
  <el-row>
    <el-col :span="12">
      <el-card class="box-card" style="width: 500px" v-bind:style="{ background: value}">

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

          <el-button type="primary" style="float: right;display: inline-block;  padding:10px " @click="gotoo">更 多</el-button>

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
                style="height: 250px;margin-left: 20px" class="text item">
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
    </el-col>

    <el-col :span="12">
      <el-card class="box-card" shadow="hover" body-style=""
               style="overflow-y:scroll;overflow-x:scroll;height:100%;padding: 18px 0; width: 550px;"
               header="问卷统计"  >
          <div id="main" style="width: 500px;height: 400px">
           </div>
      </el-card>
    </el-col>
  </el-row>


</template>

<script>



import * as echarts from 'echarts';

export default {
  name: "UserQnShow",
  data(){
    return{
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
      stainfo:[],
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
  mounted() {
    var app = {};

    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option;

    this.request.get("/qn/sta", {
      params: {
        qnId: this.qnInfo.qnId,
      }
    }).then(res => {
      console.log(res.data)
      //
      // this.stainfo= res.data
      for(var i = 0 ;i <res.data.staNum.length;i++){
        // alert(JSON.stringify( this.stainfo))
        option.dataset.source.push({ product:   res.data.staNum[i] , A: res.data.staA[i], B: res.data.staB[i], C: res.data.staC[i] })
      }
      option && myChart.setOption(option);
    })


    option = {
      legend: {},
      tooltip: {},
      dataset: {
        dimensions: ['product', 'A', 'B', 'C'],
        source: [
          // { product: '1', A: 43, B: 85, C: 93 },
          // { product: '2', A: 83, B: 73, C: 55 },
          // { product: '3', A: 86, B: 65, C: 82},
          // { product: '4', A: 72, B: 53, C: 39 }
        ]
      },
      xAxis: { type: 'category' },
      yAxis: {},
      // Declare several bar series, each will be mapped
      // to a column of dataset.source by default.
      series: [{ type: 'bar' ,  barWidth: 15,}, { type: 'bar',  barWidth: 15, }, { type: 'bar',  barWidth: 15, }],

      dataZoom: {
        show: true, // 为true 滚动条出现
        realtime: true,
        type:'slider', // 有type这个属性，滚动条在最下面，也可以不行，写y：36，这表示距离顶端36px，一般就是在图上面。
        height: 20, // 表示滚动条的高度，也就是粗细
        start: 20, // 表示默认展示20%～80%这一段。
        end: 80
      }
    };

    // for(var i = 0 ;i <2;i++){
    //   option.dataset.source.push({ product: i , A: 33, B: 44, C:55 })
    // }

    // alert("11")
    // for(var i = 0 ;i <5;i++){
    //   alert(JSON.stringify( this.stainfo))
    //   // option.dataset.source.push({ product:  this.stainfo.staNum[i] , A: this.stainfo.staA[i], B: this.stainfo.staB[i], C: this.stainfo.staC[i] })
    //   option.dataset.source.push({ product:  i , A: i, B: i, C: i })
    // }


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

    gotoo(){

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

<style scoped>

</style>