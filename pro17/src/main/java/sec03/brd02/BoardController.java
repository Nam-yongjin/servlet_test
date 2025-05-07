package sec03.brd02;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";

	BoardService boardService;
	ArticleVO articleVO;
	
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board02/listArticles.jsp";
				
			} else if (action.equals("/listArticles.do")) {  // action이 listArticles.do 이면 전체글 조회
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);  // 조회한 글목록 articlesList로 바인딩
				nextPage = "/board02/listArticles.jsp";  // listArticles.jsp로 포워딩
				
			}else if (action.equals("/articleForm.do")) { // 글쓰기창
				nextPage = "/board02/articleForm.jsp";
				
			} else if (action.equals("/addArticle.do")) { // 새 글 추가
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				articleVO.setParentNO(0);		// 새 글이므로 부모글 번호 0
				articleVO.setId("hong");		// 새 글 작성자
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.addArticle(articleVO);
				
				nextPage = "/board/listArticles.do";
				
			}else {
				nextPage = "/board02/listArticles.jsp";

			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		// DiskFileItemFactory: 파일을 저장을 담당할 객체 준비
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		// ServletFileUpload: 멀티 파트 데이터로부터 각 파트의 추출을 담당할 객체 준비
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List items = upload.parseRequest(request);
			
			for (int i = 0; i < items.size(); i++) {
				// FileItem : 멀티 파트 데이터를 읽기
				FileItem fileItem = (FileItem) items.get(i);
				
				// FileItem
				// 사용자가 업로드한 File데이터나 사용자가 input text에 입력한 일반 요청 데이터에 대한 객체. 
				// FileItem.isFormField() 메서드의 리턴값이 true이면 text같은 일반 입력 데이터이며, false이면 파일 데이터입니다. 
				// 즉 리턴값이 false인 경우에만 업로드된 파일인것으로 인지하여 처리하면 된다.
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					
					// 파일 업로드로 같이 전송된 새 글 관련 매개변수를 Map에 키,값 으로 저장한 후 반환하고
					// 새 글과 관련된 title, content를 Map에 저장
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
				} else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					//System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					
					// 익스플로러에선 파일 업로드시 파일 경로 까지 같이 전송되므로 구문 삭제
					//articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					if (fileItem.getSize() > 0) {
						// lastIndexOf( ) : 마지막 문자열의 index를 반환한다. 없으면 -1 반환한다. 
						int idx = fileItem.getName().lastIndexOf("\\");		// 윈도우
						
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");		// 리눅스 등
						}

						// getName( ): 업로드한 파일의 이름을 구한다(경로포함)
						// substring( ): 문자열 자르기 
					    // substring(start): start 위치에서 끝까지
					    // substring(start, end): start 위치에서 end 위치까지
						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일명:" + fileName);
						articleMap.put(fileItem.getFieldName(), fileName);    
						
                    //익스플로러에서 업로드 파일의 경로 
                    // 경로제거 후 파일명 Map에 추가함
					File uploadFile = new File(currentDirPath + "\\" + fileName);
					fileItem.write(uploadFile);

					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}

}
