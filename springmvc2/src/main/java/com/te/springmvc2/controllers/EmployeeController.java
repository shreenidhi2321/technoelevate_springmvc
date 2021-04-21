package com.te.springmvc2.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.springmvc2.bean.EmployeeBean;
import com.te.springmvc2.dao.EmployeeDAO;
import com.te.springmvc2.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/empLogin")
	public String getLogin() {
		return "loginPage";

	}

	@PostMapping("/empLogin")
	public String authenticate(int id, String password, HttpServletRequest request, ModelMap map) {
		EmployeeBean employeeBean = service.authenticate(id, password);

		if (employeeBean != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("emp", employeeBean);
			return "homePage";

		} else {
			map.addAttribute("errMsg", "inavlid crendtials");
			return "loginPage";
		}

	}

	@GetMapping("/search1")
	public String getSearchForm(ModelMap map, HttpSession session) {
		if (session.getAttribute("emp") != null) {
			return "search";

		} else {
			map.addAttribute("errMsg", "inavlid crendtials");
			return "loginPage";
		}

	}

	@GetMapping("/searchForm")
	public String searchEmp(int id, ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean) {
		if (bean != null) {
			EmployeeBean employeeBean2 = service.getEmployee(id);
			if (employeeBean2 != null) {
				map.addAttribute("data", employeeBean2);

			} else {
				map.addAttribute("msg", "data not found for id :"+id);

			}
			return "search";
			
		}else {
			map.addAttribute("errMsg", "please login first");
			return "loginPage";
		}

	}
	@GetMapping("/logout")
	public String logout(HttpSession session, ModelMap map) {
		session.invalidate();
		map.addAttribute("msg", "logout succesful");
		return "loginPage";
		
		
	}
	@GetMapping("/getdeleteform")
	public String getDeleteForm(ModelMap map,@SessionAttribute(name = "emp",required = false)EmployeeBean employeeBean) {
		if(employeeBean!=null) {
			return "delete";
		}else {
			map.addAttribute("errMsg", "please login first");
			return null;
			
		}
		
	}
	@GetMapping("/delete")
	public String deleteEmployee(int id,@SessionAttribute(name = "emp",required = false)EmployeeBean bean,ModelMap map) {
		if(bean!=null) {
			boolean deleted = service.deleteEmp(id);
			if(deleted==true) {
				map.addAttribute("msg", "deleted successfully");
				return "delete";
			}
			else {
				map.addAttribute("errmsg", "user not found");
				return "delete";
			}
			
		}
		
		return null;
		
	}
	@GetMapping("/viewall")
	public String viewAllEmployee(ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean) {
		if (bean != null) {
			List<EmployeeBean> employeeBeans = service.getAllEmployees();
			map.addAttribute("empdata", employeeBeans);
			return "allData";
		} else {
			map.addAttribute("msg", "no employees found");
			return "allData";
		}

	}
	
		
	

}//end of controller
