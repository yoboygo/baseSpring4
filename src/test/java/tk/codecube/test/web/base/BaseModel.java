/**
 * BaseModel.java
 * 
 * Aimy
 * 下午1:24:13
 */
package tk.codecube.test.web.base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/** 实体模型基类
 * @author Aimy
 * 2015年2月9日 下午1:24:13
 */
@MappedSuperclass
public class BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6889594499823085139L;
	
	/**
	 * 主键
	 */
	private String id;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name="ID_",length=32,unique=true,nullable=false,updatable=false)
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public  String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
