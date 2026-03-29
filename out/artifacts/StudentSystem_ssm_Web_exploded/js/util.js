
// 根据当前时间计算学年和学期
function getCurrentSemester() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var semester;
    var schoolYear;
    // 判断学期和学年
    if (month >= 9 && month <= 12) {
        // 9-12月：当前年-次年 学年，第1学期
        schoolYear = year + "-" + (year + 1);
        semester = "1";
    } else if (month == 1) {
        // 1月：上一年-当前年 学年，第1学期
        schoolYear = (year - 1) + "-" + year;
        semester = "1";
    } else if (month >= 2 && month <= 6) {
        // 2-6月：上一年-当前年 学年，第2学期
        schoolYear = (year - 1) + "-" + year;
        semester = "2";
    } else { // 7-8月：暑期学期
        schoolYear = year + "-" + (year + 1);
        semester = "summer term";
    }
    return schoolYear + " " + semester;
}


