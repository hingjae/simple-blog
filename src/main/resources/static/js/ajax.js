$(function () {
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
        let id = $("#edit-post-id").val();
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
        let id = $("#post-id").val();

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
            console.log("Post creation success!");
            window.location.href = "/login";
        });
    })

    $(".comment-edit").hide();

    $(".comment-edit-form-button").click(function () {
        $(this).closest(".comment_text").find(".comment-edit").show();
    });

    $(".comment-edit-cancel-button").click(function () {
        $(this).closest(".comment_text").find(".comment-edit").hide();
    });
});