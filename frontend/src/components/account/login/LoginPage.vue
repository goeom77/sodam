<template>

      <div id="cover">
        <div class="background">
        </div>
        <div id="LoginDetail">
          <LoginDetail/>
        </div>
      </div>
</template>

<script>
import LoginDetail from '@/components/account/login/LoginDetail.vue'
export default {
  name:'LoginPage',
  components: {
    LoginDetail,
  }
}
</script>

<style>
#cover{
  width: 1255px;
  margin: 0 auto;
  text-align: center;
}
.check {
  /* display: inline-block; */
}

.background {
  float:left; 
  width: 800px;
  height: 608px;
  background-image: url(../../../assets/images/loginImage.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
}

#LoginDetail {
  float:left; 
  width:455px;
  height: 608px;
  background-color: #FCFAEF;
  margin-right: 0px;
  justify-content: center;
}

#fh5co-main {
  width: 80%;
  float: right;
  -webkit-transition: 0.5s;
  -o-transition: 0.5s;
  transition: 0.5s;
}
@media screen and (max-width: 1200px) {
  #fh5co-main {
    width: 70%;
  }
}
@media screen and (max-width: 768px) {
  #fh5co-main {
    width: 100%;
  }
}
#fh5co-main .fh5co-narrow-content {
  position: relative;
  width: 80%;
  margin: 0 auto;
  padding: 4em 0;
}
@media screen and (max-width: 768px) {
  #fh5co-main .fh5co-narrow-content {
    width: 100%;
    padding: 3.5em 1em;
  }
}
</style>