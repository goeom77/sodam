<template>
  <div v-if="!session">
    <div>
      <p class="text-center">
        <!--
          시작 시간 30분 전이면 : 상담 시간이 아닙니다. 
          종료 시간 후 : 종료된 상담입니다.
          색깔 회색
          -->
        <v-btn 
          @click="joinSession()"
          text
          color="red accent-4"  
        >
          상담 시작
        </v-btn>
      </p>
    </div>
  </div>
  </template>
    
<script>
  // 세션 초기화 하는 용도로 불러온다.
  import { OpenVidu } from "openvidu-browser";
  import axios from "axios";
  import UserVideo from "@/components/video/UserVideo.vue";
  // 헤더 세션 만들때 쓴다.
  axios.defaults.headers.post["Content-Type"] = "application/json";
  // app서버 url 4443? 8443?
  const APPLICATION_SERVER_URL = "https://i8e103.p.ssafy.io:8443";
  const OPENVIDU_SERVER_SECRET = "SODAM";
  export default {
    name: "CounselorPage",
    components: {
      UserVideo,
    },
    data() {
      return {
        // 오픈비두 데이터
        OV: undefined,
        session: undefined,
        mainStreamManager: undefined,
        // 상담사
        publisher: undefined,
        // 상담자
        subscribers: [],
  
        // Join form
        // 화상통화
        mySessionId: "SessionA",
        // 참가자의 닉네임
        myUserName: "Participant" + Math.floor(Math.random() * 100),
      };
    },
  
    methods: {
      joinSession() {
        // 객체 불러오기
        this.OV = new OpenVidu();
  
        // 세션 지정
        this.session = this.OV.initSession();
  
        // 세션 만들기
  
        // On every new Stream received...
        this.session.on("streamCreated", ({ stream }) => {
          const subscriber = this.session.subscribe(stream);
          this.subscribers.push(subscriber);
        });
  
        // 컴파일 결과 전송
        this.session.on("exception", ({ exception }) => {
          console.warn(exception);
        });
  
        // --- 4) Connect to the session with a valid user token ---
  
        // Get a token from the OpenVidu deployment
        this.getToken(this.mySessionId).then((token) => {
  
          // First param is the token. Second param can be retrieved by every user on event
          // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
          this.session.connect(token, { clientData: this.myUserName })
            .then(() => {
  
              // --- 5) Get your own camera stream with the desired properties ---
  
              // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
              // element: we will manage it on our own) and with the desired properties
              let publisher = this.OV.initPublisher(undefined, {
                audioSource: undefined, // The source of audio. If undefined default microphone
                videoSource: undefined, // The source of video. If undefined default webcam
                publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                publishVideo: true, // Whether you want to start publishing with your video enabled or not
                resolution: "640x480", // The resolution of your video
                frameRate: 30, // The frame rate of your video
                insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                mirror: false, // Whether to mirror your local video or not
              });
  
              // Set the main video in the page to display our webcam and store our Publisher
              this.mainStreamManager = publisher;
              this.publisher = publisher;
  
              // --- 6) Publish your stream ---
  
              this.session.publish(this.publisher);
            })
            .catch((error) => {
              console.log("There was an error connecting to the session:", error.code, error.message);
            });
        });
  
        window.addEventListener("beforeunload", this.leaveSession);
      },
  
      /**
       * --------------------------------------------
       * GETTING A TOKEN FROM YOUR APPLICATION SERVER
       * --------------------------------------------
       * The methods below request the creation of a Session and a Token to
       * your application server. This keeps your OpenVidu deployment secure.
       * 
       * In this sample code, there is no user control at all. Anybody could
       * access your application server endpoints! In a real production
       * environment, your application server must identify the user to allow
       * access to the endpoints.
       * 
       * Visit https://docs.openvidu.io/en/stable/application-server to learn
       * more about the integration of OpenVidu in your application server.
       */
      async getToken(mySessionId) {
        const sessionId = await this.createSession(mySessionId);
        return await this.createToken(sessionId);
      },
  
      async createSession(sessionId) {
        const response = await axios.post(APPLICATION_SERVER_URL + 'api/sessions', { customSessionId: sessionId }, {
          headers: { 'Content-Type': 'application/json', },
        });
        return response.data; // The sessionId
      },
      async createToken(sessionId) {

        const response = await axios.post(APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections', {}, {
          auth: {
                  username: "OPENVIDUAPP",
                  password: OPENVIDU_SERVER_SECRET,
                },
          headers: { 'Content-Type': 'application/json', },
        });
        return response.data; // The token
      },
    },
  }
</script>
    
<style>
#main-video {
  height: 100%;
  /* margin-top: -70px; */
  margin-top: 0px !important;
  min-width: 1050px;

  /* background-color: black; */
}
#main-video-container {
  z-index: 9;
}
#main-video-container::after {
  position: absolute;
  width: 100%;
  height: 100%;
  margin: 0 auto;
  content: "";
}
</style>