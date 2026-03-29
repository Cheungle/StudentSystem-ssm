function getTokenFromLocalStorage() {
    return localStorage.getItem('token');
}
// 全局配置AJAX默认请求头，自动携带Token
$(function() {
    // 读取Token（按需选择Cookie/LocalStorage）
    const token = getTokenFromLocalStorage();
    console.log("进入setup"+token);
    if (token) {
        // 全局设置所有AJAX请求的请求头
        $.ajaxSetup({
            headers: {
                'token': token // 与后端拦截器约定的请求头Key（如token/Authorization）
            }
        });
    }
});
function logout() {
    console.log("logout");
    localStorage.removeItem('token');
    window.location.href = "/login.jsp";
}


// 重写 XMLHttpRequest，实现全局拦截
(function() {
    // 全局锁：保证只跳转 1 次
    window.isRedirecting = false;

    const originalOpen = XMLHttpRequest.prototype.open;

    XMLHttpRequest.prototype.open = function(method, url, async, user, password) {
        this.addEventListener('load', function() {
            // 后端返回 401 → 未登录
            console.log("load"+this.status+"||"+window.isRedirecting);
            if (this.status === 401 && !window.isRedirecting) {
                window.isRedirecting = true; // 上锁
                localStorage.removeItem("token");
                window.location.href = "/login.jsp"; // 只跳这一次！
            }
            if (url.includes("login") && this.status === 200) {
                console.log("登录成功，重置跳转锁");
                window.isRedirecting = false;
            }
        });
        originalOpen.apply(this, arguments);
    };
})();