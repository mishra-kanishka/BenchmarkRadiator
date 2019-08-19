$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/getBenchmarkScore",
        type: "GET",
        dataType: 'JSON',
        contentType: "application/json",
    }).then(function(data) {
        if(data){
            var len = data.length;
            var txt = "";
            if(len > 0){
                for(var i=0;i<len;i++){
                    txt += "<tr><td>"+ "Result" + (i+1) +"</td>" +
                        "<td>"+data[i].digitalContentCreationScore+"</td>" +
                        "<td>"+data[i].essentialsScore+
                        "</td><td>"+data[i].pcmark10Score+"</td>" +
                        "<td>"+data[i].productivityScore+"</td></tr>";
                }
                txt += getRelativeChangeInfo(data)
                if(txt !== ""){
                    $("#table").append(txt).removeClass("hidden");
                }
            }
        }
    });
});

function getRelativeChangeInfo(data) {
    return "<tr><td>" + "Relative Change" + "</td>" +
        "<td>" + Math.abs((data[0].digitalContentCreationScore - data[1].digitalContentCreationScore) / (data[0].digitalContentCreationScore)).toFixed(2) + "</td>" +
        "<td>" + Math.abs((data[0].essentialsScore - data[1].essentialsScore) / (data[0].essentialsScore)).toFixed(2) + "</td>" +
        "<td>" + Math.abs((data[0].pcmark10Score - data[1].pcmark10Score) / (data[0].pcmark10Score)).toFixed(2) + "</td>" +
        "<td>" + Math.abs((data[0].productivityScore - data[1].productivityScore) / (data[0].productivityScore)).toFixed(2) + "</td>" +
        "</tr>";
}

