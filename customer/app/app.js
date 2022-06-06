// "use strict";

// // 모듈
// const express = require("express");
// const bodyParser = require("body-parser");
// const app = express();

// // 라우팅
// const home = require("./client/src/public/index.html");

// // 앱 세팅
// app.set("views", "./src/public/views");
// app.set("view engine", "jsx");
// app.use(express.static(`${__dirname}/src/public`));
// app.engine('jsx', require('express-react-views').createEngine())
// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({ extended: true}));


// app.use("/", home); // use -> 미들 웨어를 등록해주는 메서드.

// module.exports = app;

const express = require('express');
const path = require('path');
const cors = require('cors');
const app = express();

app.use( express.static( path.join(__dirname, 'client/build') ) );

app.get('/', function(request, response){
    response.sendFile( path.join(__dirname, 'client/build/index.html') )
});

const server = require('http').createServer(app);

app.use(cors()); // cors 미들웨어를 삽입합니다.

app.get("/", (req, res) => {
  res.sendFile('index.html')
});

app.get("/products", (req, res) => {
  let data = [
    { 
      name : "토종 햇 당근",
      text : "상품 설명1",
      cnt : "10",
      price : "1000",
      img : "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
    }, { 
      name : "야이셔 레몬",
      text : "상품 설명2",
      cnt : "10",
      price : "2000",
      img : "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
    }, { 
      name : "바나나는 길어",
      text : "상품 설명3",
      cnt : "10",
      price : "3000",
      img : "https://cdn.pixabay.com/photo/2016/09/03/20/48/bananas-1642706__340.jpg"
    }, { 
      name : "사과는 맛있어",
      text : "상품 설명4",
      cnt : "10",
      price : "4000",
      img : "https://cdn.pixabay.com/photo/2016/11/18/13/47/apple-1834639_960_720.jpg"
    }
  ]

  res.json(data);
})

app.get("/rank", (req, res) => {
  let data = [
    { 
      name : "토종 햇 당근",
      text : "상품 설명1",
      cnt : "10",
      price : "1000",
      img : "https://cdn.pixabay.com/photo/2015/03/14/14/00/carrots-673184__340.jpg"
    }, { 
      name : "야이셔 레몬",
      text : "상품 설명2",
      cnt : "10",
      price : "2000",
      img : "https://cdn.pixabay.com/photo/2017/02/05/12/31/lemons-2039830__340.jpg"
    }, { 
      name : "바나나는 길어",
      text : "상품 설명3",
      cnt : "10",
      price : "3000",
      img : "https://cdn.pixabay.com/photo/2016/09/03/20/48/bananas-1642706__340.jpg"
    }, { 
      name : "사과는 맛있어",
      text : "상품 설명4",
      cnt : "10",
      price : "4000",
      img : "https://cdn.pixabay.com/photo/2016/11/18/13/47/apple-1834639_960_720.jpg"
    }
  ]

  res.json(data);
})

server.listen(5000, ()=>{
  console.log('server is running on 5000')
})