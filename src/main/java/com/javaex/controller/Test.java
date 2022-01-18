package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/board")
public class Test {

	// 메소드 일반
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String TestPrint() { // Controller이전 단계인 HandlerMapping영역에 이 메소드를 미리 넣어두길 명령함
		System.out.println("TestPrint"); //mapping: 예)test라는명령어가 오면 public void Test()를 실행시키는 기능

		return "/WEB-INF/views/Test.jsp";
	}

	@RequestMapping(value = "/writeForm", method = {RequestMethod.GET, RequestMethod.POST })
	public String TestPrint2() {
		System.out.println("TestPrint2");

		return "/WEB-INF/views/Test.jsp";
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String TestPrint3() {
		System.out.println("TestPrint3");

		return "/WEB-INF/views/Test.jsp";
	}

}