package com.tms.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tms.domain.User;
import com.tms.service.AuthUserService;
import com.tms.service.ProjectService;
import com.tms.service.UserService;

@Controller
public class LoginController  {

	@Autowired
	private AuthUserService authUserService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLoginPage(ModelMap modelMap){
		System.out.println("GET login");
		
		User emptyUser = new User();
		modelMap.addAttribute("user", emptyUser);
		
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, ModelMap modelMap,  HttpServletRequest request){

		if(bindingResult.hasErrors()){
			//ASSERT: validation failed
			return "login";
		}
		//ASSERT: validation successful
		
		System.out.println(user);
		User pulledUser = userService.getUserByUsername(user.getUsername());
		System.out.println(pulledUser);
		
		//Null check
		if(pulledUser == null){
			modelMap.addAttribute("errorMessage", "Username doesn't exist");
			return "login";
		}
		
		//Authenicate user
		boolean loginSuccess = authUserService.auth(pulledUser,user.getPassword());
		
		if(loginSuccess){
			HttpSession session = request.getSession();
			//ASSERT: authentication successful

			//Authorization
			session.setAttribute("user", pulledUser);
			if(pulledUser.getRole().getId() == 2){
				//ASSERT: Manager
				try{
					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority("ROLE_manager"));
					UserDetails userDetails = (UserDetails)new User(pulledUser);
					Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
					//Just used for syso lol
					Collection<GrantedAuthority> sim2 = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
			                .getAuthentication().getAuthorities();
					System.out.println(sim2);
				}catch(Exception e){
					e.printStackTrace();
				}
				session.setAttribute("role", "2");
				System.out.println("manager sucess");
				return "redirect:manager";
			}else if(pulledUser.getRole().getId() == 1);
				System.out.println("developer sucess");
				//ASSERT: Developer
				try{
					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority("ROLE_developer"));
					UserDetails userDetails = (UserDetails)new User(pulledUser);
					Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
					/*List<GrantedAuthority> authorities = null;
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					if (principal instanceof UserDetails) {
					    ((UserDetails) principal).getAuthorities().add(new SimpleGrantedAuthority("ROLE_developer"));
					}*/
					Collection<GrantedAuthority> sim2 = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
			                .getAuthentication().getAuthorities();
					System.out.println(sim2);
				}catch(Exception e){
					e.printStackTrace();
				}
				session.setAttribute("role", "1");
				return "redirect:developer";
		}else {
			//ASSERT: authentication failed
			modelMap.addAttribute("errorMessage", "Username/Password incorrect");
			return "login";	
		}
	}
	
	
	@RequestMapping(value="/loginProcess")
	public String doLoginProcess(ModelMap modelMap){
		System.out.println("=======doLoginProcess");
		return null;
	}
	
	
/*		Not needed as it is applied through the SecurityConfiguration
	@RequestMapping(value="/logout")
	public String doLogOut(HttpServletRequest request,HttpServletResponse response){
		System.out.println("logout");
		Authentication auth = SecurityContextHolder.getContext()
		                .getAuthentication();
		        if (auth != null) {
		            new SecurityContextLogoutHandler().logout(request, response, auth);
		        }
		        return "redirect:/login";
	}	
	*/
	
	
	private String getLoggedInUserName() {
        System.out.println("getLoggedInUsername");
		
		Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }
}