package sec03.brd07;

import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	public BoardService() {
		boardDAO = new BoardDAO();
	}

	public List<ArticleVO> listArticles() {
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}

	public int addArticle(ArticleVO article){
		return boardDAO.insertNewArticle(article);		
	}
	
	public ArticleVO viewArticle(int articleNO) {
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNO);
		return article;
	}
	
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}
	
	// 부모글과 연계된 답글도 함께 처리해야 하기에  
	// Controller에서 이미지 파일 삭제를 위해서 부모글 에 연계된 답글의 글번호들의 정보를 리스트에 담아서 리턴한다.
	// 2개의 DAO매서드를 하나의 논리단위로 처리
	public List<Integer> removeArticle(int articleNO){
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		boardDAO.deleteArticle(articleNO);
		return articleNOList;
	}
	
	public int addReply(ArticleVO article) {
		// 새글쓰기 DAO기능을 이용해 답변 추가
		return boardDAO.insertNewArticle(article);
	}

}
