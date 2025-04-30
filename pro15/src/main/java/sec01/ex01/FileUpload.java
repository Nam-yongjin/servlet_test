package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//multipart/form-data향식으로 전송된 form 데이터를 ‘파일’ 또는 form ‘아이템(필드)’으로 표현한다.
//사용자가 업로드한 'File '데이터나 사용자가 ' input text '에 입력한 일반 요청 데이터에 대한 객체.
//예를 들어, 'FileItem isFormField()'메서드의 리턴값이 'true'이면 'text'같은 일반 입력 데이터이고, 'false'이면 
//파일데이터임을 알 수 있음. 즉, 리턴값이 'false'인 경우에만 업로드된 파일인 것으로 인지하여 처리하면 됨.
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

// 업로드할 파일저장 위치와 업로드 가능한 최대 파일크기 설정
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String encoding = "utf-8";
		File currentDirPath = new File("C:\\file_repo");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);	// 첨부 최대 사이즈
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				
				// ServletFileUpload클래스 통해 request 객체에서 List로 매개변수를 받고, 
			    // FileItem 인터페이스로  Form 데이터로 표현
				if (fileItem.isFormField()) {	// 문자열인지 파일형태인지 구분
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
				} else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
	
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");		// 윈도우 기반 경로 처리
						
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");		// 유닉스, 리눅스 기반 경로처리
						}
						
						String fileName = fileItem.getName().substring(idx + 1);	// 순수 파일명 처리
						
						// 현 폴더 "C:\\file_repo\\"에 파일명
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						
						fileItem.write(uploadFile);		// 저장소 파일 업로드
					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
