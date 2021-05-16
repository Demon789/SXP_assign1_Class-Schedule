let data = {
    "uid": "1"
}//请求的数据
let beautifulColor = ["#FFF68F", "#DDA0DD", "#FFC0CB"];//好看的颜色，课程的背景色
$.ajax({
    url: "/course/getCourseByUid",//  若要改成获取自己的课可把url改为/course/getMyCourse，此时需要登录才能获得课程
    contentType: "application/json",//声明接受json类型的数据
    dataType: "json",//声明发送json数据
    data: JSON.stringify(data),//把要请求的数据打包成json字符串形式
    type: "post",//用post
    success: function (data) {//调用成功的回调函数
        let courses = data["data"];//获取所有课程信息（没做异常处理
        for (let course of courses) {//遍历所有课程
            let srcNum = -1;
            let numTmp = -1;
            let dayTmp = -1;//一些临时变量，用-1表示还没赋值，因为星期数和第几节课都是必大于等于0的
            let times = course["time"];//这个课程的时间
            times = times.sort((a, b) => 100 * (a["weekday"] - a["weekday"]) + (a["course_num"] - b["course_num"]));//按星期优先排序，保证这个课的早一点的时间先被遍历到，方便操作
            let popHtml = "<div id=\"popup_"+course["cid"]+"\">\n" +
                "\t\t<h2>"+course["address"]+"</h2>\n" +
                "\t\t<h2>"+course["teacher"]+"</h2>\n" +
                "\t\t<a href=\"#\" onclick=\"toggle('popup_"+course["cid"]+"')\">Close</a>\n" +
                "\t</div>"
            $("html").append(popHtml);//增加点击后的html，复制过来加一些参数，其它一样
            let baseHtml = "<a href=\"#\" onclick=\"toggle('popup_" + course["cid"] + "')\">" + course["courseName"] + "</a>";//这个框框里的html，同复制过来的
            for (let time of times) {
                console.log(time)//debug用的，删掉也行
                if (dayTmp == time["weekday"] && numTmp == time["course_num"] - 1) {//如果两个时间连在一起，就删掉下面的格子同时把上面格子的rowspan加1
                    let width = $("#courseTable tr[r=" + srcNum + "] td[c=" + dayTmp + "]").attr("rowspan");
                    width++;
                    $("#courseTable tr[r=" + srcNum + "] td[c=" + dayTmp + "]").attr("rowspan", width);
                    $("#courseTable tr[r=" + time["course_num"] + "] td[c=" + time["weekday"] + "]").remove();
                    console.log(width)
                } else {//不连在一起就直接加进去
                    $("#courseTable tr[r=" + time["course_num"] + "] td[c=" + time["weekday"] + "]").html(baseHtml);
                    $("#courseTable tr[r=" + time["course_num"] + "] td[c=" + time["weekday"] + "]").attr("bgColor", beautifulColor[course["cid"] % beautifulColor.length]);//随便给他个颜色，这里根据课程id给
                    srcNum = time["course_num"];
                }
                numTmp = time["course_num"];//记录刚刚的课程时间，星期几和第几节，用来给上面判断
                dayTmp = time["weekday"];
            }
        }
    }
});
