import React from "react";

export function Receipt(props) {     
    return (
        <div className="Receipt">
            <h1 className="receipt_title">
                <img className="logo" src="logo192.png" alt=""/>
                박리다매 무인마켓
            </h1>
            <div className="r_list">
                {props.cart.map((item, idx) => (
                    <div className="receipt_list" key={idx}>
                        <div>{item.name}</div>
                        <div className="receipt_list2">
                            <div>{item.cnt}</div>
                            <div>{item.sum}원</div>
                        </div>
                    </div>
                ))} 
            </div>
            <div className="total">
                <div>총 {props.purchase.cnt}개</div>
                <div>{props.purchase.price}원</div>
            </div>
        </div>
    );
}

export default Receipt;
