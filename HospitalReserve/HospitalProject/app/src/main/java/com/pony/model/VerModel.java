package com.pony.model;

import java.io.Serializable;

public class VerModel implements Serializable{

	private String Id;
	private String StudentId;
	private String CoursewareId;
	private String CoursewareName;
	private String WorkType;
	private String WorkTypeName;
	private String CreateDateTime;
	private String RecordType;
	private String RecordTypeName;
	private String Result;
	private String ResultName;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getStudentId() {
		return StudentId;
	}

	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public String getCoursewareId() {
		return CoursewareId;
	}

	public void setCoursewareId(String coursewareId) {
		CoursewareId = coursewareId;
	}

	public String getCoursewareName() {
		return CoursewareName;
	}

	public void setCoursewareName(String coursewareName) {
		CoursewareName = coursewareName;
	}

	public String getWorkType() {
		return WorkType;
	}

	public void setWorkType(String workType) {
		WorkType = workType;
	}

	public String getWorkTypeName() {
		return WorkTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		WorkTypeName = workTypeName;
	}

	public String getCreateDateTime() {
		return CreateDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		CreateDateTime = createDateTime;
	}

	public String getRecordType() {
		return RecordType;
	}

	public void setRecordType(String recordType) {
		RecordType = recordType;
	}

	public String getRecordTypeName() {
		return RecordTypeName;
	}

	public void setRecordTypeName(String recordTypeName) {
		RecordTypeName = recordTypeName;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getResultName() {
		return ResultName;
	}

	public void setResultName(String resultName) {
		ResultName = resultName;
	}

}
