package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.student.data.dao.CommunityDao;
import com.student.data.dao.JpushDao;
import com.student.data.dao.ReviewDao;

public class ScoreTest {

	private JpushDao communityDao;

	@Test
	public void Reg() {
		communityDao = new JpushDao();
//		scoreTypeId=5&scoreCourseId=33&action_flag=listMessage
		List<Object> params = new ArrayList<Object>();
		params.add("14");
		List<Map<String, Object>> list_msg = communityDao.listMessage();
		
		JSONObject jsonmsg = new JSONObject();
		jsonmsg.put("repCode", "666");
		jsonmsg.put("data", list_msg);
		System.out.println(jsonmsg);
	}
}
