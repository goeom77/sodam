<template>
  <!-- 상담사용 -->
    <table class="type03">
      <!-- 이름 -->
      <tr>
        <th scope="row">이름</th>
        <td>{{ this.name}} </td>
      </tr>
      <!-- 나이 -->
      <tr>
        <th scope="row">나이</th>
        <td>
          <select id="form_need2" class="form-control" v-model="age">
            <option 
              v-for="(item, index) in ageList"
              :key="index"
              :value="item.value"
              >{{ item.name }}</option>
          </select>
        </td>
      </tr>
      <!-- 전화번호 -->
      <tr>
        <th scope="row">전화번호</th>
        <td>
          <input type="text" name="tel" v-model="this.tel" class="form-control" placeholder="Tel">
        </td>
      </tr>
      <!-- 이메일 -->
      <tr>
        <th scope="row">E-mail</th>
        <td>
          <input type="text" name="email" v-model="this.email" class="form-control" placeholder="E-mail">
        </td>
      </tr>
      <!-- 프로필 사진  -->
      <tr>
        <th scope="row" @click="checkdata">프로필 사진</th>
        <td>
          <v-file-input
            accept="image/*"
            v-model="this.profileImage"
            label="File input"
          ></v-file-input>
          <button type="button" class="btn btn-outline-dark" @click="OnImage">제출</button>
        </td>
      </tr>
    </table>
    <div style="padding-bottom: 20px;">
      <a style="margin:auto;" class="bn62" @click="clientUpdata">확인</a>
    </div>
</template> 

<script>
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name:'EditInformationClient',
  data(){
    return{
      name: '',
      email: '',
      tel:0,
      profileImage:null,
      id: this.$store.state.payload.id,
      age:null,
      ageList: [
        {name:'9세 이하', value:"UNDER_10"},
        {name:'10대', value:"RANGE_10"},
        {name:'20대', value:"RANGE_20"},
        {name:'30대', value:"RANGE_30"},
        {name:'40대', value:"RANGE_40"},
        {name:'50대', value:"RANGE_50"},
        {name:'60대', value:"RANGE_60"},
        {name:'70대 이상', value:"OVER_70"},
      ],
      
    }
  },

  methods:{
    checkdata(){
      console.log(this.profileImage)
      console.log(this.age)
    },
    OnImage(){
      const formdata = new FormData()
      formdata.append('file', this.profileImage[0])

      return axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/my-page/profile-img/${this.id}`,
        data: formdata,
        headers:{
          'Content-Type': 'multipart/form-data',
        },
      })
      .then(res=>{
        console.log(res)
      })
    },
    getClientInfo() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/client/${this.id}`,
        data:{
          id: this.id
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res => {
        this.name= res.data.name,
        this.email= res.data.email,
        this.tel= res.data.tel
      })  
    },

    clientUpdata() {
      const tel = this.tel
      const email = this.email
      const age = this.age

      axios({
        method: 'put',
        url: `${VUE_APP_API_URL}/api/client/${this.$store.state.payload.id}`,
        data: {
          tel : tel,
          email : email,
          age: age
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then((res) => {
        console.log(res)
        // this.$router.push({
        //   name: 'MainEdit1',
        //   // params: { postId: this.$route.params.postId } 
        // })
      })
      .catch((err) => {
        console.log('업데이트 안된단다 재훈아')
        console.log(err)
      })
    },
  },
  created(){
    this.getClientInfo()


  }
}
</script>

<style>

.type03 {
  width:95%;
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  border-left: 3px solid #369;
  margin : 20px 20px;
}
.type03 th {
  background-color: white;
  width: 30px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #153d73;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  text-align: center;

}
.type03 td {
  width: 359px;
  padding: 10px;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}
#sign_table{
  width: 90%;
}

.ten h1 {
  font-size:34px; font-weight:500; text-transform:uppercase;
  margin-left: 20px;
}
.ten h1:before {
    background-color: #369;
    border-radius: 0.25rem;
    content: '';
    display: block;
    height: 0.25rem;
    width: 42px;
    margin-top: 10px;
}
#form_need2{
  width: 300px;
}
.bn62 {
  color: white;
  text-decoration: none;
  background-color: #1b2f31;
  border-radius: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 3em;
  width: 8em;
  font-size: large;
  font-weight: 600;
}
</style>