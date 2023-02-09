<template>
  <div>
    <div class="d-flex">
      <div>
        <!-- 세션이 있다면 -->
        <div v-if="session">
          <p>안뇽</p>
          <div id="main-video-session" style="width:500px; height:500px; background-color:black">
            <div id="main-video" style="width: 60%; margin: 0 auto">
              <!-- 메인 스트림 메니저의 비디오 -->
<!--                 v-if="!mainOnOff" -->
<!--                 @click="deleteMainVideoStreamManager()" -->
              <UserVideo

                :stream-manager="mainStreamManager"
                :mainStream="true"

              />
            </div>
            <div>
              <!-- 참가자 화면 -->
              <UserVideo
                class="col-md-4"
                v-if="!isScreenShared"
                :stream-manager="publisher"
                @click="updateMainVideoStreamManager(publisher)"
              />
              <!--자기 -->
              <UserVideo
                class="col-md-4"
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
                @click="updateMainVideoStreamManager(sub)"
              />
              <!-- 다른 참가자 -->
            </div>
          </div>
        </div>
        <div v-else>
          <div v-if="common_code==1">
            <!-- 상담사이면 -->
            <v-btn
              @click="joinSession()"
            >상담하기</v-btn>
          </div>
          <div v-if="common_code==2">
            <!-- 고객이면 -->
          </div>
        </div>
        <div >
          <div v-if="session">
            <div style="align-content-center">
              <!-- microphone 버튼 설정 -->
              <div v-if="audio === true">
                <button
                  type="button"
                  id="buttonLeaveSession"
                  @click="muteAudio(), muteAudio2()"
                >
                  <i class="fa-solid fa-microphone"></i>
                  <span class="footerBtnText">{{ audioMsg }}</span>
                </button>
                <!-- 마이크 on/off 버튼 -->
              </div>
              <div v-else class="roomFun buttomMenu">
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="muteAudio()"
                >
                  <i class="fa-solid fa-microphone-slash"></i>
                  <span class="footerBtnText">&nbsp{{ audioMsg }}</span></button
                ><!-- 마이크 on/off 버튼 -->
              </div>

              <!-- video 버튼 설정 -->
              <div v-if="video === true" class="buttomMenu">
                <v-btn
                  class="btn btn-large btn-danger footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="muteVideo()"
                >
                  <i></i>
                  <span class="footerBtnText">&nbsp{{ videoMsg }}</span>
                </v-btn>
              </div>
              <div v-else>
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="muteVideo()"
                >
                  <i class="fa-solid fa-video-slash"></i>
                  <span class="footerBtnText">&nbsp{{ videoMsg }}</span>
                </button>
              </div>

              <!-- 화면공유 버튼 설정 -->
              <div v-if="sharing === true" class="buttomMenu">
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="startScreenSharing()"
                >
                  <i class="fa-solid fa-tv"></i>
                  <span class="footerBtnText">&nbsp화면공유</span>
                </button>
                <!-- 나가기 버튼 -->
              </div>

              <!-- 화면공유 중지 버튼 설정 -->
              <div v-else class="buttomMenu">
                <button
                  class="btn btn-large btn-danger footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="leaveSessionForScreenSharing()"
                >
                  <i class="fa-solid fa-tv"></i>
                  <span class="footerBtnText">&nbsp공유중지</span>
                </button>
                <!-- 나가기 버튼 -->
              </div>

              <div class="buttomMenu">
                <button
                  class="btn btn-large btn-default footerBtn"
                  type="button"
                  id="buttonLeaveSession"
                  @click="leaveSession()"
                >
                  <i class="fa-solid fa-door-closed"></i>
                  <span class="footerBtnText">&nbsp나가기</span>
                </button>
                <!-- 나가기 버튼 -->
              </div>
            </div>
          </div>
        </div>
        <!-- #session-footer-wrap -->
      </div>  
    </div>
  </div>
</template>

<script>
import UserVideo from "@/components/video/UserVideo.vue"
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
axios.defaults.headers.post["Content-Type"] = "application/json";
const VUE_APP_API_URL = process.env.VUE_APP_API_URL
const APPLICATION_SERVER_URL = "https://i8e103.p.ssafy.io:8443"
const OPENVIDU_SERVER_SECRET = "SODAM";
export default {
  name: "VideoPage",
  components: {
    UserVideo,
  },
  props: ['id'],
  data() {
    return {
      // 1이면 상담사 2이면 상담자
      common_code: undefined,
      // 화면 공유
      // OVForScreenShare: undefined,
      // sessionForScreenShare: undefined,
      // sharingPublisher: undefined,
      // sharing: true,
      // spublisher: undefined,
      // sminStreamManager: undefined,
      // isScreenShared: false,
      mainOnOff: false,
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
      audioEnabled: false,
      videoEnabled: false,
      audio: false,
      video: false,
      audioMsg: "마이크 OFF",
      videoMsg: "비디오 OFF",
      messages: "",
      userId: "",
    };
  },
  created() {
    this.userInfo();
    if (this.video == true) this.videoMsg = "비디오 OFF";
    else this.videoMsg = "비디오 ON";
    if (this.audio == true) this.audioMsg = "마이크 OFF";
    else this.audioMsg = "마이크 ON";
    this.mySessionId = this.generateRandomString(10);
    this.myUserName = this.participant;
  },
  mounted() {
    // this.preventBack();
    // window.addEventListener("beforeunload", this.unLoadEvent);
    // window.addEventListener("scroll", this.updateScroll);
  },
  // beforeUnmount() {
    // window.removeEventListener("beforeunload", this.unLoadEvent);
  // },
  methods: {
    // 임의 상수 만들기
    generateRandomString(num) {
      const characters ='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
      let result = '';
      const charactersLength = characters.length;
      for (let i = 0; i < num; i++) {
          result += characters.charAt(Math.floor(Math.random() * charactersLength));
      }
      return result;
    },
    // 그룹코드 불러오기
    userInfo() {
      this.common_code = this.$store.state.payload.common_code
    },
    // preventBack: function () {
    //   const thisObject = this;
    //   history.pushState(null, null, location.href);
    //   window.onbeforeunload = null;
    //   window.onpopstate = function () {
    //     alert("나가기 버튼을 이용해 주세요");
    //     history.go(1);
    //   };
    // },
    // setCodestdin(stdin) {
    //   this.stdin = stdin;
    //   // console.log(">>>>emit check");
    //   this.sendStdin();
    // },
    // setResult(result) {
    //   this.resultCom = result;
    //   // console.log(">>>>emit check");
    //   this.sendCompile();
    // },
    // ...mapMutations(["SET_RELOADCHECK"]),
    // scrollToUp() {
    //   window.scrollTo(0, 0);
    // },
    // scrollToDown() {
    //   window.scrollTo(0, 10000);
    // },
    // updateScroll() {
    //   this.scrollPosition =
    //     window.scrollY || document.documentElement.scrollTop;
    //   // console.log(this.scrollPosition);
    // },
    // unLoadEvent: function (event) {
    //   if (this.canLeaveSite) return;
    //   event.preventDefault();
    //   event.returnValue = "";
    // },

    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();
      // --- Init a session ---
      this.session = this.OV.initSession();
      // --- Specify the actions when events take place in the session ---
      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });
      // On every Stream destroyed...
      // this.session.on("streamDestroyed", ({ stream }) => {
      //   const index = this.subscribers.indexOf(stream.streamManager, 0);
      //   if (index >= 0) {
      //     this.subscribers.splice(index, 1);
      //   }
      // });
      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });
      //컴파일 결과 전송
      // this.session.on("signal:result-com", (event) => {
      //   console.log("session test");
      //   var compile = event.data;
      //   this.propcompile = compile;
      // });
      // this.session.on("signal:stdin", (event) => {
      //   var stdin = event.data;
      //   this.propstdin = stdin;
      // });
      // --- Connect to the session with a valid user token ---
      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: this.audio, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: this.video, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });
            console.log("publisher: ###",publisher)
            this.mainStreamManager = publisher;
            this.publisher = publisher;
            // --- Publish your stream ---
            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });
      window.addEventListener("beforeunload", this.leaveSession);
    },
    leaveSession() {
      this.SET_RELOADCHECK(false);
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      this.OVForScreenShare = undefined;
      // this.sharingPublisher = undefined;
      window.removeEventListener("beforeunload", this.leaveSession);
      this.$router.push({
        name: "StudyHome",
        params: { studyPk: this.roomStudyNo },
      });
    },
    // 컴파일 결과 전송
    // sendCompile() {
    //   if (this.resultCom != "") {
    //     this.session
    //       .signal({
    //         data: JSON.stringify(this.resultCom),
    //         to: [],
    //         type: "result-com",
    //       })
    //       .then(() => {})
    //       .catch((error) => {
    //         console.error(error);
    //       });
    //   }
    // },
    // 컴파일 결과 전송
    // sendStdin() {
    //   if (this.stdin != "") {
    //     this.session
    //       .signal({
    //         data: this.stdin,
    //         to: [],
    //         type: "stdin",
    //       })
    //       .then(() => {})
    //       .catch((error) => {
    //         console.error(error);
    //       });
    //   }
    // },
    muteVideo() {
      this.videoEnabled = !this.videoEnabled;
      this.video = !this.video;
      if (this.video == true) this.videoMsg = "비디오 OFF";
      else this.videoMsg = "비디오 ON";
      this.publisher.publishVideo(this.videoEnabled);
    },
    muteAudio() {
      if (this.sharing == true) {
        this.audioEnabled = !this.audioEnabled;
        this.audio = !this.audio;
        if (this.audio == true) this.audioMsg = "마이크 OFF";
        else this.audioMsg = "마이크 ON";
        this.publisher.publishAudio(this.audioEnabled);
      }
    },
    // muteAudio2() {
    //   if (this.sharing == true) {
    //     this.spublisher.publishAudio(this.audioEnabled);
    //   }
    // },
    // updateMainVideoStreamManager(stream) {
    //   this.mainOnOff = true;
    //   if (this.mainStreamManager === stream) return;
    //   this.mainStreamManager = stream;
    // },
    // deleteMainVideoStreamManager() {
    //   //화면 크게 한거 지우기.
    //   this.mainOnOff = false;
    // },
    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */
    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },
    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${APPLICATION_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => {
            sendbackid(data.id);
            resolve(data.id);
            }
          )
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${APPLICATION_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${APPLICATION_SERVER_URL}Click OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${APPLICATION_SERVER_URL}"`
                )
              ) {
                location.assign(`${APPLICATION_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },
    sendbackid(session) {
      axios({
        method:'post',
        url: `${VUE_APP_API_URL}/api/room`,
        data:{
          sessionId:this.id,
          openviduId:session
        }
      })
      .then(res=>{
        this.showData = res.data.id
      })
    },
    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${APPLICATION_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
    // 화면 공유 부분
    // startScreenSharing() {
    //   this.OVForScreenShare = new OpenVidu();
    //   this.sessionForScreenShare = this.OVForScreenShare.initSession();
    //   var mySessionId = this.mySessionId;
    //   this.getToken(mySessionId).then((token) => {
    //     this.sessionForScreenShare
    //       .connect(token, { clientData: this.myUserName })
    //       .then(() => {
    //         this.spublisher = this.OVForScreenShare.initPublisher(undefined, {
    //           audioSource: true,
    //           videoSource: "screen",
    //           publishAudio: this.audio, // Whether you want to start publishing with your audio unmuted or not
    //           publishVideo: true,
    //           resolution: "1280x720",
    //           frameRate: 30,
    //           insertMode: "APPEND",
    //           mirror: false,
    //         });
    //         // console.log("publisher",this.spublisher);
    //         this.spublisher.once("accessAllowed", () => {
    //           try {
    //             console.log("subscriber >>>>> ", this.subscribers);
    //             this.isScreenShared = true;
    //             this.session.unpublish(this.publisher); // 송출하고 있는거 중단 (안하면 에러) -- 세션을 없앤다는 뜻.
    //             this.mainStreamManager = undefined;
    //             this.OV = undefined;
    //             this.sharing = !this.sharing; // 화면 공유 버튼에서 중지 버튼으로 change toggle
    //             const constraints = {
    //               width: { min: 640, ideal: 1280 },
    //               height: { min: 480, ideal: 720 },
    //               advanced: [
    //                 { width: 1920, height: 1280 },
    //                 { aspectRatio: 1.333 },
    //               ],
    //             };
    //             this.spublisher.stream
    //               .getMediaStream()
    //               .getVideoTracks()[0]
    //               .applyConstraints(constraints, () => {}),
    //               this.spublisher.stream
    //                 .getMediaStream()
    //                 .getVideoTracks()[0]
    //                 .addEventListener("ended", () => {
    //                   console.log('User pressed the "Stop sharing" button');
    //                   this.leaveSessionForScreenSharing();
    //                   this.isScreenShared = false;
    //                 });
    //           } catch (error) {
    //             console.error("Error applying constraints: ", error);
    //           }
    //         });
    //         this.spublisher.once("accessDenied", () => {
    //           console.warn("ScreenShare: Access Denied");
    //         });
    //         this.mainStreamManager = this.spublisher;
    //         this.sharingPublisher = this.spublisher;
    //         this.sessionForScreenShare.publish(this.sharingPublisher);
    //       })
    //       .catch((error) => {
    //         console.warn(
    //           "There was an error connecting to the session:",
    //           error.code,
    //           error.message
    //         );
    //       });
    //   });
    //   //window.addEventListener('beforeunload', this.leaveSessionForScreenSharing)
    // },
    // leaveSessionForScreenSharing() {
    //   // 화면 공유 중지
    //   this.sharing = !this.sharing; // 화면 공유 버튼에서 중지 버튼으로 change toggle
    //   this.isScreenShared = false;
    //   var mySessionId = this.mySessionId;
    //   console.log(mySessionId); // 제대로있고.
    //   this.sessionForScreenShare.unpublish(this.spublisher); // 송출하고 있는거 중단 (안하면 에러)
    //   //  if (this.sessionForScreenShare) this.sessionForScreenShare.disconnect();
    //   this.sessionForScreenShare = undefined;
    //   this.smainStreamManager = undefined;
    //   this.sharingPublisher = undefined;
    //   this.spublisher = undefined;
    //   this.OVForScreenShare = undefined;
    //   this.session.publish(this.publisher).then(() => {
    //     // 송출하기
    //     this.publisher(this.publisher);
    //   });
    //   window.removeEventListener(
    //     "beforeunload",
    //     this.leaveSessionForScreenSharing
    //   );
    // },
  },
  // stopScreenShare() {
  //   this.sharing = !this.sharing;
  //   var mySessionId = this.mySessionId;
  //   console.log(mySessionId);
  //   window.removeEventListener(
  //     "beforeunload",
  //     this.leaveSessionForScreenSharing
  //   );
  // },
  // watch: {
  //   messages() {
  //     this.$nextTick(() => {
  //       let msg = this.$refs.messages;
  //       msg.scrollTo({ top: msg.scrollHeight, behavior: "smooth" });
  //     });
  //   },
  //   resultCom() {
  //     this.resultCom;
  //   },
  // },

};
</script>