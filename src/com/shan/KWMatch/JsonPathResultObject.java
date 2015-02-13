package com.shan.KWMatch;

import java.util.List;
import java.util.StringTokenizer;

public class JsonPathResultObject {
	
	private String postText;
	
	private List<String> commentText;

	public List<String> getCommentText() {
		return commentText;
	}

	public void setCommentText(List<String> commentText) {
		this.commentText = commentText;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	
}
