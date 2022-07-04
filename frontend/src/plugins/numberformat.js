/**
* @author: yuanjs
* @description: 数字三分，保留两位小数
* @param: params
* @date: 2020-02-05 20:10:57
* @return: 
*/
function number3(value) {
    if (value == undefined || value == "") {
        value = 0;
    }
    //现将数字保留两位小数,再将其三分化
    var nStr = (value * 1).toFixed(2);
    nStr += '';
    let x = nStr.split('.');
    let x1 = x[0];
    let x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
}

function toThousands(num) {
    var result = [], counter = 0;
    num = (num || 0).toString().split('');
    for (var i = num.length - 1; i >= 0; i--) {
        counter++;
        result.unshift(num[i]);
        if (!(counter % 3) && i != 0) { result.unshift(','); }
    }
    return result.join('');
}

function outputmoney(value) {

    return "￥" + number3(value);
}

function gross(value) {
    return (value * 100).toFixed(2) + "%"

}

export { outputmoney, gross, number3,toThousands }