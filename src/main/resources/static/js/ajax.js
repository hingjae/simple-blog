$(function () {
    $("#more").click(function () {
        var next_page = parseInt($(this).attr("current-page")) + 1;

        $.ajax({
            method: "GET",
            url: "/api/articles",
            data: {"page": next_page}
        })
        .done(function(response) {
            for(var post of response) {
                $("#more-posts").append(
                    "<div class=\"post-preview\">" +
                    "<a href=\"/articles/" + post.id + "\">" +
                    "<h2 class=\"post-title\">" +
                    post.title +
                    "</h2>\n" +
                    "<h3 class=\"post-subtitle\">" +
                    post.content +
                    "</h3></a><p class=\"post-meta\">Posted by " +
                    post.name +
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
            console.log(("Article update success!"))
            window.location.href = "/articles/" + id;
        })
    });

    $(".comment-edit").hide();

    $(".comment-edit-form-button").click(function () {
        $(this).closest(".comment_text").find(".comment-edit").show();
    });

    $(".comment-edit-cancel-button").click(function () {
        $(this).closest(".comment_text").find(".comment-edit").hide();
    });
});