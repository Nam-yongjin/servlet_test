package sec03.brd08;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

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
		
		HttpSession session;	// 답글에 대한 부모 글번호를 저장하기위해 세션사용
		
		String action = request.getPathInfo();
		System.out.println("action:" + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			if (action == null) {
				// 페이지 넘버
				// section : 100단위(10개게시글 * 10페이지), pageNum : 1페이지단위
				String _section = request.getParameter("section");
				String _pageNum = request.getParameter("pageNum");
				
				// null값이면 페이지값 1(첫페이지)로 전송
				int section = Integer.parseInt(((_section == null) ? "1" : _pageNum));
				int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
				Map<String, Integer> pagingMap = new HashMap<String, Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				
				// BoardService에서 생성된 articlesMap에 바인딩한 자료 totalArticles(총 게시글수)와
				// artticlesList(해당 페이지의 10개 게시글)이 바인딩되어 반환됨
				Map articlesMap=boardService.listArticles(pagingMap);
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				request.setAttribute("articlesMap", articlesMap);
				nextPage = "/board07/ listArticles.jsp";
				
			} else if (action.equals("/listArticles.do")) {  // action이 listArticles.do 이면 전체글 조회
				String _section=request.getParameter("section");
				String _pageNum=request.getParameter("pageNum");
				int section = Integer.parseInt(((_section==null)? "1":_section) );
				int pageNum = Integer.parseInt(((_pageNum==null)? "1":_pageNum));
				Map pagingMap=new HashMap();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				Map articlesMap=boardService.listArticles(pagingMap);
				articlesMap.put("section", section);
				articlesMap.put("pageNum", pageNum);
				request.setAttribute("articlesMap", articlesMap);	// 조회한 글목록 articlesMap로 바인딩
				nextPage = "/board07/listArticles.jsp";		 // listArticles.jsp로 포워딩
							
			}else if (action.equals("/articleForm.do")) { // 글쓰기창
				nextPage = "/board07/articleForm.jsp";
				
			} else if (action.equals("/addArticle.do")) { // 새 글 추가
				int articleNO = 0;
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				articleVO.setParentNO(0);		// 새 글이므로 부모글 번호 0
				articleVO.setId("hong");		// 새 글 작성자
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				articleNO = boardService.addArticle(articleVO); 
				
				// 첨부파일이 있을때 파일명 겹치는거 방지 >> temp 폴더를 거친 후 번호폴더 생성후 옮김
				// 한번 더 겹치면 temp에 저장
				if(imageFileName!=null && imageFileName.length()!=0) {
				    File srcFile = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\"+imageFileName);
				    File destDir = new File(ARTICLE_IMAGE_REPO +"\\"+articleNO);
				    destDir.mkdirs();
				    FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" 
				         +"  alert('새글을 추가했습니다.');" 
						 +" location.href='"+request.getContextPath()+"/board/listArticles.do';"
				         +"</script>");

				return;
			}else if(action.equals("/viewArticle.do")){
				String articleNO = request.getParameter("articleNO");
				articleVO=boardService.viewArticle(Integer.parseInt(articleNO));
				request.setAttribute("article",articleVO);
				nextPage = "/board07/viewArticle.jsp";
			
				// 컨트롤러에서 수정을 요청하면 upload()를 이용해 수정 데이터를 Map으로 가져온다. 
				// Map의 데이터를 다시 ArticleVO객체의 속성에 저장한 후 SQL문으로 전달하여 수정 데이터를 반영한다. 
				// 마지막으로 temp폴더에 업로드된 수정 이미지를 다시 글번호 폴더로 이동하고 글번호 폴더의 원래 이미지를 삭제한다.
			}else if (action.equals("/modArticle.do")) {
				Map<String, String> articleMap = upload(request, response);
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				articleVO.setArticleNO(articleNO);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.modArticle(articleVO);		// 전송된 글정보를 이용해 글을 수정
				if (imageFileName != null && imageFileName.length() != 0) {
					String originalFileName = articleMap.get("originalFileName");
					// 수정된 이지미파일을 폴더로 이동
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					// 전송된 originalFileName 을 이용해 기존의 파일 삭제
					File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + originalFileName);
					oldFile.delete();
				}
				PrintWriter pw = response.getWriter();
				// 글 수정후 글상세화면 나타냄
				pw.print("<script>" + "  alert('글을 수정했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				return;
			
			}else if(action.equals("/removeArticle.do")) {
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				// articleNO값에 대한 글을 삭제후 삭제된 부모글과 자식글의 articleNO목록을 불러옴
				List<Integer> articleNOList = boardService.removeArticle(articleNO);
				// 삭제된 글들의 이미지 저장폴더들을 삭제
				for(int _articleNO : articleNOList) {
					File imgDir = new File(ARTICLE_IMAGE_REPO + "\\" + _articleNO);
					if(imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('글을 삭제했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/listArticles.do';" + "</script>");
				return;

			}else if (action.equals("/replyForm.do")) {
				
				// 답글창요청시 미리 부모 글번호를 parentNO속성으로 세션에 저장
				// viewArticle.jsp에서 articleNO->parentNO(DOM->script)
				int parentNO = Integer.parseInt(request.getParameter("parentNO"));
				session = request.getSession();
				session.setAttribute("parentNO", parentNO);
				nextPage = "/board07/replyForm.jsp";
				
			}else if (action.equals("/addReply.do")) {
				
				// 답글 전송시 세션에 저장된 parentNO를 가져옴
				session = request.getSession();
				int parentNO = (Integer) session.getAttribute("parentNO");
				session.removeAttribute("parentNO");
				Map<String, String> articleMap = upload(request, response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				// 답글의 부모 글번호 설정
				articleVO.setParentNO(parentNO);
				articleVO.setId("lee");		// 답글 작성자
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				// 답글을 테이블에 추가
				int articleNO = boardService.addReply(articleVO);
				
				// 답글에 첨부한 이미지 답글번호폴더에맞게 이동
				if (imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('답글을 추가했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/viewArticle.do?articleNO=" + articleNO + "';" + "</script>");
				return;

			}else {
				nextPage = "/board07/listArticles.jsp";
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
					// articleMap.put(fileItem.getFieldName(), fileItem.getName());
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
						
                    // 익스플로러에서 업로드 파일의 경로 
                    // 경로제거 후 파일명 Map에 추가함
					File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
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
