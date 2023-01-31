<template>
  <table>
    <caption>자격증</caption>
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
          <td><img src="https://i.postimg.cc/Y26YPMSb/zuko.jpg" alt="img"></td>
          <td>Union</td>
          <td>Ashraf</td>
          <td>90</td>
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
      degree:null,
      School:null,
      major:null,
      is_graduate:null,
    }
  }
}
</script>

<style>
table {
    width: 700px;
    text-align: center;
    border: 1px solid #fff;
    border-spacing: 1px;
    font-family: 'Cairo', sans-serif;
  margin: auto;
}

caption {
    font-weight: bold;
}

table td {
    padding: 10px;
    background-color: #eee;
}

table th {
    background-color: #333;
    color: #fff;
    padding: 10px;
}

img {
    width: 90px;
    height: 90px;
}

.view,
.delete {
    border: none;
    padding: 5px 10px;
    color: #fff;
    font-weight: bold;
}

.view {
    background-color: #03A9F4;
}

.delete {
    background-color: #E91E63;
}

.tablefoot {
    padding: 0;
    border-bottom: 3px solid #009688;
}
</style>