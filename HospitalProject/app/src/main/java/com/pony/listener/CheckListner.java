package com.pony.listener;

import com.pony.model.CategoryModel;
import com.pony.model.ReviewModel;

public interface CheckListner {
	void setRemove(int pos, ReviewModel reviewModel);
}
