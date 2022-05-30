import React from 'react'; 
import { Link } from 'react-router-dom';
import './css/Main.css';

const Main = () => { 
    const products = {
        "products": [
        ]
    }
    const purchase = {
        "cnt": "0", 
        "price": "0", 
    }

    localStorage.setItem('products', JSON.stringify(products)); // 장바구니 목록
    localStorage.setItem('purchase', JSON.stringify(purchase)); // 장바구니 물품 

    return ( 
        <div className="main">
            <h1 className="p5 f1 bold"> 박리다매 무인가게 </h1>
            <div>
                <Link to="/products">
                    <button className="menu m1 p3 shadow">
                        <div className="f3 left orange">01</div>
                        <div className="f2 left">상품 검색</div>
                        <img className="menu_img" src="https://cdn-icons-png.flaticon.com/512/2250/2250295.png"></img>
                    </button>
                </Link>
                <Link to="/buy">
                    <button className="menu m1 p3 shadow">
                        <div className="f3 left green">02</div>
                        <div className="f2 left">계산 하기</div>
                        <img className="menu_img" src="https://cdn-icons-png.flaticon.com/512/2250/2250295.png"></img>
                    </button>
                </Link> 
                <Link to="/permute">
                    <button className="menu m1 p3 shadow">
                        <div className="f3 left green">03</div>
                        <div className="f2 left">환불&교환</div>
                        <img className="menu_img" src="https://cdn-icons-png.flaticon.com/512/2250/2250295.png"></img>
                    </button>
                </Link>   
                <Link to="/rank">
                    <button className="menu m1 p3 shadow">
                        <div className="f3 left orange">04</div>
                        <div className="f2 left">인기 순위</div>
                        <img className="menu_img" src="https://cdn-icons-png.flaticon.com/512/2250/2250295.png"></img>
                    </button>
                </Link>
            </div>
        </div> 
    ); 
}; 

export default Main;