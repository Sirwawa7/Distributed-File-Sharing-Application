function login() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    var requestBody = 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password);

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: requestBody
    }).then(response => response.text()).then(data => {
        alert(data);
        if (data === 'Login successful') {
            document.getElementById('auth').style.display = 'none';
            document.getElementById('file-upload').style.display = 'block';
            document.getElementById('file-download').style.display = 'block';
        }
    }).catch(error => {
        console.error('Error:', error);
    });
}

function uploadFile() {
    var fileInput = document.getElementById('fileInput');
    var file = fileInput.files[0];

    var formData = new FormData();
    formData.append('file', file);

    fetch('/upload?fileName=' + encodeURIComponent(file.name), {
        method: 'POST',
        body: formData
    }).then(response => response.text()).then(data => {
        alert(data);
    }).catch(error => {
        console.error('Error:', error);
    });
}

function downloadFile() {
    var fileName = document.getElementById('fileName').value;

    fetch('/download?fileName=' + encodeURIComponent(fileName), {
        method: 'GET'
    }).then(response => response.blob()).then(blob => {
        var url = window.URL.createObjectURL(blob);
        var a = document.createElement('a');
        a.href = url;
        a.download = fileName;
        document.body.appendChild(a);
        a.click();
        a.remove();
    }).catch(error => {
        console.error('Error:', error);
    });
}
