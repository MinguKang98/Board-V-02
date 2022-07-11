package com.example.boardv02.dao;

import com.example.boardv02.sqlMap.SqlSessionManager;
import com.example.boardv02.vo.BoardVO;
import com.example.boardv02.vo.CategoryVO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CategoryDAO {

    // 모든 카테고리 불러오기
    public List<CategoryVO> getCategoryList(){
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<CategoryVO> categoryList = null;
        try{
            categoryList = sqlSession.selectList("CategoryMapper.getCategoryList");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

        return categoryList;
    }

    // 카테고리 하나 불러오기
    public CategoryVO getCategoryById(int categoryId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CategoryVO category = null;
        try{
            category = sqlSession.selectOne("CategoryMapper.getCategoryById", categoryId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            sqlSession.close();
        }

        return category;
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<CategoryVO> categoryList = categoryDAO.getCategoryList();
        for (CategoryVO c :
                categoryList) {
            System.out.println("c.getName() = " + c.getName());   
        }
        CategoryVO categoryById = categoryDAO.getCategoryById(3);
        System.out.println("categoryById.getName() = " + categoryById.getName());
    }
}
