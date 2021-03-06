/**
 * Created by Administrator on 2017/6/10.
 */
var date = [];
//macd
var diffdata = [];
var deadata = [];
var macddata = [];

//kdj
var kdata = [];
var ddata = [];
var jdata = [];

//rsi
var rsi6data = [];
var rsi12data = [];
var rsi24data = [];

//boll
var middata = [];
var updata = [];
var lowdata = [];


function setDate() {
    var json;
    date = [];
//macd
    diffdata = [];
    deadata = [];
    macddata = [];

//kdj
    kdata = [];
    ddata = [];
    jdata = [];

//rsi
    rsi6data = [];
    rsi12data = [];
    rsi24data = [];

//boll
    middata = [];
    updata = [];
    lowdata = [];
    $.ajax({
        cache:false,
        async:false,
        url:'getIndex.action',
        type:'GET',
        dataType:'json',
        success:function (data) {
            json = JSON.parse(data);
        },
        error:function (data) {
            alert("error");
        }
    });
    for(var i=0;i<json['diff'].length;i++){
        date.push(json['diff'][i]['date']);
    }
    for(var i=0;i<json['diff'].length;i++){
        diffdata.push(json['diff'][i]['value']);
    }
    for(var i=0;i<json['dea'].length;i++){
        deadata.push(json['dea'][i]['value']);
    }
    for(var i=0;i<json['macd'].length;i++){
        macddata.push(json['macd'][i]['value']);
    }
    for(var i=0;i<json['k'].length;i++){
        kdata.push(json['k'][i]['value']);
    }
    for(var i=0;i<json['d'].length;i++){
        ddata.push(json['d'][i]['value']);
    }
    for(var i=0;i<json['j'].length;i++){
        jdata.push(json['j'][i]['value']);
    }
    for(var i=0;i<json['rsi6'].length;i++){
        rsi6data.push(json['rsi6'][i]['value']);
    }
    for(var i=0;i<json['rsi12'].length;i++){
        rsi12data.push(json['rsi12'][i]['value']);
    }
    for(var i=0;i<json['rsi24'].length;i++){
        rsi24data.push(json['rsi24'][i]['value']);
    }
    for(var i=0;i<json['mid'].length;i++){
        middata.push(json['mid'][i]['value']);
    }
    for(var i=0;i<json['up'].length;i++){
        updata.push(json['up'][i]['value']);
    }
    for(var i=0;i<json['low'].length;i++){
        lowdata.push(json['low'][i]['value']);
    }

}
drawMACD();
function drawMACD() {
    setDate();
    var myChart = echarts.init(document.getElementById("index"));
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data:['diff','dea','macd']
        },
        grid: {
            top:'13%',
            left: '5%',
            right: '0%',
            bottom: '11%'
        },
        xAxis:  {
            boundaryGap: true,
            type: 'category',
            data: date
        },
        yAxis: {
            type: 'value',
            splitArea: {
                show: true
            },
            scale:true
        },
        series: [
            {
                name:'diff',
                type:'line',
                data:diffdata
            },
            {
                name:'dea',
                type:'line',
                data:deadata
            },
            {
                name:'macd',
                type:'bar',
                data:macddata
            }
        ]
    };
    myChart.setOption(option);
}
function drwaKDJ() {
    setDate();
    var myChart = echarts.init(document.getElementById("index"));
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data:['k','d','j']
        },
        grid: {
            top:'13%',
            left: '5%',
            right: '0%',
            bottom: '11%'
        },
        xAxis:  {
            boundaryGap: true,
            type: 'category',
            data: date
        },
        yAxis: {
            type: 'value',
            splitArea: {
                show: true
            },
            scale:true
        },
        series: [
            {
                name:'k',
                type:'line',
                data:kdata
            },
            {
                name:'d',
                type:'line',
                data:ddata
            },
            {
                name:'j',
                type:'line',
                data:jdata
            }
        ]
    };
    myChart.setOption(option);
}
function drawRSI() {
    setDate();
    var myChart = echarts.init(document.getElementById("index"));
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data:['rsi6','rsi12','rsi24']
        },
        grid: {
            top:'13%',
            left: '5%',
            right: '0%',
            bottom: '11%'
        },
        xAxis:  {
            type: 'category',
            data: date
        },
        yAxis: {
            type: 'value',
            splitArea: {
                show: true
            },
            scale:true
        },
        series: [
            {
                name:'rsi6',
                type:'line',
                data:rsi6data
            },
            {
                name:'rsi12',
                type:'line',
                data:rsi12data
            },
            {
                name:'rsi24',
                type:'line',
                data:rsi24data
            }
        ]
    };
    myChart.setOption(option);
}
function getKlineInfo() {
    var kline;
    var klinebar = [];
    $.ajax({
        cache:false,
        async:false,
        url:'getBOLLKline.action',
        type:'GET',
        dataType:'json',
        success:function (data) {
            kline = JSON.parse(data);
        }
    });
    for(var i=0;i<kline['kLine'].length;i++){
        var temp = [];
        temp.push(kline['kLine'][i]['open']);
        temp.push(kline['kLine'][i]['close']);
        temp.push(kline['kLine'][i]['low']);
        temp.push(kline['kLine'][i]['high']);
        klinebar.push(temp);
    }
    return klinebar;
}
function drawBOLL() {
    setDate();
    var myChart = echarts.init(document.getElementById("index"));
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data:['日k','mid','up','low']
        },
        grid: {
            top:'13%',
            left: '5%',
            right: '0%',
            bottom: '11%'
        },
        xAxis:  {
            type: 'category',
            data: date
        },
        yAxis: {
            type: 'value',
            splitArea: {
                show: true
            },
            scale:true
        },
        series: [
            {
                name:'日k',
                type: 'candlestick',
                data: getKlineInfo()
            },
            {
                name:'mid',
                type:'line',
                data:middata
            },
            {
                name:'up',
                type:'line',
                data:updata
            },
            {
                name:'low',
                type:'line',
                data:lowdata
            }
        ]
    };
    myChart.setOption(option);
}
function clickMACDbutton() {
    var MACDbutton = document.getElementById("MACDbutton");
    var KDJbutton = document.getElementById("KDJbutton");
    var RSIbutton = document.getElementById("RSIbutton");
    var BOLLbutton = document.getElementById("BOLLbutton");
    MACDbutton.style.backgroundColor = "#003366";
    MACDbutton.style.color = "#FFFFFF";
    KDJbutton.style.backgroundColor = "#FFFFFF";
    KDJbutton.style.color = "#000000";
    RSIbutton.style.backgroundColor = "#FFFFFF";
    RSIbutton.style.color = "#000000";
    BOLLbutton.style.backgroundColor = "#FFFFFF";
    BOLLbutton.style.color = "#000000";
    drawMACD();
}
function clickKDJbutton() {
    var MACDbutton = document.getElementById("MACDbutton");
    var KDJbutton = document.getElementById("KDJbutton");
    var RSIbutton = document.getElementById("RSIbutton");
    var BOLLbutton = document.getElementById("BOLLbutton");
    MACDbutton.style.backgroundColor = "#FFFFFF";
    MACDbutton.style.color = "#000000";
    KDJbutton.style.backgroundColor = "#003366";
    KDJbutton.style.color = "#FFFFFF";
    RSIbutton.style.backgroundColor = "#FFFFFF";
    RSIbutton.style.color = "#000000";
    BOLLbutton.style.backgroundColor = "#FFFFFF";
    BOLLbutton.style.color = "#000000";
    drwaKDJ();
}
function clickRSIbutton() {
    var MACDbutton = document.getElementById("MACDbutton");
    var KDJbutton = document.getElementById("KDJbutton");
    var RSIbutton = document.getElementById("RSIbutton");
    var BOLLbutton = document.getElementById("BOLLbutton");
    MACDbutton.style.backgroundColor = "#FFFFFF";
    MACDbutton.style.color = "#000000";
    KDJbutton.style.backgroundColor = "#FFFFFF";
    KDJbutton.style.color = "#000000";
    RSIbutton.style.backgroundColor = "#003366";
    RSIbutton.style.color = "#FFFFFF";
    BOLLbutton.style.backgroundColor = "#FFFFFF";
    BOLLbutton.style.color = "#000000";
    drawRSI();
}
function clickBOLLbutton() {
    var MACDbutton = document.getElementById("MACDbutton");
    var KDJbutton = document.getElementById("KDJbutton");
    var RSIbutton = document.getElementById("RSIbutton");
    var BOLLbutton = document.getElementById("BOLLbutton");
    MACDbutton.style.backgroundColor = "#FFFFFF";
    MACDbutton.style.color = "#000000";
    KDJbutton.style.backgroundColor = "#FFFFFF";
    KDJbutton.style.color = "#000000";
    RSIbutton.style.backgroundColor = "#FFFFFF";
    RSIbutton.style.color = "#000000";
    BOLLbutton.style.backgroundColor = "#003366";
    BOLLbutton.style.color = "#FFFFFF";
    drawBOLL();
}