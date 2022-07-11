package com.example.boardv02.dao;

import com.example.boardv02.sqlMap.SqlSessionManager;
import com.example.boardv02.vo.BoardVO;
import com.example.boardv02.vo.CommentVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CommentDAO {

    // 게시글 id 로 댓글 불러오기
    public List<CommentVO> getCommentListByBoardId(int boardId){
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<CommentVO> commentList = null;
        try{
            commentList = sqlSession.selectList("CommentMapper.getCommentListByBoardId", boardId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

        return commentList;
    }

    // 댓글 추가
    public int addComment(CommentVO comment) {
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try{
            sqlSession.insert("CommentMapper.addComment", comment);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }
        return comment.getCommentId();
    }

    public static void main(String[] args) {
        CommentDAO commentDAO = new CommentDAO();
        List<CommentVO> commentList = commentDAO.getCommentListByBoardId(5);
        for (CommentVO comment :
                commentList) {
            System.out.println("comment.getContent() = " + comment.getContent());
        }
        CommentVO commentVO = new CommentVO();
        commentVO.setContent("이게 맞더라고 ㅋㅋ");
        commentVO.setBoardId(5);
        int commentId = commentDAO.addComment(commentVO);
        System.out.println("commentId = " + commentId);
    }
}
