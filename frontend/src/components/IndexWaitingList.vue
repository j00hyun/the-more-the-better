<template>
    <div class="section bg">
        <div class="container">
            <h1>🔍전체 목록 보기🔎</h1>
            <h2>배달료 줄이고 행복을 더하세요! 多多益善</h2>
            <div v-for="(info) in orders" :key="info.no">
                <div  class="col three bg nopad pointer" @click="openModal(info)" >
                    <div class="imgholder">
                        <img :src = "info.store.picture" style=" height: 100%;
          width : 100%;
          object-fit: contain;"
                        />
                    </div>
                    <h1 class="feature">{{info.store.name}}</h1>
                    <p>모집시간 : {{info.order.closetime}}&nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;모집현황 : {{info.order.standby}}/{{info.order.minperson}}</p>
                </div>
            </div>
            <div class="group"></div>
        </div>
        <ServiceModal @close="closeModal" v-if="modal">
      <!-- default 슬롯 콘텐츠 -->
      <div class="modalBox" >
        <div class="modalBox1">
          <h1> </h1>
          <h3 style="text-align: center;">📝{{modalData.store.name}}</h3>
          <img class="fit-picture" style=" width: 100%;height: 300px;" :src="modalData.store.picture">
          <hr style="border: inset 1px orange; width: 100%;">
          <p> 부가 설명<span class="price"></span></p>
          <hr>  
          {{modalData.order.text}}
        </div>
        <div class="modalBox2">
        <h4> 📌게시자 : <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b>{{modalData.order.host.name}}</b></span></h4>
        <hr style="border: inset 1px orange; width: 100%;"> 
        <p> 시작 시간 : <span class="price">{{modalData.order.createdDate}}</span></p>
        <p> 마감 시간 : <span class="price">{{modalData.order.closetime}}</span></p>
        <hr style="border: inset 1px orange; width: 100%;">
        <p> 주문 인원<span class="price">{{modalData.order.standby}}/{{modalData.order.minperson}}
            <div class="dropdown">
              <button @click="detailMember" class="dropbtn">자세히보기</button>
              <div v-if="listValue" id="myDropdown" class="dropdown-content">
                <hr>  
                <p> 대기멤버</p>
                <p v-for="member in modalData.order.waitingmems" v-bind:key="member.id"> {{member.name}}</p>
                <hr>  
              </div>
            </div>
          </span>
        </p>
        <p> 배달 수령 장소 : <span class="price">{{modalData.order.meetplace}}</span></p>
        <p> 최소 주문 가격 : <span class="price">{{modalData.order.mincost}}</span></p>
        
        <hr>        
        </div>
      </div>


      <template slot="footer">
        <button class="dropbtn" style="width: 100px;" @click="doSend">제출</button>
      </template>
      <!-- /footer -->
    </ServiceModal>
    </div>

</template>


<script>
    import ServiceModal from './servicecoms/ServiceModal'
    import axios from 'axios'
    const storage = window.sessionStorage;
    export default {
        name: 'IndexWaitingList',
        components: {ServiceModal},
        data(){
            return{
                modal: false,
                modalData:[],
                message: '',
                orders: [],
                listValue: false
            }
        },
        created() {
            console.log("!!!!!");
            this.init()
        },
        methods:{
            detailMember() {
                this.listValue = !this.listValue 
            },
            openModal(orders) {
                this.modalData = orders;
                console.log(this.modalData);
                this.modal = true;
            },
            closeModal() {
                this.modalData = [];
                this.modal = false;
            },
            /////////////////////////////////////////////
            doSend(index) {
                console.log(this.modalData.store.no);
                axios.post('/order/setmem/' + this.modalData.store.no + "/" + storage.getItem("member"))
                    .then(res => {
                        if(res.data.status) {
                            console.log("등록성공");
                            alert("주문대기열에 추가되었습니다.");
                            this.closeModal();
                            this.init();
                        }
                    }).catch(e => {
                    console.log("주문 대기열 등록 실패");
                    alert(JSON.stringify(e.response.data.message));
                });
            },
            init(){
                console.log("시작");
                this.modal = false;
                this.modalData = [];
                this.message = '';
                this.orders =  [];
                this.listValue = false;
                
                axios.get('/order/all')
                    .then(res =>{
                        for(var i=0; i<JSON.parse(res.data.orders.length); i++) {

                            this.orders.push({
                                order: res.data.orders[i],
                                store: res.data.orders[i].store,
                            })
                        }
                    })
            }
        }
    }
</script>

<style scoped>
    /*
      terrible.css
      Andrew Tunecliffe, 2015
      http://atunnecliffe.com
    */
    /* uncomment to see outlines of stuff when you hover *-/
    :hover {
      background:rgba(0, 0, 0, 0.1);
    }/**/
    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');

    .dropbtn {
  background-color: #4CAF50;
  color: white;
  padding: 3px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}
.dropbtn:hover, .dropbtn:focus {
  background-color: #46a049;
}


p{
  font-size: 20px;
  left: 10px;
}
.modalBox{
  font-family: 'Do Hyeon', sans-serif;
  overflow: hidden;
  display:inline-block;
  background-color: #ffefc3;
  
}
.modalBox1{
  
  margin: 10px;
  float: left;
  background-color: #ffffff;
  width: 400px;
  height: 500px;

}
.modalBox2{
  margin: 10px;
  float: left;
  width: 300px;
  height: 500px;
  background-color: #ffffff; 
}

    body{
        background-color:#f2f2f2;
    }
    h2{
        font-family: 'Do Hyeon', sans-serif;
    }
    .carousel-inner>.carousel-item>img {  width: 320px; height: 360px;

    }


    html,
    body {
        margin: 0;
        padding: 0;
        font-family: 'Do Hyeon', sans-serif;
        background-color:#f2f2f2;
    }

    .section {
        width: 100%;
    }

    .container {
        position: relative;
        width: 1170px;
        margin: 0 auto;
        left-margin: 50px;
        color: #444;
        font-size: 14px;
        font-weight: 300;
        font-family: 'Do Hyeon', sans-serif;
    @import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
        overflow: hidden;
    }

    .section .container {
        padding: 30px 0 50px 0;
    }

    .section.bg {
        background: #f7f7f7;
    }
    /*
      Header
    */

    .hold {
        height: 80px;
    }

    .header {
        line-height: 80px;
        width: 100%;
        transition: line-height 0.2s linear, box-shadow 0.2s linear;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 100;
        background: rgba(245, 245, 245, 0.97);
    }

    .header.small {
        line-height: 50px;
        box-shadow: 0px 1px 3px 0px rgba(50, 50, 50, 0.8);
    }

    .header.small > .container > #logo {
        height: 40px;
    }

    #logo {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        float: left;
        height: 40px;
        width: 170px;
        margin-left: 5px;
    }

    ul.nav {
        float: right;
        list-style: none;
        margin: 0;
        padding: 0;
    }

    ul.nav li {
        float: left;
        position: relative;
    }

    ul.nav li a {
        transition: color 0.2s linear;
        font-size: 18px;
    }

    ul.nav li:hover a {
        color: red;
    }

    ul.nav li a {
        padding: 21px;
        color: initial;
        text-decoration: initial;
    }
    /*
      Slider
    */

    .section .slider,
    .section .footer {
        background: #333;
    }

    .slidercontent {
        text-align: center;
    }

    .hero {
        font-family: 'Roboto Slab', sans-serif;
        color: white;
        font-weight: normal;
        letter-spacing: 1px;
    }

    h1.hero {
        font-size: 54px;
    }

    h2.hero {
        font-size: 30px;
        margin-bottom: 60px;
    }

    h1.hero:after {
        content: "";
        width: 300px;
        position: relative;
        border-bottom: 1px solid #aaa;
        text-align: center;
        margin: auto;
        margin-top: 15px;
    }

    .call {
        color: white;
        display: block;
        margin-bottom: 20px;
    }

    .call span {
        display: inline;
        border: 1px solid white;
        align-content: center;
        padding: 8px 13px;
        font-size: 20px;
        transition: background 0.15s linear;
    }

    .call span:hover {
        background: rgba(255, 255, 255, 0.1);
        cursor: pointer;
    }
    /*
      Columns
    */

    .col {
        float: left;
        padding: 0;
        margin: 0;
        position: relative;
    }

    .col.four {
        width: 23%;
        margin: 0 1%;
    }

    .col.three {
        width: 31.3%;
        margin: 0 1%;
    }

    .col.two {
        width: 40%;
        margin: 0 2.5%;
        padding: 0 2.5%;
    }

    .col.extrapad {
        padding-top: 20px;
        padding-bottom: 20px;
    }

    .col .service,
    .col .feature {
        font-size: 50px;
        font-weight: 300;
        color: black;
        font-family: 'Do Hyeon', sans-serif;
    }

    .col .service:after {
        content: "";
        width: 50px;
        position: relative;
        border-bottom: 1px solid #eee;
        display: block;
        text-align: center;
        margin: auto;
        margin-top: 15px;
    }

    .col .feature {
        font-size: 19px;
    }

    .col h1.side,
    .col p.side,
    .col span.side:first-of-type {
        margin-left: 50px;
        text-align: left;
    }

    .col .icon {
        border-radius: 50%;
        height: 85px;
        width: 85px;
        line-height: 85px;
        text-align: center;
        margin: 0 auto;
        transition: background 0.25s linear, color 0.25s linear;
    }

    .col .icon.side {
        position: absolute;
        padding: 0;
        margin: 0;
        top: -15px;
        height: 50px;
        width: 50px;
    }

    .col:hover > .icon {
        background: #F44336;
        color: white;
    }

    .col:hover > .icon.side {
        background: initial;
        color: initial;
    }

    .responsivegroup {
        display: none;
    }
    /*
      Column specifics
    */

    .col p,
    .col h1 {
        padding: 0 1%;
        text-align: center;
    }

    .group.margin {
        margin-bottom: 20px;
    }

    .col .imgholder {
        height: 300px;
        width: 100%;
        background: #b38e74;
        transition: background 0.3s linear;
    }

    .col.imgholder.img{
        height: 100%;
        width : 100%;
        object-fit: contain;
    }

    .col.bg {
        background: #FFF;
    }

    .col.pointer {
        cursor: pointer;
    }

    .col.bg:hover .imgholder {
        background: #555;
    }

    .col span.feature {
        font-size: 20px;
    }
    /*
      Text
    */

    .container > h1:not(.hero) {
        margin-bottom: 30px;
        text-align: center;
    }

    .container > h1:after {
        content: "";
        width: 30px;
        position: relative;
        border-bottom: 1px solid #aaa;
        display: block;
        text-align: center;
        margin: auto;
        margin-top: 15px;
    }

    h2 {
        font-family: 'Roboto Slab', sans-serif;
        text-align: center;
        font-weight: 400;
        font-size: 18px;
    }

    .left,
    .left > h1,
    .left > p {
        text-align: left;
    }

    .reset {
        text-align: left !important;
    }

    .reset:after {
        display: none !important;
    }
    /*
      Slider with Content
    */

    .white h1,
    .white h2,
    .white p,
    .white div,
    .white a {
        color: #fff;
    }
    /*
      Responsive
    */

    .group:after {
        content: "";
        display: table;
        clear: both;
    }

    @media all and (max-width: 768px) {
        .container {
            width: 100%;
        }
        .col.four {
            width: 48%;
            margin: 1%;
        }
        .col.three {
            display: block;
            width: 95%;
            padding: 0;
            margin: 0 auto;
            float: none;
        }
        .header {
            height: auto;
            background: #eee;
        }
        #logo {
            position: initial;
            float: none;
            display: block;
            transform: none;
            margin: 10px auto 0 auto;
        }
        ul.nav {
            float: none;
            display: block;
            text-align: center;
            margin: 0 auto;
        }
        ul.nav li {
            float: initial;
            display: inline-block;
        }
        .responsivegroup {
            display: block;
        }
        .responsivegroup:after {
            content: "";
            display: table;
            clear: both;
        }
    }

    @media all and (min-width: 768px) {
        .container {
            width: 750px;
            margin-left: 200px;
        }
    }

    @media all and (min-width: 992px) {
        .container {
            width: 970px;
        }
    }

    @media all and (min-width: 1200px) {
        .container {
            width: 1170px;
        }
    }

    @media all and (max-width:450px) {
        .col, .col.four, .col.three, .col.two {
            display: block;
            width: 95%;
            padding: 0;
            margin: 0 auto;
            float: none;
        }
        .col.extrapad {
            padding: 1%;
            margin-bottom: 10px;
        }
        .group {
            display: none;
        }
    }

    .webdesigntuts-workshop {
        background: #151515;
        height: 100%;
        position: absolute;
        text-align: center;
        width: 100%;
    }

    .webdesigntuts-workshop:before,
    .webdesigntuts-workshop:after {
        content: '';
        display: block;
        height: 1px;
        left: 50%;
        margin: 0 0 0 -400px;
        position: absolute;
        width: 800px;
    }

    .webdesigntuts-workshop:before {
        background: #444;
        background: linear-gradient(left, #151515, #444, #151515);
        top: 192px;
    }

    .webdesigntuts-workshop:after {
        background: #000;
        background: linear-gradient(left, #151515, #000, #151515);
        top: 191px;
    }

    .webdesigntuts-workshop form {
        background: rgb(229, 232, 241);
        background: linear-gradient(rgb(229, 232, 241), rgb(229, 232, 241));
        border: 1px solid #000;
        border-radius: 30px;
        box-shadow: inset 0 0 0 1px #272727;
        display: inline-block;
        font-size: 0px;
        margin:auto 0;
        padding: 0px;
        position: relative;
        z-index: 1;
    }

    .webdesigntuts-workshop input {
        background: rgb(89, 90, 26);
        background: linear-gradient(white,white);
        border: 1px solid #444;
        border-radius: 5px 0 0 5px;
        box-shadow: 0 2px 0 rgb(229, 232, 241);
        color: #888;
        display: block;
        float: left;
        font-family: 'Cabin', helvetica, arial, sans-serif;
        font-size: 13px;
        font-weight: 400;
        height: 40px;
        margin: 0;
        padding: 0 10px;
        text-shadow: 0 -1px 0 #000;
        width: 1000px;
    }

    .ie .webdesigntuts-workshop input {
        line-height: 40px;
    }

    .webdesigntuts-workshop input::-webkit-input-placeholder {
        color: #888;
    }

    .webdesigntuts-workshop input:-moz-placeholder {
        color: #888;
    }

    .webdesigntuts-workshop input:focus {
        animation: glow 800ms ease-out infinite alternate;
        background: rgb(229, 232, 241);
        background: linear-gradient(rgb(229, 232, 241),rgb(229, 232, 241));
        border-color: #393;
        box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
        color: #efe;
        outline: none;
    }

    .webdesigntuts-workshop input:focus::-webkit-input-placeholder {
        color: #efe;
    }

    .webdesigntuts-workshop input:focus:-moz-placeholder {
        color: #efe;
    }

    .webdesigntuts-workshop button {
        background: rgb(229, 232, 241);
        background: linear-gradient(#333, #222);
        box-sizing: border-box;
        border: 1px solid rgb(229, 232, 241);
        border-left-color: rgb(229, 232, 241);
        border-radius: 0 5px 5px 0;
        box-shadow: 0 2px 0 rgb(229, 232, 241);
        color: black;
        display: block;
        float: left;
        font-family: 'Cabin', helvetica, arial, sans-serif;
        font-size: 13px;
        font-weight: 400;
        height: 40px;
        line-height: 40px;
        margin: 0;
        padding: 0;
        position: relative;
        text-shadow: 0 -1px 0 rgb(229, 232, 241);
        width: 80px;
    }

    .webdesigntuts-workshop button:hover,
    .webdesigntuts-workshop button:focus {
        background: rgb(229, 232, 241);
        background: linear-gradient(rgb(229, 232, 241), rgb(229, 232, 241));
        color: #5f5;
        outline: none;
    }

    .webdesigntuts-workshop button:active {
        background: rgb(229, 232, 241);
        background: linear-gradient(#393939, #292929);
        box-shadow: 0 1px 0 #000, inset 1px 0 1px #222;
        top: 1px;
    }

    @keyframes glow {
        0% {
            border-color: #393;
            box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
        }
        100% {
            border-color: #6f6;
            box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
        }
    }


</style>
