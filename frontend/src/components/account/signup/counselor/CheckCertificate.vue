<template>
  <table>
    <caption @click="checkData">자격증</caption>
      <thead>
        <tr>
          <th>발행처</th>
          <th>자격 이름</th>
          <th>증명서 사본</th>
        </tr>
      </thead>

      <tbody>
        <tr>
          <td>
            <!-- 발행처 -->
            <div class="haveBorder">
              <input type="text" v-model="certificate_location" placeholder="발행처">
            </div>
          </td>
          <td>
            <!-- 학교 -->
            <div class="haveBorder">
              <input type="text" name="eduSchool" v-model="certificate_name" placeholder="자격 이름 ">
            </div>
          </td>

          <td>
            <v-file-input
            accept="image/*"
            label="File input"
          ></v-file-input>
          </td>
        </tr>
      </tbody>
      <tfoot>
        <td colspan="5" class="tablefoot"></td>
      </tfoot>
    </table>
</template>

<script>
export default {
  name:'CheckCertificate',
  data(){
    return{
      certificate_location:null,
      certificate_name:null,
    }
  },
  methods:{
    checkData(){
      console.log(this.certificate_location, this.certificate_name)
    }
  }
}
</script>

<style>

select {
  width: 100px; /* 원하는 너비설정 */
  padding: .8em .5em; /* 여백으로 높이 설정 */
  font-family: inherit;  /* 폰트 상속 */
  background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; /* 네이티브 화살표 대체 */  
  border: 1px solid #999; 
  border-radius: 0px; /* iOS 둥근모서리 제거 */
  -webkit-appearance: none; /* 네이티브 외형 감추기 */
  -moz-appearance: none;
  appearance: none;
}


table {
  width: 1200px;
  text-align: center;
  border: 1px solid black;
  border-spacing: 1px;
  font-family: 'Cairo', sans-serif;
  margin: auto;
}

caption {
  font-weight: bold;
}

table td {
    padding: 10px;
    background-color: #fff;
}

table th {
    background-color: #333;
    color: #fff;
    padding: 10px;
}

.tablefoot {
    padding: 0;
    border-bottom: 3px solid #F1E7DB;
}
#haveBorder{
  border: solid 1px ;
}
</style>