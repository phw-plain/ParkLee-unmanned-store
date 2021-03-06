import React from 'react';
import ReactDOM from 'react-dom/client';
import './css/index.css';
import App from './App';
import ReportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.css';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  // 개발 후 React.StrictMode 지워주기 2번 출력 방지
  <App />
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
ReportWebVitals();
