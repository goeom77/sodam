<template>
  <div class="container d-flex justify-content-center">
    <div v-if="!session">
      <!-- 모달을 띄워줄것 녹화할 수 있다는 것 -->
      <div style="height:100px;"></div>
      <div id="cover">
        <div class="video-background"/>
        <div>
          <h1>SODAM</h1>
          <div>
            <!-- 주의사항 테그 -->
            <v-dialog
              v-model="dialog"
              persistent
            >
              <template v-slot:activator="{ props }">
                <v-btn
                  color="primary"
                  v-bind="props"
                >
                  주의사항
                </v-btn>
              </template>
              <v-card style="width: 40%; margin:auto">
                <div style="height: 20px;"/>
                <v-card-title class="text-h4" style="height:5%">
                  상담, 녹음 및 비밀 보장 동의서
                </v-card-title>
                <v-card-text>
                  <p>  상담자와 내담자는 상담의 효과성을 위해 성실히 노력 할 것이며,</p>
                  <p>내담자의 신상 및 상담 내용에 대하여 녹음 및 쌍방이 비밀을</p>
                  <p>지킬 것을 약속합니다.</p>
                  <p>  상담자는 강의, 교육, 출판, 심리검사, 상담 전문성 향상 훈련,</p>
                  <p>사적 대화 등의 상황에서 내담자의 신원확인이 가능한 정보나 비밀</p>
                  <p>  정보를 공개하지 않을 것을 약속합니다. 내담자는 상담 사전 안내 </p>
                  <p>동의에 동의하겠습니다. </p>
                  <p>단, 다음 사항에 해당하는 내용은 예외입니다.</p>
                  <p>❗ 1. 자신이나 타인의 신체 또는 재산을 해칠 위험이 있는 경우</p>
                  <p>❗ 2. 사회의 안전을 위협하는 경우</p>
                  <p>❗ 3. 아동학대나 성폭력 등 중대한 범죄에 관한 내용을 알게된 경우</p>
                  <p>❗ 4. 법원에서 정보공개의 요청이 있는 경우</p>
                  <p>❗ 5. 감염성이 있는 치명적인 전염병을 가진 확실한 정보가 있는 경우</p>
                  &nbsp;&nbsp;
                  <h5>⭕녹음</h5>
                  <p>1. 상담자가 상담 받는 장면을 녹화하거나 녹음 하는 이유는 내담자의</p>
                  <p>문제 해결에 적극적으로 도움을 주고, 상담내용은 일정기간 기록,</p>
                  <p>보관하기 위한 용도로 활용 됩니다.</p>
                  <p>2. 보존기간은 3년 이내 입니다.</p>
                  <p>3. 그 외의 용도로 활용할 경우는 반드시 내담자의 허락과 동의를</p>
                  <p>받겠습니다.</p>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn
                    color="red-darken-1"
                    variant="text"
                    @click="dialog = false; consultadmit = false"
                  >
                    Disagree
                  </v-btn>
                  <v-btn
                    color="green-darken-1"
                    variant="text"
                    @click="dialog = false; consultadmit = true"
                  >
                    Agree
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            <div>
              <!-- 상담 일정 세션값이 들어오고, 버튼의 위치를 옮기고 나서 진행 -->
              <!-- 유저의 이름을 myUserName으로 넣고, sessionId를 "session" + 키값*100 + 턴으로 생성 -->
              <p>
                <label>Participant</label>
                <input
                  v-model="myUserName"
                  class="form-control"
                  type="text"
                  required
                />
              </p>
              <p>
              <label>Session</label>
                <input
                  v-model="mySessionId"
                  class="form-control"
                  type="text"
                  required
                />
              </p>
              <!-- 상담사이면 -->
              <p class="text-center" v-if="common_code==1">
                <v-btn
                  @click="join()"
                >
                  상담하기
                </v-btn>
              </p>
              <!-- 고객이면 -->
              <p class="text-center" v-if="common_code==2">
                <v-btn
                  @click="join()"
                >
                  상담하기
                </v-btn>
              </p>
              <!-- 관리자이면 -->
              <p class="text-center" v-if="common_code==0">
                <v-btn
                  class="btn btn-lg btn-success"
                  @click="join()"
                >
                  상담하기
                </v-btn>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
      <!-- 주의사항 테그 끝 -->
    <div>
      <div>
        <div>
          <!-- 상담 일정 세션값이 들어오고, 버튼의 위치를 옮기고 나서 진행 -->
          <!-- 유저의 이름을 myUserName으로 넣고, sessionId를 "session" + 키값*100 + 턴으로 생성 -->
          <p>
            <label>Participant</label>
            <input
              v-model="myUserName"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p >
            <label>Session</label>
            <input
              v-model="mySessionId"
              class="form-control"
              type="text"
              required
            />
          </p>
          <!-- 상담사이면 -->
          <p class="text-center" v-if="common_code==1">
            <v-btn
              @click="join()"
            >
              상담하기
            </v-btn>
          </p>
          <!-- 고객이면 -->
          <p class="text-center" v-if="common_code==2">
            <v-btn
              @click="join()"
            >
              상담하기
            </v-btn>
          </p>
          <!-- 관리자이면 -->
          <p class="text-center" v-if="common_code==0">
            <v-btn
              class="btn btn-lg btn-success"
              @click="join()"
            >
              상담하기
            </v-btn>
          </p>
        </div>
      </div>
    </div>
    <!-- 스트림 시작 -->
    <div id="session" v-if="session">
      <!-- session header -->
      <div>
        <nav class="navbar navbar-dark" style="background-color: rgb(65, 65, 67);">
          <form class="container-fluid justify-content-end">
            <button class="btn btn-sm btn-outline-secondary" type="button">신고하기</button>
            <button class="btn btn-sm btn-outline-secondary" type="button" @click="leaveSession">나가기</button>
          </form>
        </nav>
      </div>
      <div style="height:30px"></div>
      <!-- session -->
      <v-row justify="center">
        <v-col cols="4">
          <v-card class="mx-auto" max-width="500">
            <v-container>
              <v-card>
                <user-video :stream-manager="mainStreamManager"/>
                <v-card-actions>
                  <v-btn size="small" color="surface-variant" variant="text" icon="mdi-heart"></v-btn>
                  <v-btn size="small" color="surface-variant" variant="text" icon="mdi-bookmark"></v-btn>
                  <v-btn size="small" color="surface-variant" variant="text" icon="mdi-share-variant"></v-btn>
                </v-card-actions>
              </v-card>
            </v-container>
          </v-card>
        </v-col>
        <v-col cols="4">
          <v-card class="mx-auto" max-width="500">
            <v-container fluid>
              <v-card>
                <user-video
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  v-for="sub in subscribers"
                  :key="sub.stream.connection.connectionId"
                  :stream-manager="sub"
                  @click="updateMainVideoStreamManager(sub)"
                />
                <v-card-actions>
                  <v-btn size="small" color="surface-variant" variant="text" icon="mdi-heart"></v-btn>
                  <v-btn size="small" color="surface-variant" variant="text" icon="mdi-bookmark"></v-btn>
                  <v-btn size="small" color="surface-variant" variant="text" icon="mdi-share-variant"></v-btn>
                </v-card-actions>
              </v-card>
            </v-container>
          </v-card>
        </v-col>
      </v-row>
      <!-- session 끝 -->
      <div style="height:30px"></div>
      <nav class="navbar fixed-bottom" style="background-color: rgb(65, 65, 67);">
        <form class="container-fluid justify-content-center">
          <div>
            <v-btn
              size="x-large"
              @click="videoController"
              color="grey-darken-4"
            >
              <v-icon v-if="videoMute == false" icon="mdi-video-outline" size="x-large"></v-icon>
              <v-icon v-else icon="mdi-video-off-outline" size="x-large"></v-icon>
            </v-btn>
            &nbsp;&nbsp;&nbsp;
            <v-btn
              size="x-large"
              v-if="audioMute == false"
              color="grey-darken-4"
              @click="audioController"
            >
              <v-icon style="color:white" icon="mdi-volume-high" size="x-large"></v-icon>
            </v-btn>
            <v-btn
              size="x-large"
              v-else
              color="grey-darken-4"
              @click="audioController"
            >
              
              <v-icon style="color:white" icon="mdi-volume-off" size="x-large"></v-icon>
            </v-btn>
            &nbsp;&nbsp;&nbsp;
              <!-- 녹음 버튼 -->

            <v-btn
              size="x-large"
              v-if="recordMode == false"
              color="grey-darken-4"
              @click="startRecord"
            >
              <v-icon style="color:red" icon="mdi-record-rec" size="x-large"></v-icon>
            </v-btn>
            <v-btn
              size="x-large"
              v-else
              @click="stopRecord"
            >
            <v-icon icon="mdi-stop" size="x-large"></v-icon>
            </v-btn>
            &nbsp;&nbsp;&nbsp;
            <v-btn
              size="x-large"
              id="buttonLeaveSession"
              color="grey-darken-4"
              @click="leaveSession"
            >
              <v-icon icon="mdi-logout" size="x-large"></v-icon>
            </v-btn>
          </div>
        </form>
      </nav>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/video/UserVideo.vue";
axios.defaults.headers.post["Content-Type"] = "application/json";
const VUE_APP_API_URL = process.env.VUE_APP_API_URL;
const OPENVIDU_SERVER_URL = "https://i8e103.p.ssafy.io:8443";
const OPENVIDU_SERVER_SECRET = "SODAM";
export default {
  name: "VideoPage",
  components: {
    UserVideo,
  },
  data() {
    return {
      consultadmit: false,
      dialog: false,
      // 1이면 상담사 2이면 상담자
      common_code: undefined,
      OV: undefined,
      session: undefined,
      scheduleId: 1,    // 일정 ID (추가)
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      recordMode : false,
      recordNames: [],
      videoMute: false,
      audioMute: false,
      recordMode : false,
      recordNames: [],
      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
      message: "",
      // chatSeq: 0,
      // chatList: [],
    };
  },
  created() {
    this.userInfo();
  },
  mounted() {
    this.preventBack();
    window.addEventListener("beforeunload", this.unLoadEvent);
    window.addEventListener("scroll", this.updateScroll);
  },
  beforeUnmount() {
    window.removeEventListener("beforeunload", this.unLoadEvent);
  },
  methods: {
    preventBack: function () {
      const thisObject = this;
      history.pushState(null, null, location.href);
      window.onbeforeunload = null;
      window.onpopstate = function () {
        this.swal("나가기 버튼을 이용해 주세요");
        history.go(1);
      };
    },
    scrollToUp() {
      window.scrollTo(0, 0);
    },
    scrollToDown() {
      window.scrollTo(0, 10000);
    },
    updateScroll() {
      this.scrollPosition =
        window.scrollY || document.documentElement.scrollTop;
      // console.log(this.scrollPosition);
    },
    unLoadEvent: function (event) {
      if (this.canLeaveSite) return;
      event.preventDefault();
      event.returnValue = "";
    },    
    userInfo() {
      this.common_code = this.$store.state.payload.common_code
    },
    join() {
      if (this.consultadmit == false) {
        this.$swal("주의사항을 확인하세요.")
      } else {
        this.joinSession()
      }
    },
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
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });
      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });
      // Receiver of the message (usually before calling 'session.connect')
      // this.session.on("signal:my-chat", (event) => {
      //   this.chatSeq = this.chatSeq + 1;
      //   var chat = {
      //     chatSeq: this.chatSeq,
      //     timeStamp: Date.now(),
      //     date: new Date(),
      //     creationTime: event.from.creationTime,
      //     user: event.from.data,
      //     message: event.data,
      //   };
      //   this.chatList.push(chat);
      //   // alert("보낸사람 - " + event.from.data + "\n 메시지 - " + event.data);
      //   console.log("Message :" + event.data); // Message
      //   console.log("Connection object of the sender :" + event.from); // Connection object of the sender
      //   console.log("The type of message :" + event.type); // The type of message ("my-chat")
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
              publishAudio: !this.audioMute, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: !this.videoMute, // Whether you want to start publishing with your video enabled or not
              resolution: "500x570", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });
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
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      window.removeEventListener("beforeunload", this.leaveSession);
    },
    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },
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
      if (mySessionId === "SessionA") {
        alert("잘못된 접근입니다.");
        return this.session = undefined;
      }
      else {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      )};
    },
    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
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
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },
    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
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
    videoController() {
      this.videoMute = !this.videoMute;
      this.publisher.publishVideo(this.videoMute);
    },
    audioController() {
      this.audioMute = !this.audioMute;
      this.publisher.publishAudio(this.audioMute);
    },
    startRecord() {
      const recordName = `recording_${this.mySessionId}_${this.recordNames.length + 1}`;
      this.recordNames.push(recordName);
      axios
        .post(
          `${VUE_APP_API_URL}/api/room/recordings/start/${this.mySessionId}`,
          {},
          {
            auth: {
              username: "OPENVIDUAPP",
              password: OPENVIDU_SERVER_SECRET,
            },
          }
        )
        .then((response) => {
          console.log(response)
          this.recordMode = !this.recordMode;
        })
        .catch((error) => console.log(error));
    },
    stopRecord() {
      axios
        .post(
          `${VUE_APP_API_URL}/api/room/recordings/stop/${this.mySessionId}/${this.scheduleId}`,
          {},
          {
            auth: {
              username: "OPENVIDUAPP",
              password: OPENVIDU_SERVER_SECRET,
            },
          }
        )
        .then((response) => {
        console.log(response)
        this.recordMode = !this.recordMode;
        })
        .catch((error) => console.log(error));
    },
    // sendChat() {
    //   if (this.message != "") {
    //     this.session
    //       .signal({
    //         data: this.message, // Any string (optional)
    //         to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
    //         type: "my-chat", // The type of message (optional)
    //       })
    //       .then(() => {
    //         this.message = "";
    //         console.log("Message successfully sent");
    //       })
    //       .catch((error) => {
    //         console.error(error);
    //       });
    //   }
    // },
  },
};
</script>
<style>
#session {
  position: absolute;
  width:100%;
  height:100%;
  background: rgb(26, 25, 31);

}
#video-main-bar-center {
  display:flex;
  justify-content:center;
  align-content:center;
}
.video-background {
  float:left; 
  width: 800px;
  height: 608px;
  background-image: url(../../assets/images/video-front.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.extra-background {
  float:left; 
  width: 500px;
  height: 600px;
  background-image: url(../../assets/images/대기.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
</style>