package com.basepackage;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basepackage.Entities.FeedbackAttr;
import com.basepackage.Entities.FeedbackEntries;
import com.basepackage.Entities.LoginUser;
import com.basepackage.Entities.User;
import com.basepackage.Entities.User_project_link;
import com.basepackage.Repositories.FeedbackAttrRepository;
import com.basepackage.Repositories.FeedbackEntriesRepository;
import com.basepackage.Repositories.UserProjectLinkRepository;
import com.basepackage.Repositories.UserRepository;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;



@RestController
public class HelloController {
	
	@Autowired
	public FeedbackAttrRepository feedbackAttrRepository;
	
	@Autowired
	public FeedbackEntriesRepository feedbackEntriesRepository;
	
	@Autowired
	public UserProjectLinkRepository userProjectLinkRepository;
	
	@Autowired
	public UserRepository userRepository;

	@GetMapping("/api/v1/t")
	public String index() {
		return "Greetings from Spring boot!";
	}

	@PostMapping("/login")
	public  LoginUser getm(@RequestBody LoginUser loginuser) {
		return loginuser;
	}
	
	
	@GetMapping("/api/v1/eligibleFeedbackAttrs")
	
	public List<FeedbackAttr> getFeedbackAttr(@RequestParam("giverDesignation") int giverDesignation, 
			@RequestParam("receiverDesignation") int receiverDesignation) {
		
		List<FeedbackAttr>  list = feedbackAttrRepository.findAll();
		
		List<FeedbackAttr> newList = new ArrayList<FeedbackAttr>();
		
		for(int i=0;i<list.size();i++ ) {
			System.out.println(list.get(i).getAttr_name());
		}
		
		if(giverDesignation == 2) {
			giverDesignation = 1;
		}
		if(receiverDesignation == 2) {
			receiverDesignation = 1;
		}
		if(giverDesignation == receiverDesignation) { // giving for a peer
			newList.add(list.get(2)); newList.add(list.get(3));
		} else if(giverDesignation > receiverDesignation) { //giving for a junior
			newList.add(list.get(0)); newList.add(list.get(1));
		} else if(receiverDesignation > giverDesignation) { // giving for a senior
			newList.add(list.get(4)); newList.add(list.get(5));
		}
		
		
		for(int i=0;i<newList.size();i++ ) {
			System.out.println(newList.get(i).getAttr_name());
		}
		
		return newList;
	}
	
	@PostMapping("/api/v1/submitFeedback")
	public String testit(@RequestBody List<FeedbackEntries> list ) {
		System.out.println(list.get(0).getFb_desc());
		feedbackEntriesRepository.saveAll(list);
		return "success";
	}
	
	@GetMapping("/api/v1/manageFeedback") // only employee with role -2(superuser) has access to this resource
	public Set<FeedbackEntries> mytest( @RequestParam("mgrId") Long empId) { 
		
		List<FeedbackEntries> fbEntries = feedbackEntriesRepository.getFeedbacksOfMySubordinates(empId);
		Set<FeedbackEntries> accumalatedList = new HashSet<FeedbackEntries>();
		for(int i=0;i<fbEntries.size();i++) {
			final int c = i;
		List<FeedbackEntries> temp = 	fbEntries.stream()                        // Convert to steam
            .filter(x -> x.getFb_target_id() == fbEntries.get(c).getFb_target_id() )        // we want "jack" only
            .collect(Collectors.toList());
		
		FeedbackEntries acc_entry = fbEntries.get(c); int total = 0;
		for(int k =0;k<temp.size();k++) {
			total+= Integer.parseInt(temp.get(k).getFeedback_entriescol());
		}
		accumalatedList.add(fbEntries.get(i));
		}
		
		return accumalatedList;
	}
	
	@GetMapping("/api/v1/getMyProjectMembers") 
	public List<User> getMyProjectMembers(@RequestParam(value = "userid") Long userId) {
		
		//Long projectId = userProjectLinkRepository.getMyProjectId(userId);
		//System.out.println("My ProjectId"+projectId);
		List<Long> empIds =  userProjectLinkRepository.findUserByProject(userId);
		
		List<User> users =  userRepository.findAllById(empIds);
		java.util.ListIterator itr = users.listIterator();
		
		
		
		while(itr.hasNext()) {
			if ( ((User)itr.next()).getEmpId() == userId.intValue()) {
				itr.remove();
				
			}
		}
		return users;
	}
	
	
		
	
//	@GetMapping("")
//	public String testit(@RequestBody List<FeedbackEntries> list ) {
//		System.out.println(list.get(0).getFb_desc());
//		feedbackEntriesRepository.saveAll(list);
//		return "succeess";
//	}
//	

}