package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 글 상세내용 불러올때 업로드 된 파일 가져오기
@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		// 업로드 돼있는 이미지 파일 이름, 글번호 불러옴
		String imageFileName = (String)request.getParameter("imageFileName");
		String articleNO = request.getParameter("articleNO");
		System.out.println("imageFileName=" + imageFileName);
		OutputStream out = response.getOutputStream();
		// 글 번호에대한 파일경로
		String path = ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + imageFileName;
		File imageFile = new File(path);
		// HTTP 헤더는 클라이언트와 서버가 요청 또는 응답으로 부가적인 정보를 전송할 수 있도록 해준다. 
		// HTTP 헤더는 대소문자를 구분하지 않는 key: value로 이루어져있다.
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);
		FileInputStream in = new FileInputStream(imageFile);
		// 한번에 8kb씩 전송
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = in.read(buffer);
			if(count==-1) {
				break;
			}
			out.write(buffer, 0, count);	
		}
		in.close();
		out.close();
	}
}
