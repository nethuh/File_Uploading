let baseUrl = "http://localhost:8080/FileUpload_BackEnd_war/";

loadTheLastUploadedImage();
let latestImageData = null;

$("#btnUpload").on('click', function () {
    let data = new FormData();
    let file = $("#file")[0].files[0];
    let fileName = $("#file")[0].files[0].name;
    data.append("image", file, fileName);

    $(".progress").show();
    $("#upload-progress").css('width', '0%').attr('aria-valuenow', 0);

    $.ajax({
        url: baseUrl + "upload",
        method: 'post',
        async: true,
        contentType: false,
        processData: false,
        data: data,
        xhr: function () {
            var xhr = new window.XMLHttpRequest();
            // Listen to the upload progress
            xhr.upload.addEventListener("progress", function (evt) {
                if (evt.lengthComputable) {
                    var percentComplete = (evt.loaded / evt.total) * 100;
                    // Update the progress bar
                    $("#upload-progress").css('width', percentComplete + '%').attr('aria-valuenow', percentComplete);
                }
            }, false);
            return xhr;
        },
        success: function (resp) {
            latestImageData = resp;
            $('#display').attr('src', `data:Image/png;base64,${resp}`);
        },
        error: function (err) {
            console.log(err);
        }
    });
});

$("#btnLoad").on('click', function () {
    if (latestImageData) {
        updatePhotoTable(`Image${$("#photo-table tbody tr").length + 1}`, latestImageData);
    }
});

$("#btnLoad").on('click',function () {
    loadTheLastUploadedImage();
});

function loadTheLastUploadedImage() {
    $.ajax({
        url: baseUrl + "upload",
        method: 'get',
        dataType: 'json',
        success: function (resp) {

            $('#display').attr('src', `${resp}`);
            console.log(resp)

        },
        error: function (err) {
            console.log(err);
        }
    });
}

function updatePhotoTable(fileName, resp) {
    let photoTableBody = $("#photo-table tbody");
    photoTableBody.prepend(`
        <tr>
            <td>${photoTableBody.children().length + 1}</td>
            <td>${fileName}</td>
            <td><img src="data:Image/png;base64,${resp}" style="max-width: 50px;"></td>
        </tr>
    `);
}