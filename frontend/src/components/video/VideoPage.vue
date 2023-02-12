<template>
  <div class="container d-flex justify-content-center">
    <div v-if="!session">
      <!-- 모달을 띄워줄것 녹화할 수 있다는 것 -->
      <div id="join-div">
        <img src="#" />
      </div>
      <div>
        <h1>SODAM</h1>
          <v-row justify="center">
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
              <v-card-title class="text-h5" style="height:5%">
                상담 녹화에 대한 안내
              </v-card-title>
              <v-card-text>
                <p>상담의 투명성과 높은 질의 상담을 위해</p>
                <p>면담과정을 녹화할 수 있습니다.</p>
                <p>해당 상담의 녹화본은 오직 상담사만</p>
                <p>볼 수 있으며, 다른 용도로 사용되지 않습니다.</p>
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
        </v-row>
        <!-- 주의사항 테그 끝 -->
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
        <!-- session -->
        <div class="d-flex justify-content-center">
          <div style="float:left">
            <user-video :stream-manager="mainStreamManager" />
          </div>
          <div style="width:10px float:left"></div>
          <div style="float:left">
            <user-video
              v-for="sub in subscribers"
              :key="sub.stream.connection.connectionId"
              :stream-manager="sub"
              @click="updateMainVideoStreamManager(sub)"
            />
          </div>
        </div>
      </div>
      <nav class="navbar fixed-bottom" style="background-color: rgb(65, 65, 67);">
        <div class="container-fluid">
          <div>
            <v-btn
              id="buttonVideo"
              size="large"
              class="ma-2"
              @click="videoController"
            >
              <div v-if="videoMute == false">
                <v-icon icon="mdi-video" size="large"></v-icon>
              </div>
              <div v-else>
                <v-icon icon="mdi-video-off" size="large"></v-icon>
              </div>
            </v-btn>
            <v-btn
              id="buttonAudio"
              @click="audioController"
            >
              <span>{{ audioMsg }}</span>
            </v-btn>
            <!-- 녹음 버튼 -->

            <v-btn
              v-if="recordMode == false"
              class="btn btn-large btn-danger"
              type="button"
              id="buttonRecord"
              @click="startRecord"
            >
              <span>start record</span>
            </v-btn>
            <v-btn
              v-else
              class="btn btn-large btn-danger"
              type="button"
              id="buttonRecord"
              @click="stopRecord"
            >
              <span>stop record</span>
            </v-btn>

            <v-btn
              id="buttonLeaveSession"
              color="red"
              @click="leaveSession"
            >
              <span>나가기</span>
            </v-btn>
          </div>
        </div>
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
  props: ['id'],
  data() {
    return {
      consultadmit: false,
      dialog: false,
      // 1이면 상담사 2이면 상담자
      common_code: undefined,
      OV: undefined,
      session: undefined,
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
              resolution: "600x900", // The resolution of your video
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
      if(this.videoMute == true) this.videoMsg = "비디오 OFF";
      else(this.videoMsg = "비디오 ON");
      this.publisher.publishVideo(this.videoMute);
    },
    audioController() {
      this.audioMute = !this.audioMute;
      if(this.audioMute == true) this.audioMsg = "마이크 OFF";
      else(this.audioMsg = "마이크 ON");
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
          `${VUE_APP_API_URL}/api/room/recordings/stop/${this.mySessionId}`,
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
</style>