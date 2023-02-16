<template>
  <div class="helplist" v-if="(limit-1)*5 <= index && index < limit * 5">
    <div class="container">
      <div class="row" @click="moveTo" style="cursor: pointer">
      
        <div class="col-1" style="text-align: center; margin:auto;" id="helpID">
          {{ Boardarticle.id }}
        </div>
        <div class="col-8" style=" margin:auto; padding-left:30px">
          {{ Boardarticle.title }}
          <br>
          
            {{ Boardarticle.adminId }}ID
          
        </div>
        <div class="col-1" style="text-align: center; margin:auto;">
          {{ Boardarticle.views }}
        </div>
        <div class="col-2" style="text-align: center; margin:auto;">
          {{ this.date }}
        </div>

      </div>

    </div>
    <!-- <div>
      <h5>{{ Boardarticle.postId }}</h5>
        </div>
    <div >
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'BoardDetailView', 
            params: { id: Boardarticle.id } 
          }"
        >
            {{ Boardarticle.title }}
        </router-link>
        </div>
      <div>
        <p>{{ Boardarticle.adminId }}  {{ Boardarticle?.createdAt }}</p>
        </div>
        </div>
    <div >
      <h3>{{ Boardarticle.views }}</h3>
      </div>
    <hr> -->

  </div>
</template>

<script>

export default {
  name: 'NoticeHelpViewListItem',
  props: {
    Boardarticle: Object,
    index: Number,
    limit: Number,
  },
  data(){
    return{
      date: this.Boardarticle.createdAt.split('T')[0]
    }
  },
  methods: {
    moveTo(){
      this.$router.push({name:'BoardDetailView', params: { id: this.Boardarticle.id }  })
    },
  }
}
</script>

<style>
#BoardViewListItem {
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

