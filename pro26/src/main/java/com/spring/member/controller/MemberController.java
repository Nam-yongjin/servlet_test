package com.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.member.vo.MemberVO;

public interface MemberController {

	ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView addMember(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView removeMember(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView modMember(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

	ModelAndView updateMember(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception;

}