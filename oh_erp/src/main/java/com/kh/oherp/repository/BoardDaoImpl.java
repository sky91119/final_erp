package com.kh.oherp.repository;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.oherp.entity.BoardDto;



	@Repository
	public class BoardDaoImpl implements BoardDao{

		@Autowired
		private SqlSession sqlSession;
		
		@Override
		public int write(BoardDto boardDto) {
			//1. 게시글 번호 생성
			int no = sqlSession.selectOne("board.getSeq");
			//2. 게시글 등록
			boardDto.setBoard_no(no);
			sqlSession.insert("board.write", boardDto);

			return no;
		}

		@Override
		public BoardDto get(int board_no) {
			BoardDto boardDto = sqlSession.selectOne("board.get", board_no);
			return boardDto;
		}

		@Override
		public BoardDto read(int board_no) {
			sqlSession.update("board.view", board_no);
			BoardDto boardDto = sqlSession.selectOne("board.get", board_no);
			return boardDto;
		}

		@Override//삭제
		public void delete(int board_no) {
		sqlSession.delete("board.del", board_no);
			
		}

		// 게시물 수정
		@Override
		public void update(BoardDto boardDto) throws Exception {
			sqlSession.update("board.update", boardDto);
		}
		
//		--
		@Override
		public int countBoard() {
			// TODO Auto-generated method stub
			return 0;
		}
	}