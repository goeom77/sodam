<template>
<div class="content">
    <div class="container-fluid h-100">
      <div class="row h-100">
        <div class="col-3">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">
                이벤트
              </h3>
            </div>
            <div class="card-body p-0">
              <div id="external-events">
                <div class="external-event bg-success" data-toggle="modal" data-target="#maintenance">
                  1
                </div>
                <div class="external-event bg-warning">
                  2
                </div>
                <div class="external-event bg-info">
                  3
                </div>
                <div class="external-event bg-primary">
                  4
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-9 h-100">
          <div class="card cal">
            <div class="card-body p-0 h-100">
              <div
                id="calendar"
                class="h-100"
              >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- /.content -->
    <div id="maintenance" class="modal fade">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">
              이벤트 등록
            </h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="row">
              <label for="title" class="col-3">이벤트명</label>
              <div class="col-9">
                <input id="title" v-model="maintenance.title" class="form-control w-100" type="text">
              </div>
            </div>
            <div class="row">
              <label for="title" class="col-3">색상선택</label>
              <div class="col-9 btn-group w-100 my-20">
                <ul id="color-chooser" class="fc-color-picker">
                  <li><a class="text-primary" href="#" @click="maintenance.color='primary'"><i class="fas fa-square" /></a></li>
                  <li><a class="text-warning" href="#" @click="maintenance.color='warning'"><i class="fas fa-square" /></a></li>
                  <li><a class="text-success" href="#" @click="maintenance.color='success'"><i class="fas fa-square" /></a></li>
                  <li><a class="text-danger" href="#" @click="maintenance.color='danger'"><i class="fas fa-square" /></a></li>
                  <li><a class="text-muted" href="#" @click="maintenance.color='muted'"><i class="fas fa-square" /></a></li>
                </ul>
              </div>
            </div>
            <div class="row">
              <label for="detail" class="col-3">이벤트 세부내용</label>
              <div class="col-9">
                <textarea id="detail" v-model="maintenance.description" class="form-control" rows="3" />
              </div>
            </div>
            <div class="row">
              <span class="col-3">시간</span>
              <input id="bannerDataSdate" type="hidden" name="bannerDataSdate" value="">
              <input id="bannerDataEdate" type="hidden" name="bannerDataEdate" value="">
              <div class="form-group col-9">
                <div class="input-group w-100">
                  <div class="input-group-prepend">
                    <span class="input-group-text"><i class="far fa-clock" /></span>
                  </div>
                  <input id="reservationtime" type="text" class="form-control float-right">
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer justify-content-between">
            <button type="button" class="btn btn-default" data-dismiss="modal">
              Close
            </button>
            <button type="button" class="btn btn-primary" @click="save()">
              Save changes
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';

declare const FullCalendar: any;

export default Vue.extend({
  data((vm: any): any) {
    return {
      maintenance: {
        title: null,
        description: null,
        color: null,
        startedAt: vm.$moment().format('YYYY-MM-DD HH:mm:ss'),
        endedAt: vm.$moment().format('YYYY-MM-DD HH:mm:ss'),
      },
    };
  },
  mounted(): any {
    this.initCalendar();
    this.setDate(new Date(), new Date());
  },
  methods: {
    save() {
      // API Post
    },

    setDate(date1, date2) {
      this.maintenance.startedAt = this.$moment(date1).format('YYYY-MM-DD 09:00:00');
      this.maintenance.endedAt = this.$moment(date2).format('YYYY-MM-DD 18:00:00');

      $('#reservationtime').daterangepicker({
        timePicker: true,
        startDate: this.$moment(date1).format('YYYY-MM-DD 09:00:00'),
        endDate: this.$moment(date2).format('YYYY-MM-DD 18:00:00'),
        timePickerIncrement: 30,
        locale: {
          format: 'YYYY-MM-DD HH:mm:ss',
        },
      });
    },
    initCalendar() {
      $(document).ready(() => {
        const date = new Date();
        const d = date.getDate(),
          m = date.getMonth(),
          y = date.getFullYear();

        const { Calendar } = FullCalendar;
        const calendarEl = document.getElementById('calendar');
        const containerEl = document.getElementById('external-events');
        let numClicks = 0;
        let timeOut;
        const draggable = new FullCalendar.Draggable(containerEl, {
          itemSelector: '.external-event',
          eventData(eventEl) {
            return {
              title: eventEl.innerText,
              backgroundColor: window.getComputedStyle(eventEl, null).getPropertyValue('background-color'),
              borderColor: window.getComputedStyle(eventEl, null).getPropertyValue('background-color'),
              textColor: window.getComputedStyle(eventEl, null).getPropertyValue('color'),
            };
          },
        });

        const calendar = new Calendar(calendarEl, {
          headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay',
          },
          themeSystem: 'bootstrap',	// 이렇게 설정하면 다크모트 라이트모드 가능
          editable: true,
          droppable: true, // this allows things to be dropped onto the calendar !!!
          drop: ((info) => {
            $('#maintenance').modal('show');
            this.setDate(new Date(info.dateStr), new Date(info.dateStr));
          }),
          dateClick: ((info) => {
            numClicks++;
            switch (numClicks) {
              case 2:
                numClicks = 0;
                $('#maintenance').modal('show');
                this.setDate(new Date(info.dateStr), new Date(info.dateStr));
                break;
              case 1:
                timeOut = setTimeout(() => {
                  numClicks = 0;
                }, 400);
                break;
              default:
                break;
            }
          }),
          eventClick: ((info) => {
            info.el.addEventListener('click', () => {
              $('#maintenance').modal('show');
            });
          }),
        });
        calendar.render();
      });
    },

  },
});
</script>


<style lang='css'>

h2 {
  margin: 0;
  font-size: 16px;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b { /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  display: flex;
  min-height: 100%;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
}

.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 2em;
}

.demo-app-main {
  flex-grow: 1;
  padding: 3em;
}

.fc { /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}
.fc-daygrid-day-number{
  color: black;
}
.fc-day-number.fc-sat.fc-past { color:#0000FF; }     /* 토요일 */
.fc-day-number.fc-sun.fc-past { color:#FF0000; }    /* 일요일 */


</style>
