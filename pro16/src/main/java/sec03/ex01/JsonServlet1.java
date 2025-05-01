package sec03.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/json")
public class JsonServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 화면단에서 문자열데이터를 받아와서 json파일 형태로 화면단에 전송
		// 클라이언트로부터 받은 JSON 파라미터 추출
		// 요청 파라미터 중 jsonInfo라는 이름의 값(문자열)을 가져옵니다.
		String jsonInfo = request.getParameter("jsonInfo");
		
		try {
			JSONParser jsonParser = new JSONParser();
			// jsonInfo 문자열을 JSONObject(json파일 형태)로 파싱.
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonInfo);
			System.out.println("* 회원 정보*");
			System.out.println(jsonObject.get("name"));
			System.out.println(jsonObject.get("age"));
			System.out.println(jsonObject.get("gender"));
			System.out.println(jsonObject.get("nickname"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
