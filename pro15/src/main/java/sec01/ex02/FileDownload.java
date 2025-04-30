package sec01.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String file_repo = "C:\\file_repo";		// 다운받을 이미지가 있는 장소
		String fileName = (String) request.getParameter("fileName");
		
		System.out.println("fileName=" + fileName);		// 파일 확인용
		
		OutputStream out = response.getOutputStream(); // OutputStream 객체 생성(바이트 단위: 이미지, 동영상)
		String downFile = file_repo + "\\" + fileName;
		File f = new File(downFile);
		response.setHeader("Cache-Control", "no-cache"); // 캐시에 저장된 데이터 저지
		// 파일을 다운로드할 때 파일이름을 붙여 배치
		response.addHeader("Content-disposition", "attachment; fileName=" + fileName);
						
		FileInputStream in = new FileInputStream(f);
		byte[] buffer = new byte[1024 * 8];  // 8바이트 씩 읽음
		while (true) {
			int count = in.read(buffer); // 버퍼에 읽고
			if (count == -1)
					break;  // 더이상 파일이 없을 때 벗어남
				out.write(buffer, 0, count);   // 8바이트 씩 출력 (버퍼 내용을 0에서 count까지)
		}
		in.close();
		out.close();
	}
}
