$(function () {

    //loggedIn은 checkLoggedIn 함수의 결과 값으로 취급되며,
    // 이 값을 콜백 함수 내에서 활용하여 로그인 여부에 따른 동작을 수행합니다.
    // 로그인 여부에 따라 버튼을 숨기거나 보이게 하는 등의 로직을 처리하기 위해 loggedIn 값을 사용할 수 있습니다.
    function checkLoggedIn(callback) {
        $.ajax({
            method: "GET",
            url: "/api/users/login-check"
        })
        .done(function (response) {
            callback(response.loggedIn) // 콜백함수는 비동기 작업이 완료된 이후 즉시 실행되는 함수이다.
        })
        .fail(function () {
            callback(false);
        });
    }

    checkLoggedIn(function (loggedIn) {
        if (loggedIn) {
            $("#logout-menu").show();
            $("#login-menu").hide();
            $("#signup-menu").hide();
            $("#write-menu").show();
        } else {
            $("#logout-menu").hide();
            $("#login-menu").show();
            $("#signup-menu").show();
            $("#write-menu").hide();
        }
    });

    $("#more").click(function () {
        var next_page = parseInt($(this).attr("current-page")) + 1;
        $.ajax({
            method: "GET",
            url: "/api/articles",
            data: {"page": next_page}
        })
        .done(function(response) {
            for(var article of response) {
                var postSubtitle = "<h3 class=\"post-subtitle text-truncate\">" + article.content + "</h3>";
                $("#more-posts").append(
                    "<div class=\"post-preview\">" +
                    "<a href=\"/articles/" + article.id + "\">" +
                    "<h2 class=\"post-title\">" +
                    article.title +
                    "</h2>\n" +
                    postSubtitle +
                    "</a><p class=\"post-meta\">Posted by " +
                    article.username +
                    "</p></div><hr class=\"my-4\" />");
            }
        });
        $(this).attr("current-page", next_page);
    });

    $("#create_button").click(function () {
        let title = $("#post-title").val();
        let content = $("#post-content").val();
        $.ajax({
            method: "POST",
            url: "/api/articles",
            data: JSON.stringify({
                "title": title,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function (response) {
            console.log("Post creation success!");
            window.location.href = "/";
        });
    });

    $("#edit_button").click(function () {
        let id = $("#edit-article-id").val();
        let title = $("#edit-post-title").val();
        let content = $("#edit-post-content").val();

        $.ajax({
            method: "PUT",
            url: "/api/articles",
            data: JSON.stringify({
                "id" : id,
                "title": title,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function () {
            console.log(("Article update success!"));
            window.location.href = "/articles/" + id;
        })
    });

    $("#post-delete-button").click(function () {
        let id = $("#article-id").val();

        $.ajax({
            method: "DELETE",
            url: "/api/articles",
            data: {"id": id},
        })
        .done(function () {
            console.log("Article delete success!");
            window.location.href = "/";
        });
    });

    $("#signup-button").click(function () {
        let id = $("#signup-id").val();
        let password = $("#signup-password").val();
        let name = $("#signup-name").val();

        $.ajax({
            method: "POST",
            url: "/api/users/sign-up",
            data: JSON.stringify({
                "id": id,
                "password": password,
                "name": name
            }),
            contentType: "application/json"
        })
            .done(function () {
                console.log("User creation success!");
                window.location.href = "/login";
            })
            .fail(function (response) {
                let errorMessage = response.responseText;
                alert(errorMessage);
            });
    });

    $("#login-button").click(function () {
        let id = $("#login-id").val();
        let password = $("#login-password").val();

        $.ajax({
            method: "POST",
            url: "/api/users/login",
            data: JSON.stringify({
                "id": id,
                "password": password
            }),
            contentType: "application/json"
        })
        .done(function () {
            window.location.href = "/";
        })
        .fail(function (response) {
            let errorMessage = response.responseText;
            alert(errorMessage);
        });
    });

    $("#logout-menu").click(function () {
        $.ajax({
            method: "POST",
            url: "/api/users/logout"
        })
            .done(function () {
                window.location.href = "/";
            });
    });

    $("#comment-save-button").click(function () {
        let articleId = $("#article-id").val();
        let content = $("#comment-content").val();
        $.ajax({
            method: "POST",
            url: "/api/articles/" + articleId + "/articleComments",
            data: JSON.stringify({
                "articleId": articleId,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function (response) {
            window.location.reload();
        })
        .fail(function (response) {
            let errorMessage = response.responseText;
            alert(errorMessage);
        });
    });

    $(".comment-edit").hide();

    $(".comment-edit-form-button").click(function () {
        $(this).closest(".comment_text").find(".comment-edit").show();
    });

    $(".comment-edit-cancel-button").click(function () {
        $(this).closest(".comment_text").find(".comment-edit").hide();
    });
});