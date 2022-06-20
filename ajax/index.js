//CREATE
$(document).ready(function () {
    pagePost();
    listPost();
    let post = {};


    $('#btn-create').click(function () {
        post.title = $('#title').val();
        post.content = $('#content').val();
        post.createAt = $('#createAt').val();
        post.likes = $('#likes').val();
        post.image = resultImage();
        console.log('post ===== ', post);
        let postOBJ = JSON.stringify(post);
        $.ajax({
            url: 'http://localhost:8080/api/posts',
            method: 'POST',
            contentType: 'application/json; charset=utf8',
            data: postOBJ,
            success: function (data) {
                console.log('data ====== ', data)
            }
        })
        resetFormCreate();
    })
})

function pagePost() {
    let container = $('#pagePost');
    container.pagination({
        dataSource: 'http://localhost:8080/api/posts',
        locator: 'items',
        totalNumber: 100,
        pageSize: 5,
        callback: function (response, pagination) {
            let dataHtml = '<table border="1" class="table">'
            let pageStart = (pagination.pageNumber - 1) * pagination.pageSize;
            let pageEnd = pageStart + pagination.pageSize;
            let pageItems = response.slice(pageStart, pageEnd);
            $.each(pageItems, function (index, item) {
                dataHtml +=
                    '<tr class="table-success">' +
                    '<th >Id</th>' +
                    '<th >Title</th>' +
                    '<th>Content</th>' +
                    '<th>CreateAt</th>' +
                    '<th>Like</th>' +
                    '<th>Image</th>' +
                    '<th>Edit</th>' +
                    '<th>Delete</th>' +
                    '</tr>' +
                    '<tr>' +
                    '<td class="table-danger">' + '<p>' + item.id + '</p>' + '</td>' +
                    '<td class="table-warning">' + '<p>' + item.title + '</p>' + '</td>' +
                    '<td class="table-light">' + '<p>' + item.content + '</p>' + '</td>' +
                    '<td class="table-light">' + '<p>' + item.createAt + '</p>' + '</td>' +
                    '<td class="table-light">' + '<p>' + item.likes + '</p>' + '</td>' +
                    '<td>' + '<img ' + 'class =' + "show_image " + 'src=' + item.image + '>' + '</td>' +
                    '<td><button class="btn btn-primary" onclick="editPost(' + item.id + ')">Edit</button> </td>' +
                    '<td><button class="btn btn-danger" onclick="deletePost(' + item.id + ')">Delete</button> </td>' +
                    '</tr>'
            })
            dataHtml += '</table>';
            container.prev().html(dataHtml);
        }
    })
}

//EDIT
function editPost(id) {
    document.getElementById('btn-create').innerHTML = 'EDIT',
        $.ajax({
            url: 'http://localhost:8080/api/posts' + id,
            method: 'GET',
            success: function (data) {
                $('#id').val(data.id);
                $('#title').val(data.title);
                $('#content').val(data.content);
                $('#createAt').val(data.createAt);
                $('#likes').val(data.likes);
                $('#imgDiv').val(data.image);
            }
        })
}

//DELETE
function deletePost(id) {
    $.ajax({
        url: 'http://localhost:8080/api/posts/' + id,
        method: 'DELETE',
        success: function () {
            alert('Delete success!');
            pagePost();
        }
    })
}

function listPost() {
    $.ajax({
        url: 'http://localhost:8080/api/posts',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            size = data.length;
        }
    })
}

//SEARCH POSTS
function searchPost() {
    let title = document.getElementById("search_title").value;
    console.log("title ========= ", title);
    let year = document.getElementById("search_year").value;
    console.log("year ========= ", year);
    $.ajax({
        url: 'http://localhost:8080/api/posts/search/many?title=' + title + "&year=" + year,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            console.log("searchbetween========",data)
            // display(data);
            listPost();
            let container = $('#pagePost');
            container.pagination({
                dataSource: 'http://localhost:8080/api/posts/search/many?title=' + title + "&year=" + year,
                locator: 'items',
                totalNumber: 100,
                pageSize: 5,
                callback: function (response, pagination) {
                    let dataHtml = '<table border="1" class="table">'
                    let pageStart = (pagination.pageNumber - 1) * pagination.pageSize;
                    let pageEnd = pageStart + pagination.pageSize;
                    let pageItems = response.slice(pageStart, pageEnd);
                    $.each(pageItems, function (index, item) {
                        dataHtml +=
                            '<tr class="table-success">' +
                            '<th >Id</th>' +
                            '<th >Title</th>' +
                            '<th>Content</th>' +
                            '<th>CreateAt</th>' +
                            '<th>Like</th>' +
                            '<th>Image</th>' +
                            '<th>Edit</th>' +
                            '<th>Delete</th>' +
                            '</tr>' +
                            '<tr>' +
                            '<td class="table-danger">' + '<p>' + item.id + '</p>' + '</td>' +
                            '<td class="table-warning">' + '<p>' + item.title + '</p>' + '</td>' +
                            '<td class="table-light">' + '<p>' + item.content + '</p>' + '</td>' +
                            '<td class="table-light">' + '<p>' + item.createAt + '</p>' + '</td>' +
                            '<td class="table-light">' + '<p>' + item.likes + '</p>' + '</td>' +
                            '<td>' + '<img ' + 'class =' + "show_image " + 'src=' + item.image + '>' + '</td>' +
                            '<td><button class="btn btn-primary" onclick="editPost(' + item.id + ')">Edit</button> </td>' +
                            '<td><button class="btn btn-danger" onclick="deletePost(' + item.id + ')">Delete</button> </td>' +
                            '</tr>'
                    })
                    dataHtml += '</table>';
                    container.prev().html(dataHtml);
                }
            })
        }
    })
    // let container = $('#demo')
    // container.pagination({
    //     dataSource: 'http://localhost:8080/api/posts/search/many?title=' + title + "&year=" + year,
    //     method: 'GET',
    //     locator: 'items',
    //     totalNumber: 100,
    //     pageSize: 5,
    //     callback: function (response, pagination) {
    //         let dataHtml = '<table border="1" class="table">'
    //         let pageStart = (pagination.pageNumber - 1) * pagination.pageSize;
    //         let pageEnd = pageStart + pagination.pageSize;
    //         let pageItems = response.slice(pageStart, pageEnd);
    //         $.each(pageItems, function (index, item) {
    //             dataHtml +=
    //                 '<tr class="table-success">' +
    //                 '<th >Id</th>' +
    //                 '<th >Title</th>' +
    //                 '<th>Content</th>' +
    //                 '<th>CreateAt</th>' +
    //                 '<th>Like</th>' +
    //                 '<th>Image</th>' +
    //                 '<th>Edit</th>' +
    //                 '<th>Delete</th>' +
    //                 '</tr>' +
    //                 '<tr>' +
    //                 '<td class="table-danger">' + '<p>' + item.id + '</p>' + '</td>' +
    //                 '<td class="table-warning">' + '<p>' + item.title + '</p>' + '</td>' +
    //                 '<td class="table-light">' + '<p>' + item.content + '</p>' + '</td>' +
    //                 '<td class="table-light">' + '<p>' + item.createAt + '</p>' + '</td>' +
    //                 '<td class="table-light">' + '<p>' + item.likes + '</p>' + '</td>' +
    //                 '<td>' + '<img ' + 'class =' + "show_image " + 'src=' + item.image + '>' + '</td>' +
    //                 '<td><button class="btn btn-primary" onclick="editPost(' + item.id + ')">Edit</button> </td>' +
    //                 '<td><button class="btn btn-danger" onclick="deletePost(' + item.id + ')">Delete</button> </td>' +
    //                 '</tr>'
    //         })
    //         dataHtml += '</table>';
    //         container.prev().html(dataHtml);
    //     }
    // })
}

//RESET FORM CREATE
function resetFormCreate() {
    $('#title').val('');
    $('#content').val('');
    $('#createAt').val('');
    $('#likes').val('');
    $('#id').val('');
    $('#imgDiv').val('');
}