package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	// 필드

	// 생성자

	// 메소드 gs

	// 메소드 일반

	/*
	 * @RequestMapping(value = "/phone/writeForm", method = { RequestMethod.GET,
	 * RequestMethod.POST })
	 *  public String writeForm() {
	 * System.out.println("PhoneController > writeForm() ");
	 * 
	 * return "/WEB-INF/views/writeForm.jsp"; // return writeForm; 이라 써도 무방.
	 * WebUtil의 (path)에 return값 넣는 메커니즘. }
	 * 
	 * @RequestMapping(value = "/phone/write", method = { RequestMethod.GET,
	 * RequestMethod.POST }) 
	 * public String write(@ModelAttribute PersonVo personVo)
	 * { System.out.println("PhoneController > write() "); //필드와 파라미터 이름이 동일할 시 스프링이
	 * 자동으로 넣어줌
	 * 
	 * System.out.println(personVo);
	 * 
	 * // 저장 return ""; }
	 */

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController > writeForm() ");

		return "/WEB-INF/views/writeForm.jsp"; // return writeForm; 이라 써도 무방. WebUtil의 (path)에 return값 넣는 메커니즘.
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("PhoneController > write() ");

		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);

		PersonVo personVo = new PersonVo(name, hp, company);

		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(personVo);

		// 저장
		return "redirect: /phone/list"; // redierect를 안 쓰면 phone/list파일을 찾는다
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) { // model이란 그릇에 컨트롤러가 정보 넣어두면 디스패처가 모델 가져감
		System.out.println("PhoneController > list() ");

		// MVC방식으로 세 과정을 최대한 분리함
		// 다오에서 리스트를 가져온다
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList.toString());

		// 컨트롤러 --> DS데이터를 보낸다(model)
		model.addAttribute("personList", personList);

		// jsp정보를 리턴한다(view) cf>Model(데이터저장, model에 넣으면 리퀘스트어트리뷰트에 넣기로 했음) View(보여주기:
		// 리퀘스트에서 꺼내씀) Control(조합조종)
		return "/WEB-INF/views/list.jsp";
	}

	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(@RequestParam("no") int no, Model model) {
		System.out.println("PhoneController > updateForm() ");

		PhoneDao phoneDao = new PhoneDao();
		PersonVo personVo = phoneDao.getPerson(no);

		model.addAttribute("personVo", personVo);

		return "/WEB-INF/views/updateForm.jsp";
	}

	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > update");

		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(personVo);

		return "redirect: /phone/list";
	}

}
