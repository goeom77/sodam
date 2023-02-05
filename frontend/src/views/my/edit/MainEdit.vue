<template>
  <div>
    <v-card class="Card">
      <v-tabs
        v-model="tab"
        bg-color="transparent"
        color="grey"
        grow
      >
        <v-tab value="one">
          회원 정보 수정
        </v-tab>
        <v-tab value="two">
          비밀번호 변경
        </v-tab>
        <v-tab value="three">
          회원 탈퇴
        </v-tab>
      </v-tabs>
      
      <v-window v-model="tab">
        <v-window-item value="one">
          <EditInformation/>
        </v-window-item>
        
        <v-window-item value="two">
          <ChangePassword/>
        </v-window-item>
        
        <v-window-item value="three">
          <DeleteAccount/>
        </v-window-item>
        
      </v-window>
    </v-card>
  </div>
</template>

<script>
import ChangePassword from '@/views/my/edit/ChangePassword.vue'
import EditInformation from '@/views/my/edit/EditInformation.vue'
import DeleteAccount from '@/views/my/edit/DeleteAccount.vue'


export default {
  name:'MainEdit',
  components:{
    ChangePassword,
    EditInformation,
    DeleteAccount
  },
  data(){
    return{
      tab:null
    }
  }

}
</script>

<style>

</style>