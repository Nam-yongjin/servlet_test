<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<title>JSON 테스트</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(function() {
        $("#checkJson").click(function() {
        	// '  +', 이건 한줄에 쓰기 길어져서 다음줄에도 쓰기위해 사용 >> 한줄에 쓸려면 , 하나로 충분
        	var jsonStr = '{"members":[{"name":"박지성","age":"25","gender":"남자","nickname":"날센돌이"}'
        	+', {"name":"손흥민","age":"30","gender":"남자","nickname":"탱크"}] }';
	     // 문자열을 JSON파일 형식으로 저장
	        var jsonInfo = JSON.parse(jsonStr);
		    var output ="회원 정보<br>";
		    output += "=======<br>";
		    for(var i in jsonInfo.members){
	            output += "이름: " + jsonInfo.members[i].name+"<br>";
		        output += "나이: " + jsonInfo.members[i].age+"<br>";
		        output += "성별: " + jsonInfo.members[i].gender+"<br>";
		        output += "별명: " +jsonInfo.members[i].nickname+"<br><br><br>";
		    }
	
	        $("#output").html(output);
        }); 
    });       
</script>
</head>

<body>
    <a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
</body>
</html>
