function time(time) {
    let a = new Date(time)
    let y = a.getFullYear(); //获取完整的年份(4位,1970-????)
    let M = a.getMonth() + 1; //获取当前月份(0-11,0代表1月)  // 所以获取当前月份是myDate.getMonth()+1; 
    let d = a.getDate(); //获取当前日(1-31)
    let h = a.getHours(); //获取当前小时数(0-23)
    let m = a.getMinutes(); //获取当前分钟数(0-59)
    let s = a.getSeconds(); //获取当前秒数(0-59)
    let timeStr = y + '-' + M + '-' + d + ' ' + h + ':' + m + ':' + s;
    return (timeStr)
}
export default time;