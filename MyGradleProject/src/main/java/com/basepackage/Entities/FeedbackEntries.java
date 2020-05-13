package com.basepackage.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="feedback_entries")
public class FeedbackEntries {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long fb_id;
		
		private int fb_src_id;
		
		private int fb_target_id;
		
		private String fb_desc;
		
		private int fb_attr_id;
		
		
		
		public Long getFb_id() {
			return fb_id;
		}

		public void setFb_id(Long fb_id) {
			this.fb_id = fb_id;
		}

		public int getFb_src_id() {
			return fb_src_id;
		}

		public void setFb_src_id(int fb_src_id) {
			this.fb_src_id = fb_src_id;
		}

		public int getFb_target_id() {
			return fb_target_id;
		}

		public void setFb_target_id(int fb_target_id) {
			this.fb_target_id = fb_target_id;
		}

		public String getFb_desc() {
			return fb_desc;
		}

		public void setFb_desc(String fb_desc) {
			this.fb_desc = fb_desc;
		}

		public String getFeedback_entriescol() {
			return feedback_entriescol;
		}

		public void setFeedback_entriescol(String feedback_entriescol) {
			this.feedback_entriescol = feedback_entriescol;
		}

		public int getFb_attr_id() {
			return fb_attr_id;
		}

		public void setFb_attr_id(int fb_attr_id) {
			this.fb_attr_id = fb_attr_id;
		}

		private String feedback_entriescol;
		
		
		@Override
		public boolean equals(Object obj) {
		// TODO Auto-generated method stub
			FeedbackEntries fb = (FeedbackEntries)obj;
			return ( this.getFb_target_id() == fb.getFb_target_id() && 
					this.getFb_attr_id() == fb.getFb_attr_id() );
		}

}
