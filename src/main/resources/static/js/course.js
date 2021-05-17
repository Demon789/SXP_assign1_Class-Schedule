let data = {
    "uid": "2"
}
let beautifulColor = ["#FFF68F", "#DDA0DD", "#FFC0CB"];
$.ajax({
    url: "/course/getCourseByUid",//  若要改成获取自己的课可把url改为/course/getMyCourse，此时需要登录才能获得课程
    contentType: "application/json",
    dataType: "json",
    data: JSON.stringify(data),
    type: "post",
    success: function (data) {
        let courses = data["data"];
        for (let course of courses) {
            let srcNum = -1;
            let numTmp = -1;
            let dayTmp = -1;
            let times = course["time"];
            times.sort((a, b) => 100 * (a["weekday"] - b["weekday"]) + (a["course_num"] - b["course_num"]));
            let popHtml = "<div class='popup' id=\"popup_"+course["cid"]+"\">\n" +
                "\t\t<h2>"+course["address"]+"</h2>\n" +
                "\t\t<h2>"+course["teacher"]+"</h2>\n" +
                "\t\t<a href=\"#\" onclick=\"toggle('popup_"+course["cid"]+"')\">Close</a>\n" +
                "\t</div>"
            $("body").append(popHtml);
            let baseHtml = "<a href=\"#\" onclick=\"toggle('popup_" + course["cid"] + "')\">" + course["courseName"] + "</a>";
            for (let time of times) {
                if (dayTmp == time["weekday"] && numTmp == time["course_num"] - 1) {
                    let width = $(".webdesigntuts-workshop tr[r=" + srcNum + "] td[c=" + dayTmp + "]").attr("rowspan");
                    width++;
                    $(".webdesigntuts-workshop tr[r=" + srcNum + "] td[c=" + dayTmp + "]").attr("rowspan", width);
                    $(".webdesigntuts-workshop tr[r=" + time["course_num"] + "] td[c=" + time["weekday"] + "]").remove();
                } else {
                    $(".webdesigntuts-workshop tr[r=" + time["course_num"] + "] td[c=" + time["weekday"] + "]").html(baseHtml);
                    $(".webdesigntuts-workshop tr[r=" + time["course_num"] + "] td[c=" + time["weekday"] + "]").attr("bgColor", beautifulColor[course["cid"] % beautifulColor.length]);
                    srcNum = time["course_num"];
                }
                numTmp = time["course_num"];
                dayTmp = time["weekday"];
            }
        }
    }
});
