package com.example.boardv02.dao;

import com.example.boardv02.sqlMap.SqlSessionManager;
import com.example.boardv02.vo.BoardVO;
import com.example.boardv02.vo.SearchCriteriaPaging;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;

public class BoardDAO {

    // 모든 게시판 불러오기
    public List<BoardVO> getBoardList(){
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<BoardVO> boardList = null;
        try{
            boardList = sqlSession.selectList("BoardMapper.getBoardList");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

        return boardList;
    }

    // 검색 조건 해당하는 board 갯수 
    public int getTotalBoardCountWithSearchCriteria(SearchCriteriaPaging searchCriteriaPaging) {
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int totalBoardCountWithSearchCriteria = 0;
        try {
            HashMap<String, Object> searchCriteriaMap = searchCriteriaPaging.getSearchCriteriaMap();
            totalBoardCountWithSearchCriteria =
                    sqlSession.selectOne("BoardMapper.getTotalBoardCountWithSearchCriteria",
                            searchCriteriaMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return totalBoardCountWithSearchCriteria;
    }

    // 검색 조건 해당하는 board 페이징
    public List<BoardVO> getBoardListWithPagingAndSearchCriteria(SearchCriteriaPaging searchCriteriaPaging) {
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<BoardVO> boardList = null;
        try {
            searchCriteriaPaging.pageSetting();
            HashMap<String, Object> pagingAndSearchCriteriaMap =
                    searchCriteriaPaging.getPagingAndSearchCriteriaMap();
            boardList = sqlSession.selectList("BoardMapper.getBoardListWithPagingAndSearchCriteria",
                    pagingAndSearchCriteriaMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return boardList;
    }

    // 게시판 하나 불러오기
    public BoardVO getBoardById(int boardId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BoardVO board = null;
        try{
            board = sqlSession.selectOne("BoardMapper.getBoardById", boardId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

        return board;
    }

    // 게시판 추가
    public int addBoard(BoardVO board) {
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            sqlSession.insert("BoardMapper.addBoard", board);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }
        return board.getBoardId();
    }

    // 게시판 삭제
    public void deleteBoard(int boardId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            sqlSession.delete("BoardMapper.deleteBoard", boardId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }
    }

    public static void main(String[] args) {
        BoardDAO boardDAO = new BoardDAO();
        SearchCriteriaPaging search = new SearchCriteriaPaging();
        search.setCreatedDateFrom("2022-07-10");
        search.setCreatedDateTo("2022-07-11");
        search.setCategoryId(4);
        search.setText("test");
        int count = boardDAO.getTotalBoardCountWithSearchCriteria(search);
        System.out.println("count = " + count);
        search.setTotalRowCount(count);
        List<BoardVO> list = boardDAO.getBoardListWithPagingAndSearchCriteria(search);
        for (BoardVO b : list) {
            System.out.println("b.getBoardId() = " + b.getBoardId());
        }
    }
}
