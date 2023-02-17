<template>
  <div v-if="(limit-1)*10 <= index && index < limit * 10">
    <div class="container" style="border-bottom: 1px solid #ccc;">
      <div class="row" @click="moveTo" style="cursor: pointer">
        <div class="col-1" style="text-align: center; margin:auto;" id="qnaId">
          {{ Noticearticle.id }}
        </div>
        <div class="col-8" style="margin:auto;">
          <div style="font-size:20px; font-weight: 600;">
            {{ Noticearticle.title }}
          </div>
          {{ Noticearticle.writerId }} ID
        </div>
        <div class="col-1" style="text-align: center; margin:auto;">
          {{ Noticearticle.views }}
        </div>
        <div class="col-2" style="text-align: center; margin:auto;">
          {{ this.date }}
        </div>
      </div>
    </div>
  </div>
  <!-- <div id="NoticeViewListItem" class="flex-container space-between" v-if="(limit-1)*5 <= index && index < limit * 5">
    <div>
      <h5>{{ Noticearticle.postId }}</h5>
        </div>
    <div >
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'NoticeDetailView', 
            params: { id: Noticearticle.id } 
          }"
        >
            {{ Noticearticle.title }}
        </router-link>
        </div>
      <div>
        <p>{{ Noticearticle.adminId }}  {{ Noticearticle?.createdAt }}</p>
        </div>
        </div>
    <div >
      <h3>{{ Noticearticle.views }}</h3>
      </div>
    <hr> 

  </div> -->
</template>

<script>

export default {
  name: 'NoticeHelpViewListItem',
  props: {
    Noticearticle: Object,
    index: Number,
    limit: Number,
  },
  data(){
    return{
      date: this.Noticearticle.createdAt.split('T')[0]
    }
  },
  methods: {
    moveTo(){
      this.$router.push({name:'NoticeDetailView', params: { id: this.Noticearticle.id }  })
    },
  }
}
</script>

<style>
#NoticeViewListItem {
  margin-left: 60px;
  margin-right: 60px;
}

.flex-container{
  display: flex;
  border-radius: 10px;
  margin:5px;
  background-color:white;
}
.HelpItem{
  border-bottom: 1px solid #ccc;
  padding: 10px
}
.helpdetail{
  padding-top:1em;
  padding-bottom:1em;
}
#helpID{
  padding:20px;
}

.flex-container.flex-start{justify-content: flex-start;}
.flex-container.flex-end{justify-content: flex-end;}
.flex-container.center{justify-content: center;}
.flex-container.space-around{justify-content: space-around;}
.flex-container.space-between{justify-content: space-between;}
.flex-container.space-evenly{justify-content: space-evenly;}
</style>

