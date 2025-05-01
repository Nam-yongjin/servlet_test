<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
  <title>JSON 테스트</title>
  <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(function() {
        $("#checkJson").click(function() {
        var jsonStr  = '{"name":"박지성", "age":25, "gender":"남자", "nickname":"날쎈돌이" }';    
     // 문자열을 JSON파일 형식으로 저장
        var jsonInfo = JSON.parse(jsonStr);
        var output ="회원 정보<br>";
        output += "=======<br>";
        output += "이름: " + jsonInfo.name+"<br>";
       	output += "나이: " + jsonInfo.age+"<br>";
      	output += "성별: " + jsonInfo.gender+"<br>";
      	output += "별명: " + jsonInfo.nickname+"<br>"; 

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
