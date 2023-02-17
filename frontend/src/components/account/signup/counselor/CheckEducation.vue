<template>
  <table>
    <caption @click="checkData">학력</caption>
      <thead>
        <tr>
          <th>학위</th>
          <th>학교</th>
          <th>전공</th>
          <th>졸업 여부</th>
          <th>증명서 사본</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <div class="haveBorder">
              <select v-model="degree">
                <option value="학사">학사</option>
                <option value="석사">석사</option>
                <option value="박사">박사</option>
              </select>
            </div>
          </td>
          <td>
            <!-- 학교 -->
            <div class="haveBorder">
              <input type="text" name="eduSchool" v-model="school" placeholder="학교명">
            </div>
          </td>
          <td>
            <!-- 전공 -->
            <div class="haveBorder">
              <input type="text" name="eduMajor" v-model="major" placeholder="전공명">
            </div>
          </td>
          <td>
            <!-- 졸업여부 -->
            <div class="haveBorder">
              <select v-model="is_graduate">
                <option value="재학">재학</option>
                <option value="졸업">졸업</option>
                <option value="수료">수료</option>
              </select>
            </div>
          </td>
          <td>
            <v-file-input
            accept="image/*"
            v-model="this.files"
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
  name:'CheckEducation',
  data(){
    return{
      degree:null,
      school:null,
      major:null,
      is_graduate:null,
      files: null,
    }
  },
  methods:{
    clearFile(){
      if(this.files !== null){
        this.files = null;
      }
    },
    submit(){
      const payload = {

      }
      this.$emit('Education',)
    },
    checkData(){
      console.log(this.degree, this.school, this.major, this.is_graduate,this.files)
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